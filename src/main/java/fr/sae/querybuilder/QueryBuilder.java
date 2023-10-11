package fr.sae.querybuilder;

import fr.sae.algebraictree.ETreeNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Parcours un arbre algébrique de requête, et extrait de chaque noeud une partie de requête SQL,
     * et les ajoutes à une HashMap pour utilisation utlérieure.
     *
     * @param currentNode Le noeud actuel dans la récursion
     * @param queryMap    La HashMap à remplir avec les "jetons" de la requête
     *
     * @return La HashMap remplie
     */
    private static HashMap<String, ArrayList<String>> extractQueryPartsFromTree(
            ETreeNode currentNode,
            HashMap<String,
                    ArrayList<String>> queryMap
    ) {
        switch (currentNode.getClass().getSimpleName()) {
            case "EProjection":
                queryMap.get("SELECT").add(currentNode.toString());
                break;
            case "EJoin":
                queryMap.get("JOIN").add(currentNode.toString());
                break;
            case "ESelection":
                queryMap.get("WHERE").add(currentNode.toString());
                break;
            case "ETable":
                queryMap.get("FROM").add(currentNode.toString());
                break;
        }
        // Vu que l'on remonte l'arbre par le bas, il faut vérifier s'il existe un parent
        if (null == currentNode.getParent()) {
            return queryMap;
        } else {
            return QueryBuilder.extractQueryPartsFromTree(currentNode.getParent(), queryMap);
        }
    }

    /**
     * Crée une requête SQL à partir d'un noeud feuille de l'arbre en algébrique,
     * en remontant sa branche jusqu'au parent.
     *
     * @param node Noeud feuille (bout de branche, donc sans enfant)
     */
    public static String buildQueryFromTree(ETreeNode node, ArrayList<String> tableList) {
        // Récupération des sous parties de la query à partir de l'arbre algébrique
        HashMap<String, ArrayList<String>> splitQuery = QueryBuilder.extractQueryPartsFromTree(node, QueryBuilder.initializeHashMap());

        splitQuery.get("JOIN").addAll(splitQuery.get("WHERE"));

        ArrayList<String> join = splitQuery.get("JOIN");
        String whereClause = "";

        for (int i = 0; i < join.size(); i++) {
            whereClause = whereClause.concat(i == join.size() - 1 ? join.get(i) : join.get(i) + " AND ");
        }

        return String.format(
                "SELECT %s FROM %s WHERE %s",
                String.join(",", splitQuery.get("SELECT")),
                String.join(",", splitQuery.get("FROM")),
                whereClause
        );
    }
}
