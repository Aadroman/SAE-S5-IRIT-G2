package fr.irit.module1.algebraictree;

import fr.irit.algebraictree.Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    @Test
    public void testGetLabel() {
        String expectedLabel = "table1";
        var table = new Table(expectedLabel);

        assertEquals(table.getLabel(), expectedLabel);
    }
    @Test
    public void testTestToString() {
        String expectedLabel = "table1";
        var table = new Table(expectedLabel);

        assertEquals(table.toString(), expectedLabel);
    }

    @Test
    public void testListIncludedTables() {
        String expectedLabel = "table1";
        var table = new Table(expectedLabel);

        assertEquals(new ArrayList<String>(){{add(expectedLabel);}}, table.listIncludedTablesRecursive());
    }

    @Test
    public void testFindLowestNodeContainingTables() {
        String expectedLabel = "table1";
        var table = new Table(expectedLabel);
        List<String> tableListExpectedResult = new ArrayList<>(List.of(expectedLabel));
        List<String> tableListExpectedNull = new ArrayList<>(List.of("table2"));

        assertEquals(table, table.findLowestNodeContainingTables(tableListExpectedResult));
        assertNull(table.findLowestNodeContainingTables(tableListExpectedNull));
    }

    @Test
    public void testAddChildren() {
        var table = new Table("table1");
        var childNode = new Table("table2");

        UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, () -> {table.addChildren(childNode);});
        assertEquals("addChildren method not supported in Table node", thrown.getMessage());
    }

    public void testPrint() {
    }
}