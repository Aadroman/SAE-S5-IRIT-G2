module query_tree_modules{
    requires javafx.controls;
    requires javafx.fxml;
    requires org.abego.treelayout.core;
    requires java.desktop;
    requires org.apache.commons.collections4;
    requires info.picocli;
    requires com.google.gson;
    requires org.antlr.antlr4.runtime;

    exports com.main.irit;
    exports fr.irit.module1;
    exports fr.irit.module2;
    exports fr.irit.module3;
    exports fr.irit.module4;
    exports fr.irit.algebraictree;
    exports fr.irit.commands;
    opens com.main.irit to javafx.fxml;
    opens fr.irit.module2.UnifiedView to com.google.gson;
}
