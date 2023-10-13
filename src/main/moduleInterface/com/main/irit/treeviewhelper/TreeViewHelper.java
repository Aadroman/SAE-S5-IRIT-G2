package com.main.irit.treeviewhelper;

import fr.sae.algebraictree.ETreeNode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Classe utilitaire regroupant des fonctions d'aide pour les TreeView JavaFX.
 */
public class TreeViewHelper {
    /**
     * Recherche et retourne un TreeItem en fonction de sa valeur.
     *
     * @param root  La racine de la TreeView dans laquelle rechercher
     * @param value La valeur à rechercher
     */
    public static TreeItem<Object> findTreeItem(TreeItem<Object> root, String value) {
        if (root == null) {
            return null;
        }

        if (root.getValue().equals(value)) {
            return root;
        }

        for (TreeItem<Object> child : root.getChildren()) {
            TreeItem<Object> result = findTreeItem(child, value);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * Méthode utilitaire permettant de savoir si il existe déjà un TreeItem avec une valeure spécifique.
     *
     * @param treeView La TreeView dans laquelle chercher
     * @param value    La valeur à rechercher
     *
     * @return True si la valeur existe dans l'arbre
     */
    public static boolean isValueExists(TreeView<Object> treeView, String value) {
        return treeView.getRoot().getChildren().stream()
                .anyMatch(item -> ((TreeItem<?>) item).getValue().equals(value));
    }

    /**
     * Fonction utilitaire qui ajoute une valeur à une TreeView si elle n'y est pas déjà.
     *
     * @param valueToAdd La valeur à ajouter dans l'arbre
     *
     * @return Le TreeItem pré-existant OU celui nouvellement crée
     */
    public static TreeItem<Object> addValueToTree(TreeView<Object> treeView, String valueToAdd) {
        if (!isValueExists(treeView, valueToAdd)) {
            TreeItem<Object> dbRoot = new TreeItem<>(valueToAdd);
            treeView.getRoot().getChildren().add(dbRoot);

            return dbRoot;
        }

        return findTreeItem(treeView.getRoot(), valueToAdd);
    }

    /**
     * Remplit l'arborescence de la TreeView en partant d'une feuille, vers la racine.
     *
     * @param child  Le noeud enfant
     * @param parent Le noeud parent
     */
    public static TreeItem<Object> bottomToTopBranchFill(TreeItem<Object> child, ETreeNode parent) {
        if (null == parent) {
            return child;
        } else {
            TreeItem<Object> parentItem = new TreeItem<>(parent);
            parentItem.getChildren().add(child);

            return TreeViewHelper.bottomToTopBranchFill(parentItem, parent.getParent());
        }
    }
}
