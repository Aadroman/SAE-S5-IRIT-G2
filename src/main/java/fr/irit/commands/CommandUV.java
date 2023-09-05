package fr.irit.commands;

import com.google.gson.Gson;
import fr.irit.module2.UnifiedView.UnifiedView;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * CommandUV class define all parameters & options for the command 'unified-view'
 * which show to the user the polystore as a unified view.
 */
@CommandLine.Command(
        name = "unified-view",
        header = "Display polystore unified view",
        synopsisHeading = "%nUsage:%n%n",
        customSynopsis = {
                "java -jar polyglot.jar unified-view [--help] [-pimpl]",
        },
        descriptionHeading = "%nDescription:%n%n",
        description = "Output Format :\nTable : \n    DB_NAME (DB_TYPE) [column_in_unified_view <-> column_in_real_implementation]",
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n"
)
public class CommandUV implements Runnable{ //TODO add mongo unified view

    @CommandLine.Parameters(index = "0", description = "Database type (relational or document)")
    private String dbType;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean help;

    @CommandLine.Option(names = {"-pimpl", "--physical-implementation"}, description = "show physical implementation in addition of unified view")
    boolean physicalImplementation = false;

    /**
     * CommandUV.run read unifiedView json file and print it to the user
     */
    @Override
    public void run() {
        try {
            FileReader fr;
            if(dbType == "relational" || dbType == "RELATIONAL"){
                fr = new FileReader("src/main/java/fr/irit/module2/UnifiedView/relationalUnifiedView.json");
            } else {
                fr = new FileReader("src/main/java/fr/irit/module2/UnifiedView/documentUnifiedView.json");
            }
            BufferedReader br = new BufferedReader(fr);
            UnifiedView uv = new Gson().fromJson(br, UnifiedView.class);
            uv.print(physicalImplementation);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
