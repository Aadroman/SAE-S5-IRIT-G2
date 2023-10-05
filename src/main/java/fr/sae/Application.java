package fr.sae;

import fr.irit.algebraictree.*;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import fr.irit.module3.TransformationTransferAlgebraicTree;
import fr.sae.algebraictree.*;

public class Application {
    public static void main(String[] args){
        String query = "SELECT Customers.customer_id, Orders.order_id, Orders.total_price, Products.brand " +
                "FROM Reviews, Orders, Products, Customers " +
                "WHERE (Orders.total_price > 10000 OR Customers.zipcode = 31000) " +
                "AND Products.brand = nike AND Products.p_price > 100 " +
                "AND Reviews.order_id = Orders.order_id " +
                "AND Reviews.product_id = Products.product_id " +
                "AND Customers.customer_id = Orders.customer_id ";
        Query queryParsed = QueryParserUtils.parse(query);
        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);
        System.out.println("Algebraic Tree : ");
        TreeNode global = globalAlgebraicTree.getRootNode();
        ETreeNode globalTree = ETreeNode.createTree(global);
        globalTree.print("");


        MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);
        System.out.println("");
        System.out.println("Algebraic Multi-stores Tree : ");
        // Récupérer la racine de l'arbre généré et regénérer un arbre "miroir" avec nos classes
        TreeNode multi = mat.getMultistoreAlgebraicTree();
        ETreeNode multiTree = ETreeNode.createTree(multi);
        multiTree.print("");

       TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
       System.out.println("");
       System.out.println("Algebraic Multi-stores Tree With transformation: ");
       TreeNode multiTransformation = ttat.getTransformationTransferAlgebraicTree();
       ETreeNode multiTransformationTree = ETreeNode.createTree(multiTransformation);
       multiTransformationTree.print("");

    }

    public static ETreeNode[] getTreeFromQuery(Query q){
        // Query déjà parsed pour l'instant
        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(q);
        TreeNode global = globalAlgebraicTree.getRootNode();
        ETreeNode globalTree = ETreeNode.createTree(global);
        //globalTree.print("");

        MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);
        TreeNode multi = mat.getMultistoreAlgebraicTree();
        ETreeNode multiTree = ETreeNode.createTree(multi);
        //multiTree.print("");

        TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
        TreeNode multiTransformation = ttat.getTransformationTransferAlgebraicTree();
        ETreeNode multiTransformationTree = ETreeNode.createTree(multiTransformation);
        //multiTransformationTree.print("");

        ETreeNode[] treeArray = new ETreeNode[3];
        treeArray[0] = globalTree;
        treeArray[1] = multiTree;
        treeArray[2] = multiTransformationTree;

        return treeArray;
    }


}