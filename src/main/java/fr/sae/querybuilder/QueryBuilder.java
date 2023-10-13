package fr.sae.querybuilder;

import fr.sae.algebraictree.ETreeNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class QueryBuilder
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

//    private String removeUnqerablePart(String )

    /**
     * Parcours un arbre algébrique de requête, et extrait de chaque noeud une partie de requête SQL,
     * et les ajoutes à une HashMap pour utilisation utlérieure.
     *
     * @param currentNode Le noeud actuel dans la récursion
     * @param queryMap    La HashMap à remplir avec les "jetons" de la requête
     *
     * @return La HashMap remplie
     */
    private static void extractQueryPartsFromTree(
            ETreeNode currentNode,
            HashMap<String,
                    ArrayList<String>> queryMap,
            ArrayList<String> tableList
    ) {
        switch (currentNode.getClass().getSimpleName()) {
            case "EProjection":
                String[] test = currentNode.toString().split(",");
                Stream<String> mappedTest = Arrays.stream(test).filter(item -> tableList.stream().anyMatch(item::contains));
                String computed = String.join(",", mappedTest.toArray(String[]::new));

                if (!queryMap.get("SELECT").contains(computed)) {
                    queryMap.get("SELECT").add(computed);
                };
                break;
            case "EJoin":
                String[] splatJoin = currentNode.toString().split("=");

                boolean firstMatch = tableList.stream().anyMatch(splatJoin[0]::contains);
                boolean secondMatch = tableList.stream().anyMatch(splatJoin[1]::contains);

                boolean impossibleJoin = Objects.equals(
                        splatJoin[0].split("\\.")[0].replaceAll(" ", ""), splatJoin[1].split("\\.")[0].replaceAll(" ", "")
                );

                if (!queryMap.get("JOIN").contains(currentNode.toString()) && (firstMatch && secondMatch) && !impossibleJoin) {
                    queryMap.get("JOIN").add(currentNode.toString());
                };
                break;
            case "ESelection":
                String selection = "";

                if (currentNode.toString().contains("OR")) {
                    String cleanedSelection = currentNode.toString().replaceAll("[()]", "");
                    String[] splatSelection = cleanedSelection.split("OR");
                    Stream<String> filteredSelection = Arrays.stream(splatSelection)
                            .filter(item -> tableList.stream().anyMatch(item::contains));
                    String[] selectionArray = filteredSelection.toArray(String[]::new);
                    selection = String.join(" OR ", selectionArray);

                    if (selectionArray.length > 1) {
                        selection = "(" + selection + ")";
                    }
                }

                if (!selection.isEmpty() &&!queryMap.get("WHERE").contains(selection)) {
                    queryMap.get("WHERE").add(selection);
                };
                break;
            case "ETable":
                if (!queryMap.get("FROM").contains(currentNode.toString())) {
                    queryMap.get("FROM").add(currentNode.toString());
                };
                break;
        }
        // Vu que l'on remonte l'arbre par le bas, il faut vérifier s'il existe un parent
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
        // Création et remplissage
        ArrayList<String> tableList = new ArrayList<>();
        nodeList.forEach(item -> tableList.add(item.toString()));

        HashMap<String, ArrayList<String>> splitQuery = QueryBuilder.initializeHashMap();

        nodeList.forEach(node -> QueryBuilder.extractQueryPartsFromTree(node, splitQuery, tableList));

        splitQuery.get("JOIN").addAll(splitQuery.get("WHERE"));

        ArrayList<String> join = splitQuery.get("JOIN");
        String whereClause = "";

        if (!join.isEmpty()) {
            for (int i = 0; i < join.size(); i++) {
                whereClause = whereClause.concat(i == join.size() - 1 ? join.get(i) : join.get(i) + " AND ");
            }
        }

        return String.format(
                "SELECT %s FROM %s WHERE %s",
                String.join(",", splitQuery.get("SELECT").get(0)),
                String.join(",", splitQuery.get("FROM")),
                whereClause
        );
    }
}
