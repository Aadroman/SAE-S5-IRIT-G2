package fr.irit.module2;

import com.google.gson.Gson;
import fr.irit.algebraictree.*;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module2.UnifiedView.Column;
import fr.irit.module2.UnifiedView.Store;
import fr.irit.module2.UnifiedView.UVTable;
import fr.irit.module2.UnifiedView.UnifiedView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static fr.irit.module1.QueryParserUtils.getQueryType;

/**
 * Constructor of MultistoreAlgebraicTree add stores information to table nodes and add joins between same tables in different stores
 * and set the new tree root node to rootNode class attribute
 */
public class MultistoreAlgebraicTree {
    //region ATTRIBUTES
    private TreeNode rootNode;
    private UnifiedView unifiedViewFiltered;
    //endregion
    //region CONSTRUCTOR

    /**
     * For each table of the globalAlgebraicTree the constructor first set Store information object on the node
     * If the table is split into several stores it adds the necessary joins
     *
     * @param globalAlgebraicTree
     */
    public MultistoreAlgebraicTree(GlobalAlgebraicTree globalAlgebraicTree) {
        TreeNode rootNode = globalAlgebraicTree.getRootNode();
        UnifiedView unifiedView = this.readUnifiedView();
        // Filter unified view : keep only tables required by the query
        if (((Projection) rootNode).getAttributes() == null) {
            // Handle Select * case
            this.unifiedViewFiltered = unifiedView;
        } else {
            Set<DotNotation> columnsInTree = rootNode.listDistinctColumnsRecursive();
            this.unifiedViewFiltered = this.filterUnifiedView(unifiedView, columnsInTree);
        }
        // Iterate on tables in tree to set store information and add multi-stores join
        for (Table table : globalAlgebraicTree.getTables()) {
            UVTable uvTable = this.unifiedViewFiltered.getUVTable(table.getLabel());
            if (uvTable == null || uvTable.stores.size() == 0) {
                throw new UnsupportedOperationException("A table should be reference in unified view");
            } else if (uvTable.stores.size() == 1) {
                table.setStore(uvTable.stores.get(0));
            } else {
                createMultistoreJoin(table, uvTable);
            }
        }
        rootNode.renameColumnsRecursive(this.getColumnNamingMap());
        //TODO Optimize selection again
        this.rootNode = globalAlgebraicTree.getRootNode();
    }

    //endregion
    //region GETTERS & SETTERS
    public TreeNode getMultistoreAlgebraicTree() {
        return rootNode;
    }

    //endregion
    //region METHODS

    /**
     * Parse json unified view and convert it into UnifiedView Java Object
     */
    private UnifiedView readUnifiedView() {
        try {
            FileReader fr;
            if (getQueryType().name() == QueryParserUtils.QueryType.RELATIONAL.name()) {
                fr = new FileReader("src/main/java/fr/irit/module2/UnifiedView/relationalUnifiedView.json");
            } else {
                fr = new FileReader("src/main/java/fr/irit/module2/UnifiedView/documentUnifiedView.json");
            }
            BufferedReader br = new BufferedReader(fr);
            return new Gson().fromJson(br, UnifiedView.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Filter the unified view to keep only the column request in the query
     *
     * @param unifiedView   complete unified view read in json format
     * @param columnsInTree Unique list (Set) containing all columns in the tree
     * @return UnifiedView Object filtered according to the query columns needs
     */
    private UnifiedView filterUnifiedView(UnifiedView unifiedView, Set<DotNotation> columnsInTree) {
        List<UVTable> uvTableList = new ArrayList<>();
        for (UVTable uvTable : unifiedView.uvTables) {
            List<Store> storeList = new ArrayList<>();
            for (Store store : uvTable.stores) {
                List<Column> columnList = store.getMatchingColumns(columnsInTree);
                if (!columnList.isEmpty()) {
                    Store s = new Store(store.name, store.type, columnList);
                    storeList.add(s);
                }
            }
            if (!storeList.isEmpty()) {
                UVTable t = new UVTable(uvTable.label, storeList);
                uvTableList.add(t);
            }
        }
        return new UnifiedView(unifiedView.type, uvTableList);
    }

    /**
     * Add new joins to the tree for take in account the multiple stores
     *
     * @param table   table in the tree
     * @param uvTable table associated in the unified view
     */
    private void createMultistoreJoin(Table table, UVTable uvTable) {
        // Create new Tables with store object;
        List<Table> newTablesList = new ArrayList<>();
        for (int i = 0; i < uvTable.stores.size(); i++) {
            Store store = uvTable.stores.get(i);
            if (i == 0) {
                table.setStore(store);
            } else {
                Table t = new Table(uvTable.label);
                t.setStore(store);
                newTablesList.add(t);
            }
        }
        ;
        Table currentTable = table;
        // Create new joins for all tables in multiple stores
        for (Table newTable : newTablesList) {
            TreeNode parent = currentTable.getParent();
            Join join = new Join(table.getStore().getStoreKey(), newTable.getStore().getStoreKey());
            if (parent instanceof Join) {
                // Is table the left child or the right child
                if (((Join) parent).getLeftChild().equals(table)) {
                    parent.addChildren(join, ((Join) parent).getRightChild());
                } else {
                    parent.addChildren(((Join) parent).getLeftChild(), join);
                }
            } else {
                parent.addChildren(join);
            }
            join.addChildren(currentTable, newTable);
            currentTable = newTable;
        }
    }

    private Map<DotNotation, DotNotation> getColumnNamingMap() {
        Map<DotNotation, DotNotation> columnNamingMap = new HashMap<>();
        for (UVTable table : this.unifiedViewFiltered.uvTables) {
            for (Store store : table.stores) {
                for (Column column : store.columns) {
                    columnNamingMap.put(column.columnUVtoDotNotation(), column.columnLinkedtoDotNotation());
                }
            }
        }
        return columnNamingMap;
    }
    //endregion
}
