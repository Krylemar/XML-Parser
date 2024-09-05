package parser;

import java.util.*;
import java.util.regex.Pattern;

// Node
public class Node {
    private List<Node> children;
    private String name;
    private String id;
    private Map<String, String> attributes;
    private String content;

    public Node(String name) {
        this.name = name;
        //case: tag doesn't have an id
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();

        this.children = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    public Node(String name, String id) {
        this.name = name;
        this.id = id;

        this.children = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void deleteAttribute(String key) {
        attributes.remove(key);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // private function with tracking of the depth is required in order to be able to add proper indentation
    @Override
    public String toString() {
        return toString(0, true); // Start with level 0 and set isRoot to true
    }


    private String toString(int indentLevel, boolean isRoot) {
        StringBuilder sb = new StringBuilder();

        // Add indentation for current level if not root
        if (!isRoot) {
            sb.append("\t".repeat(Math.max(0, indentLevel)));
        }

        // Append the opening tag
        sb.append("<").append(name);
        if (!id.isEmpty() && !isIdAnUUID()) {
            sb.append(" id=\"").append(id).append("\"");
        }
        for (String attribute : attributes.keySet()) {
            sb.append(" ").append(attribute).append("=\"").append(attributes.get(attribute)).append("\"");
        }
        sb.append(">");

        // Append content if available
        if (content != null) {
            sb.append(content);
        }

        // Check if the node has children
        boolean hasChildren = !children.isEmpty();

        if (hasChildren) {
            sb.append("\n"); // Newline before children start
            for (int i = 0; i < children.size(); i++) {
                Node child = children.get(i);
                sb.append(child.toString(indentLevel + 1, false)); // Increase indentation level
                // Add newline after each child except the last one
                if (i < children.size() - 1) {
                    sb.append("\n");
                }
            }
            // Add closing tag with proper indentation
            sb.append("\n");
            sb.append("\t".repeat(Math.max(0, indentLevel)));
            sb.append("</").append(name).append(">");
        } else {
            // If no children, simply append the closing tag on the same line
            sb.append("</").append(name).append(">");
        }

        // Ensure a newline after the closing tag only if it's the root
        if (isRoot) {
            sb.append("\n");
        }

        return sb.toString();
    }



    private boolean isIdAnUUID(){
        return Pattern.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", this.id);
    }
}
