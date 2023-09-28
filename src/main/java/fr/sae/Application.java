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
        MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);
        TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);

        // Récupérer la racine de l'arbre généré et regénérer un arbre "miroir" avec nos classes
        TreeNode r = globalAlgebraicTree.getRootNode();
        ETreeNode re = ETreeNode.createTree(r);
        re.print("");

    }


}