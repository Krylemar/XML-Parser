package parser;

import cli.Command;
import cli.CommandEnum;
import utils.XmlConverter;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class Parser {
    private static Parser instance;
    private boolean isFileOpen;
    private Node root;

    private Parser() {
        this.isFileOpen = false;
        this.root = null;
    }

    public static Parser getInstance() {
        if (instance == null)
            instance = new Parser();
        return instance;
    }

    public boolean isFileOpen() {
        return isFileOpen;
    }

    public void setFileOpen(boolean fileOpen) {
        isFileOpen = fileOpen;
    }

    public Node getRoot() {
        return root;
    }

    public void printTree() {
        System.out.println(this.root);
    }

    public void openFile(String filepath){
        System.out.println("Opening " + filepath);
        String xmlString = "";
        try {
            xmlString = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        root = XmlConverter.deserialize(xmlString);
        isFileOpen = true;
    }

    public void printFile(){
        System.out.println(root);
    }

    public void help(){
        for (CommandEnum command : CommandEnum.values()) {
            System.out.println(command);
        }
    }
}
