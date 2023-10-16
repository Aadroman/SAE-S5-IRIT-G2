package fr.sae.querybuilder;

import fr.sae.algebraictree.ETreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class QueryBuilder.
 * Permet de recréer des requêtes SQL fonctionnelles à partir d'une branche de l'arbre algébrique de transferts.
 */
public class QueryBuilder {
    /**
     * Fonction d'initialisation d'une HashMap avec les propriétées souhaitées
     */
    private static HashMap<String, ArrayList<String>> initializeHashMap() {
        // Initialisation de la HashMap
        HashMap<String, ArrayList<String>> init = new HashMap<>();

        // Création des paires clées valeures vides
        init.put("SELECT", new ArrayList<>());
        init.put("JOIN", new ArrayList<>());
        init.put("WHERE", new ArrayList<>());
        init.put("FROM", new ArrayList<>());

        return init;
    }

    /**
     * Parcours un arbre algébrique de requête, et extrait de chaque noeud une partie de requête SQL,
     * et les ajoutes à une HashMap pour utilisation utlérieure.
     *
     * @param currentNode Le noeud actuel dans la récursion
     * @param queryMap    La HashMap à remplir avec les "jetons" de la requête
     */
    private static void extractQueryPartsFromTree(
            ETreeNode currentNode,
            HashMap<String,
                    ArrayList<String>> queryMap,
            ArrayList<String> tableList
    ) {
        switch (currentNode.getClass().getSimpleName()) {
            case "EProjection":
                // Vérifie si le select correspond bien aux tables de la BDD en question
                String[] splatProjection = currentNode.toString().split(",");
                Stream<String> filteredProjectionStreal = Arrays
                    .stream(splatProjection)
                    .filter(item -> tableList.stream().anyMatch(item::contains));
                String updatedProjection = String.join(",", filteredProjectionStreal.toArray(String[]::new));

                // Evite les clauses doublons dans la requête
                if (!queryMap.get("SELECT").contains(updatedProjection)) {
                    queryMap.get("SELECT").add(updatedProjection);
                }
                break;
            case "EJoin":
                // Sépare chaque argument de la clause JOIN
                String[] splatJoin = currentNode.toString().split("=");

                // Verifie que chaque parties de l'équation de join vise une table existant dans la bdd actuelle
                boolean isLeftArgumentValid = tableList.stream().anyMatch(splatJoin[0]::contains);
                boolean isRightArgumentValid = tableList.stream().anyMatch(splatJoin[1]::contains);

                // Permet d'éviter les erreur en cas de deux tables de bases différentes avec le même nom
                boolean isSelfJoin = Objects.equals(
                    splatJoin[0].split("\\.")[0].replaceAll(" ", ""),
                    splatJoin[1].split("\\.")[0].replaceAll(" ", "")
                );

                // Evite les clauses doublons dans la requête
                if (
                    !queryMap.get("JOIN").contains(currentNode.toString())
                    && (isLeftArgumentValid
                    && isRightArgumentValid) && !isSelfJoin
                ) {
                    queryMap.get("JOIN").add(currentNode.toString());
                }
                break;
            case "ESelection":
                String selection = "";

                if (currentNode.toString().contains("OR")) {
                    String cleanedSelection = currentNode.toString().replaceAll("[()]", "");
                    String[] splatSelection = cleanedSelection.split("OR");
                    // Enlève toutes les conditions du "OR" qui visent un table non présente dans la BDD actuelle
                    Stream<String> filteredSelection = Arrays.stream(splatSelection)
                            .filter(item -> tableList.stream().anyMatch(item::contains));
                    String[] selectionArray = filteredSelection.toArray(String[]::new);
                    selection = String.join(" OR ", selectionArray);

                    // Ne met des parenthèses que s'il existe plusieurs conditions
                    if (selectionArray.length > 1) {
                        selection = "(" + selection + ")";
                    }
                }

                // Evite les clauses doublons dans la requête
                if (!selection.isEmpty() &&!queryMap.get("WHERE").contains(selection)) {
                    queryMap.get("WHERE").add(selection);
                }
                break;
            case "ETable":
                // Evite les clauses doublons dans la requête
                if (!queryMap.get("FROM").contains(currentNode.toString())) {
                    queryMap.get("FROM").add(currentNode.toString());
                }
                break;
        }
        // Vu que l'on remonte l'arbre par le bas, il faut vérifier s'il existe un parent au noeud actuel
        if (null != currentNode.getParent()) {
            QueryBuilder.extractQueryPartsFromTree(currentNode.getParent(), queryMap, tableList);
        }
    }

    /**
     * Crée une requête SQL à partir d'un noeud feuille de l'arbre en algébrique,
     * en remontant sa branche jusqu'au parent.
     *
     * @param nodeList Noeud feuille (bout de branche, donc sans enfant)
     */
    public static String buildQueryFromTree(ArrayList<ETreeNode> nodeList) {
        // Création et remplissage de la liste des tables existant dans la BDD correspondant à la branche
        ArrayList<String> tableList = new ArrayList<>();
        nodeList.forEach(item -> tableList.add(item.toString()));

        // Création et remplissage de la HashMap stockant les différentes parties de la requête
        HashMap<String, ArrayList<String>> splitQuery = QueryBuilder.initializeHashMap();
        nodeList.forEach(node -> QueryBuilder.extractQueryPartsFromTree(node, splitQuery, tableList));

        // Pour l'instant seules des "NATURAL JOIN" sont utilisées, donc on peut regrouper les clauses JOIN et WHERE
        splitQuery.get("JOIN").addAll(splitQuery.get("WHERE"));

        // Création de la clause "WHERE" avec toutes les conditions
        ArrayList<String> join = splitQuery.get("JOIN");
        String whereClause = "";
        if (!join.isEmpty()) {
            for (int i = 0; i < join.size(); i++) {
                whereClause = whereClause.concat(i == join.size() - 1 ? join.get(i) : join.get(i) + " AND ");
            }
        }

        // Formattage final de la requête
        return String.format(
                "SELECT %s FROM %s WHERE %s",
                String.join(",", splitQuery.get("SELECT").get(0)),
                String.join(",", splitQuery.get("FROM")),
                whereClause
        );
    }
}
