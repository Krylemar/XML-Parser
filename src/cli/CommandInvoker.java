package cli;

import cli.commands.*;
import parser.Parser;

import java.nio.file.Paths;
import java.util.Scanner;

public class CommandInvoker {
    private static CommandInvoker instance;
    private static Parser parser;

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
        parser = Parser.getInstance();

        if (!parser.isFileOpen()){
            if (action.equalsIgnoreCase("open")){
                if (tokens.length > 1){
                    new Open(tokens[1]).execute();
                } else {
                    System.out.println("Error: Please specify a file name.");
                }
            } else {
                System.out.println("Error: No file is open. Please open a file first.");
            }
        }
        else{
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
                case "select" -> {
                    if (tokens.length > 2) {
                        new Select(tokens[1], tokens[2]).execute();
                    } else {
                        System.out.println("Error: Please specify a tag and an attribute.");
                    }
                }
                case "set" -> {
                    if (tokens.length > 3) {
                        new Set(tokens[1], tokens[2], tokens[3]).execute();
                    } else {
                        System.out.println("Error: Please specify a tag and an attribute.");
                    }
                }
                case "children" -> {
                    if (tokens.length > 1) {
                        new Children(tokens[1]).execute();
                    } else {
                        System.out.println("Error: Please specify a tag.");
                    }
                }
                case "child" -> {
                    if (tokens.length > 2) {
                        new Child(tokens[1], Integer.parseInt(tokens[2])).execute();
                    } else {
                        System.out.println("Error: Please specify a tag and an index.");
                    }
                }
                case "text" -> {
                    if (tokens.length > 1) {
                        new Text(tokens[1]).execute();
                    } else {
                        System.out.println("Error: Please specify a tag.");
                    }
                }
                case "delete" -> {
                    if (tokens.length > 2) {
                        new Delete(tokens[1], tokens[2]).execute();
                    } else {
                        System.out.println("Error: Please specify a tag and an attribute.");
                    }
                }
                case "xpath" -> {
                    System.out.println("Not implemented yet.");
                }
                case "save" -> {
                    new Save().execute();
                    System.out.println("Successfully saved " + parser.getFileName());
                }
                case "saveas" -> {
                    if (tokens.length > 1) {
                        new SaveAs(tokens[1]).execute();
                        System.out.println("Successfully saved " + parser.getFileName());
                    } else {
                        System.out.println("Error: Please specify a file name.");
                    }
                }
                case "close" -> {
                    new Close().execute();
                    System.out.println("Successfully closed " + parser.getFileName());
                }
                default -> {
                    System.out.println("Unknown command: " + action);
                }
            }
        }
    }
}
