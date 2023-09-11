package fr.irit;

import fr.irit.algebraictree.*;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

// prototype crée par Adriel Marchant afin de tester le code donné
public class prototype {
    public static void main(String[] argsv){
        String query = "SELECT Order_line.price FROM Order_line";
        Query queryParsed = QueryParserUtils.parse(query);
        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);
        globalAlgebraicTree.getRootNode().print("");
        TreeNode rootNode = globalAlgebraicTree.getRootNode();
        System.out.println(queryParsed.getTablesNames());

        MultistoreAlgebraicTree multistoreAlgebraicTree = new MultistoreAlgebraicTree(globalAlgebraicTree);
        System.out.println("test1");
        System.out.println(multistoreAlgebraicTree.getMultistoreAlgebraicTree().listDistinctColumnsRecursive());
        multistoreAlgebraicTree.getMultistoreAlgebraicTree().print("");


    }
}
