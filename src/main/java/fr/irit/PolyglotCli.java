package fr.irit;

import fr.irit.commands.CommandRun;
import fr.irit.commands.CommandUV;
import picocli.CommandLine;

/**
 * Main class for run the program in command line interface.
 * The various commands available are described in ./commands package
 */
@CommandLine.Command(
        name = "Polyglot query rewriter",
        header = "Polyglot query rewriter",
        subcommands = {CommandRun.class, CommandUV.class}
)
public class PolyglotCli implements Runnable{
    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean help;
    @Override
    public void run(){
        System.out.println("command needed : 'run' OR 'unified-view");
    }
    public static void main(String[] args){
        new CommandLine((new PolyglotCli())).setInterpolateVariables(false).execute(args) ;
    }
}
