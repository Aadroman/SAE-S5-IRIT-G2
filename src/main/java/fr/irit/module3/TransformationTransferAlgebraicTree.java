package fr.irit.module3;

import fr.irit.algebraictree.*;
import fr.irit.module2.MultistoreAlgebraicTree;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Constructor of TransformationTransferAlgebraicTree add all conversions to the MultistoreAlgebraicTree
 * and set the new tree root node to rootNode class attribute
 */
public class TransformationTransferAlgebraicTree {
    //region ATTRIBUTES
    private TreeNode rootNode;
    private String TARGET_DB_NAME = "DB1";
    private String TARGET_DB_TYPE = "RELATIONAL";
    //endregion
    //region CONSTRUCTOR

    /**
     * Set transformations and transfers nodes to the multi-stores algebraic tree given in parameter
     * Transformations and Transfers are added depending on TARGET_DB_NAME and TARGET_DB_TYPE
     * @param multistoreAlgebraicTree
     */
    public TransformationTransferAlgebraicTree(MultistoreAlgebraicTree multistoreAlgebraicTree){
        TreeNode root = multistoreAlgebraicTree.getMultistoreAlgebraicTree();
        List<String> tableNameList = root.listIncludedTablesRecursive();
        List<String> tableNameListDistinct = tableNameList.stream().distinct().collect(Collectors.toList());
        for(String tableName : tableNameListDistinct){
            TreeNode lowestNode = root.findLowestNodeContainingTables(List.of(tableName));
            this.handleLowestNode(lowestNode, tableName);
        }
        this.rootNode = root;
    }
    public TransformationTransferAlgebraicTree(MultistoreAlgebraicTree multistoreAlgebraicTree, String targetDbName, String targetDbType){
        this.TARGET_DB_NAME = targetDbName;
        this.TARGET_DB_TYPE = targetDbType;
        TreeNode root = multistoreAlgebraicTree.getMultistoreAlgebraicTree();
        List<String> tableNameList = root.listIncludedTablesRecursive();
        List<String> tableNameListDistinct = tableNameList.stream().distinct().collect(Collectors.toList());
        for(String tableName : tableNameListDistinct){
            TreeNode lowestNode = root.findLowestNodeContainingTables(List.of(tableName));
            this.handleLowestNode(lowestNode, tableName);
        }
        this.rootNode = root;
    }
    //endregion
    //region GETTERS & SETTERS
    public TreeNode getTransformationTransferAlgebraicTree() {
        return rootNode;
    }
    //endregion
    //region METHODS

    /**
     * handleLowestNode search add transfers and transformations to every Table nodes named like TableName param
     * @param lowestNode Lowest node in the tree containing the table in tableName
     * @param tableName Name of the table node searched
     */
    private void handleLowestNode(TreeNode lowestNode, String tableName){
        if(lowestNode instanceof Join){
            TreeNode lowestNodeLeft = ((Join)lowestNode).getLeftChild().findLowestNodeContainingTables(List.of(tableName));
            this.handleLowestNode(lowestNodeLeft, tableName);
            TreeNode lowestNodeRight = ((Join)lowestNode).getRightChild().findLowestNodeContainingTables(List.of(tableName));
            this.handleLowestNode(lowestNodeRight, tableName);
        } else if (lowestNode instanceof Table){
            if(this.isTransferNeeded((Table) lowestNode)){
                this.addTransferNode((Table) lowestNode);
            }
            if(this.isTransformNeeded((Table) lowestNode)){
                this.addTransformationNode((Table) lowestNode);
            }
        }
    }

    private boolean isTransformNeeded(Table table){
        return !table.getStore().type.equals(TARGET_DB_TYPE);
    }
    private boolean isTransferNeeded(Table table){
        return !table.getStore().name.equals(TARGET_DB_NAME);
    }

    /**
     * Add Transformation Node as child of table parent and replace table as child of Transformation Node
     * @param table Table where transformation should be added
     */
    private void addTransformationNode(Table table){
        Transformation transformation = new Transformation(table.getStore().type, TARGET_DB_TYPE);
        TreeNode parent = table.getParent();
        if(parent instanceof Join){
            if(((Join) parent).getLeftChild().equals(table)){
                parent.addChildren(transformation, ((Join) parent).getRightChild());
            } else {
                parent.addChildren(((Join) parent).getLeftChild(), transformation);
            }
        } else {
            parent.addChildren(transformation);
        }
        transformation.addChildren(table);
    }
    /**
     * Add Transfer Node as child of table parent and replace table as child of Transfer Node
     * @param table Table where transfer should be added
     */
    private void addTransferNode(Table table){
        Transfer transfer = new Transfer(table.getStore().name, TARGET_DB_NAME);
        TreeNode parent = table.getParent();
        if(parent instanceof Join){
            if(((Join) parent).getLeftChild().equals(table)){
                parent.addChildren(transfer, ((Join) parent).getRightChild());
            } else {
                parent.addChildren(((Join) parent).getLeftChild(), transfer);
            }
        } else {
            parent.addChildren(transfer);
        }
        transfer.addChildren(table);
    }
    //endregion
}
