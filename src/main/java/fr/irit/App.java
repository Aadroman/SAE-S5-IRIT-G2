package fr.irit;

import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import fr.irit.module3.TransformationTransferAlgebraicTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        /*
        //Store queries in a list
        List<String> queries = getQueries("C:\\Users\\lelahdab\\Documents\\STAGE - Paul\\Projet\\query-tree-modules\\src\\annexes\\queries.txt");
        //Shuffle queries
        Collections.shuffle(queries);

        for(int i =0; i< queries.size();i++){
            int number = i + 1;
            System.out.println("Query " + number + " : " + queries.get(i));
            String query = queries.get(i);
            SqlQueryParser.QueryContext tree = QueryParser.parseSqlQuery(query);
            GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(tree);

            System.out.println("Algebraic Tree : ");
            globalAlgebraicTree.getRootNode().print("");

            MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);

            System.out.println("");
            System.out.println("Algebraic Multi-stores Tree : ");
            mat.getMultistoreAlgebraicTree().print("");

            TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
            System.out.println("");
            System.out.println("Algebraic Multi-stores Tree : ");
            ttat.getTransformationTransferAlgebraicTree().print("");
        }*/


        //TODO Handle Table alias
//        String query = "SELECT * FROM Orders";
        String query = "SELECT Customers.customer_id, Orders.order_id, Orders.total_price, Products.brand " +
                "FROM Reviews, Orders, Products, Customers " +
                "WHERE (Orders.total_price > 10000 OR Customers.zipcode = 31000) " +
                "AND Products.brand = nike AND Products.p_price > 100 " +
                "AND Reviews.order_id = Orders.order_id " +
                "AND Reviews.product_id = Products.product_id " +
                "AND Customers.customer_id = Orders.customer_id ";
//
        Query queryParsed = QueryParserUtils.parse(query);
        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);

        System.out.println("Algebraic Tree : ");
        globalAlgebraicTree.getRootNode().print("");

        MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);

        System.out.println("");
        System.out.println("Algebraic Multi-stores Tree : ");
        mat.getMultistoreAlgebraicTree().print("");

        TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
        System.out.println("");
        System.out.println("Algebraic Multi-stores Tree : ");
        ttat.getTransformationTransferAlgebraicTree().print("");
    }

    /**
     * User queries getter from file
     *
     * @param fileName
     * @return listFileLines //each line contains one query
     */
    public static List<String> getQueries(String fileName) {
        List<String> listFileLines = new ArrayList<String>();
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(fileName);
            Scanner scanner = new Scanner(file);

            //renvoie true s'il y a une autre ligne à lire
            while (scanner.hasNextLine()) {
                listFileLines.add(scanner.nextLine());
                //System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFileLines;
    }
}
