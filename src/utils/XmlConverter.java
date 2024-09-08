package utils;

import parser.Node;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class XmlConverter {
    private static Node root;
    public static Node deserialize(String xmlString) {
        XmlValidate.validate(xmlString);
        Stack<Node> nodeStack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new StringReader(xmlString))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("<?") || line.startsWith("<!")) {
                    // Ignore XML declarations and DTDs
                    continue;
                }
                if (line.contains("<!--") && line.contains("-->")) {
                    // Ignore comments
                    continue;
                }

                if (line.startsWith("</")) {
                    // Closing tag - pop the stack
                    nodeStack.pop();
                } else if (line.startsWith("<")) {
                    boolean isSelfClosing = line.endsWith("/>");
                    boolean isTagWithContent = line.contains("</");

                    int tagEndIndex = line.indexOf(isSelfClosing ? "/>" : ">");
                    String tagContent = line.substring(1, tagEndIndex).trim();

                    // Split the tag content into parts to extract the tag name and attributes
                    String[] parts = tagContent.split("\\s+", 2);
                    String tagName = parts[0];

                    Map<String,String> attributeMap = new HashMap<>();
                    // Parse attributes if any
                    if (parts.length > 1) {
                        String attributesPart = parts[1];
                        String[] attributes = attributesPart.split("\\s+");
                        for (String attribute : attributes) {
                            String[] keyValue = attribute.split("=");
                            if (keyValue.length == 2) {
                                String key = keyValue[0];
                                String value = keyValue[1].replace("\"", "");
                                attributeMap.put(key, value);
                            }
                        }
                    }
                    Node node;
                    if (attributeMap.containsKey("id")){
                        node = new Node(tagName, attributeMap.get("id"));
                        attributeMap.remove("id");
                        node.setAttributes(attributeMap);
                    }else {
                        node = new Node(tagName);
                        node.setAttributes(attributeMap);
                    }



                    if (!nodeStack.isEmpty()) {
                        nodeStack.peek().addChild(node);
                    } else {
                        root = node;
                    }

                    if (isSelfClosing) {
                        // Do nothing, as the node is complete.
                    } else if (isTagWithContent) {
                        // Extract content and close tag on the same line
                        String content = line.substring(tagEndIndex + 1, line.indexOf("</")).trim();
                        node.setContent(content);
                    } else {
                        // Push to stack for nested content
                        nodeStack.push(node);
                    }
                } else {
                    // Content between tags
                    if (!nodeStack.isEmpty() && nodeStack.peek().getContent() != null) {
                        // Multiline content processing for 2+ lines
                        Node currentNode = nodeStack.pop();
                        String content = currentNode.getContent();
                        currentNode.setContent(content + " " + line);
                        nodeStack.push(currentNode);
                    } else if (!nodeStack.isEmpty()){ //First line of content
                        Node currentNode = nodeStack.peek();
                        currentNode.setContent(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}
