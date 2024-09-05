package cli;

import cli.commands.Help;
import cli.commands.Open;
import cli.commands.Print;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CommandInvoker {
    private static CommandInvoker instance;

    private CommandInvoker() {
    }

    public static CommandInvoker getInstance(){
        if (instance == null)
            instance = new CommandInvoker();
        return instance;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("\n> ");
            String command = scanner.nextLine();
            handleCommand(command);
        }
    }

    private void handleCommand(String command){
        String[] tokens = command.split("\\s+");
        String action = tokens[0];

        switch (action.toLowerCase()) {
            case "open" -> {
                if (tokens.length > 1) {
                    new Open(tokens[1]).execute();
                } else {
                    System.out.println("Error: Please specify a file name.");
                }
            }
            case "print" -> {
                new Print().execute();
            }
            case "help" -> {
                new Help().execute();
            }
            case "exit" -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
            default -> {
                System.out.println("Unknown command: " + action);
            }
        }

    }
}
