package fr.irit.commands;

import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import fr.irit.module3.TransformationTransferAlgebraicTree;
import picocli.CommandLine;

/**
 * CommandRun class define all parameters & options for the command 'run'
 * which rewrite sql or mongo query.
 */
@CommandLine.Command(
        name = "run",
        header = "run a sql or mongo query",
        synopsisHeading = "%nUsage:%n%n",
        customSynopsis = {
                "'java -jar polyglot.jar run <query> [-gt] [-mt] [-tt] [--time] [--help]'",
        },
        descriptionHeading = "%nDescription:%n%n",
        description = "Rewrite sql query in relational algebraic tree to query heterogeneous multi-stores.",
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n"
)
public class CommandRun implements Runnable {
    @CommandLine.Parameters(index = "0", description = "Database query (ex: SELECT * FROM Customers)")
    private String query;

    @CommandLine.Option(names = {"--target-db-name"}, defaultValue = "DB1", description = "Target database name (default : ${DEFAULT-VALUE})")
    public static String TARGET_DB_NAME;

    @CommandLine.Option(names = {"--target-db-type"}, defaultValue = "RELATIONAL", description = "Target database type (default : ${DEFAULT-VALUE})")
    public static String TARGET_DB_TYPE;

    @CommandLine.Option(names = {"-gt", "--globalTree"}, description = "Print global tree")
    private boolean printGlobalTree = false;

    @CommandLine.Option(names = {"-mt", "--multistoreTree"}, description = "Print multi-store tree")
    private boolean printMultistoreTree = false;

    @CommandLine.Option(names = {"-tt", "--transferTree"}, description = "Print transfer tree")
    private boolean printTransferTree = false;

    @CommandLine.Option(names = {"--time"}, description = "Show execution time")
    private boolean time = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean help;

    /**
     * CommandRun.run parse query given as parameter and convert it into algebraic tree
     * containing multistore, transformation and transfer info.
     * It also can print tree depending on options given.
     */
    @Override
    public void run() {
        long startTime = System.nanoTime();
        Query queryParsed = QueryParserUtils.parse(query);
        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);
        if (printGlobalTree) {
            System.out.println("\nAlgebraic Tree : \n");
            globalAlgebraicTree.getRootNode().print("");
        }
        MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);
        if (printMultistoreTree) {
            System.out.println("\nAlgebraic Multi-stores Tree : \n");
            mat.getMultistoreAlgebraicTree().print("");
        }
        TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat, TARGET_DB_NAME, TARGET_DB_TYPE);
        if (printTransferTree) {
            System.out.println("\nAlgebraic Multi-stores Tree : \n");
            ttat.getTransformationTransferAlgebraicTree().print("");
        }
        long endTime = System.nanoTime();
        if (time) {
            long duration = (endTime - startTime) / 1000000;
            System.out.println("\nExecution time : " + duration + " ms");
        }
    }
}
