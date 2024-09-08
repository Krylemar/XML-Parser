package parser;

import cli.CommandEnum;
import utils.XmlConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public final class Parser {
    private static Parser instance;
    private boolean isFileOpen;
    private Node root;
    private String filename;

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

    public String getFileName() {
        return filename;
    }

    public void openFile(String filepath){
        String xmlString = "";
        try {
            xmlString = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (InvalidPathException pathException) {
            System.out.println("Invalid path");
            return;
        } catch (IOException ioException) {
            System.out.println("Error reading file");
            return;
        }
        root = XmlConverter.deserialize(xmlString);
        System.out.println("Successfully opened " + filepath);
        isFileOpen = true;
        filename = filepath;
    }

    public void printFile(){
        System.out.println(root);
    }

    public void help(){
        for (CommandEnum command : CommandEnum.values()) {
            System.out.println(command);
        }
    }

    public void save(){
        try {
            Files.write(Paths.get(filename), root.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public void select(String id, String key){
        Node node = find(id);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        System.out.println(node.getAttributes().get(key));
    }

    private Node find(String id){
        //Traverse the tree and find the node with the given id
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (id.equals(currentNode.getId())) {
                return currentNode;
            }
            queue.addAll(currentNode.getChildren());
        }

        return null;
    }

    public void set(String id, String key, String value){
        Node node = find(id);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        node.addAttribute(key, value);

    }

    public void listChildren(String id){
        //Prints the children of the node with the given id
        Node node = find(id);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        for (Node child : node.getChildren()) {
            System.out.println(child);
        }

    }

    public void child(String id, int n) {
        // Print the n-th child of node with id
        Node node = find(id);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        // n-1 because n is 1-indexed
        if (n-1 < 0 || n-1 >= node.getChildren().size()) {
            System.out.println("Invalid child index");
            return;
        }
        System.out.println(node.getChildren().get(n-1));
    }

    public void text(String id) {
        // Print the text content of the node with id
        Node node = find(id);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        System.out.println(node.getContent());
    }

    public void delete(String id, String key) {
        //Deletes the attribute with the given key of the node with the given id
        Node node = find(id);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        node.deleteAttribute(key);
    }

    public void newChild(String id) {
        //Adds a new child to the node with the given id
        Node node = find(id);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        Node child = new Node("child", id);
        node.addChild(child);
    }

    public void xpath(String path) {
        //TODO: Implement XPath
    }

    public void close(){
        root = null;
        isFileOpen = false;
    }

    public void saveAs(String filePath) {
        try {
            Files.write(Paths.get(filePath), root.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }
}
