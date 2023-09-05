package fr.irit.module1;

import fr.irit.algebraictree.*;
import fr.irit.module1.queries.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * AlgebraicTree is a class which build a relational algebraic tree
 * It's compose by a succession of nodes (TreeNode object)
 */
public class GlobalAlgebraicTree {
    private TreeNode root;
    private List<Table> tables = new ArrayList<>();

    public TreeNode getRootNode() { return root; }
    public List<Table> getTables() { return this.tables; }

    /**
     * Construct the AlgebraicTree and set AlgebraicTree root node attribute
     * @param query Query parsed by the antlr parser
     */
    public GlobalAlgebraicTree(Query<?> query){
        // Start tree building : create projection node (root)
        Projection rootNode = query.createProjectionNode();
        // Get all table names in a list (used for check at the end)
        List<String> tablesNames = query.getTablesNames();
        if (!query.hasCondition()) {
            // Handle case where the query is composed only by SELECT and FROM
            // That mean we only have 1 table in the query
            Table table = new Table(tablesNames.get(0));
            rootNode.addChildren(table);
            this.tables.add(table);
        } else {
            // Create and add Joins to the tree
            List<Join> joinList = query.createJoinNodes();
            this.setJoinsAndTables(rootNode, joinList, tablesNames);
            // Create and add Selections to the tree
            List<Selection> selectionList = query.createSelectionNodes();
            this.setSelections(rootNode, selectionList);
        }
        this.root = rootNode;
    };



    /**
     * This method add all the joins and the tables associated to the tree.
     * Iterate over the joinList param and for each join add the next join and set the good children.
     *
     * @param previousNode Node from which to start adding joins
     * @param joinList  List containing all join nodes
     * @param tablesNames List containing all table names
     */
    private void setJoinsAndTables(TreeNode previousNode, List<Join> joinList, List<String> tablesNames){
        if(joinList.isEmpty()){
            // That mean we only have 1 table in the query
            Table table = new Table(tablesNames.get(0));
            previousNode.addChildren(table);
            this.tables.add(table);
            return;
        }
        // Add the first join to the tree
        Join firstJoin = joinList.get(0);
        previousNode.addChildren(firstJoin);
        Table tableLeft = new Table(firstJoin.getCondition()[0].table);
        Table tableRight = new Table(firstJoin.getCondition()[1].table);
        firstJoin.addChildren(tableLeft, tableRight);
        this.tables.add(tableLeft);
        this.tables.add(tableRight);
        joinList.remove(firstJoin);

        while( joinList.size() > 0) {
            System.out.println(joinList.size());
            for (Join join : joinList) {
                if(addNextJoin(firstJoin, join)){
                    joinList.remove(join);
                    break;
                }
            }
        }
        if(firstJoin.findLowestNodeContainingTables(tablesNames) == null || tablesNames.size() != this.tables.size()){
            throw new UnsupportedOperationException("Tree builder algorithm error : All tables are not placed in the tree :");
        }
    }

    /**
     * This method try to add following join node.
     * For each condition check if the table is in the tree
     * (if found it -> place a join instead of the table and set join children.
     *
     * @param firstJoin last join node in the tree = current
     * @param join join to add to the current join
     * @return true if the join is added and false if isn't
     */
    private boolean addNextJoin(Join firstJoin, Join join){
        for (DotNotation c : join.getCondition()){
            TreeNode lowestNode = firstJoin.findLowestNodeContainingTables(new ArrayList<>(List.of(c.table)));
            if(lowestNode != null) {
                var leftChild = ((Join) lowestNode.getParent()).getLeftChild();
                var rightChild = ((Join) lowestNode.getParent()).getRightChild();
                if(lowestNode == leftChild){
                    lowestNode.getParent().addChildren(join, rightChild);
                    this.addJoinTables(join, lowestNode);
                } else if(lowestNode == rightChild) {
                    lowestNode.getParent().addChildren(leftChild, join);
                    this.addJoinTables(join, lowestNode);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called by addNextJoin() and allow add tables to the goode side of the join
     *
     * @param join
     * @param existingTable
     */
    private void addJoinTables(Join join, TreeNode existingTable){
        if (existingTable.toString().equals(join.getCondition()[0].table)){
            Table rightTable = new Table(join.getCondition()[1].table);
            join.addChildren(existingTable, rightTable);
            this.tables.add(rightTable);
        } else {
            Table leftTable = new Table(join.getCondition()[0].table);
            join.addChildren(leftTable, existingTable);
            this.tables.add(leftTable);
        }
    }

    /**
     * This method will add all selections to the tree as low as possible.
     *
     * @param rootNode
     * @param selectionList List containing all selection nodes
     */
    private void setSelections(TreeNode rootNode, List<Selection> selectionList){
        if(selectionList.isEmpty()){
            return;
        }
        for (Selection s : selectionList){
            List<String> tablesInSelection = new ArrayList<String>();
            for (Predicate p : s.getPredicates()){
                tablesInSelection.add(p.attribute.table);
            }
            TreeNode lowestNode = rootNode.findLowestNodeContainingTables(tablesInSelection.stream().distinct().toList());
            if(lowestNode.getParent() instanceof Join){
                var leftChild = ((Join) lowestNode.getParent()).getLeftChild();
                var rightChild = ((Join) lowestNode.getParent()).getRightChild();
                if(lowestNode == leftChild){
                    lowestNode.getParent().addChildren(s, rightChild);
                    s.addChildren(leftChild);
                } else if(lowestNode == rightChild) {
                    lowestNode.getParent().addChildren(leftChild, s);
                    s.addChildren(rightChild);
                }
            } else {
                lowestNode.getParent().addChildren(s);
                s.addChildren(lowestNode);
            }
        }
    }

}
