package cli;

public enum CommandEnum {
    OPEN ("open [FILE]","Opens a file for operations"),
    CLOSE ("close","Closes the current file"),
    SAVE ("save","Saves the current file"),
    SAVEAS ("saveas [PATH]","Saves the current file to the specified pathname"),
    HELP ("help","Prints this help message"),
    EXIT ("exit","Exits the program without saving changes"),

    PRINT ("print", "Prints the XML content of the current file"),
    SELECT ("select [ID] [KEY]","Prints the value of the attribute with the specified [ID] and [KEY]"),
    SET("set [ID] [KEY] [VALUE]","Sets the value of the attribute with the specified [ID] and [KEY]"),
    CHILDREN("children [ID] [N]","Prints the N-th child of the element with the specified [ID]"),
    TEXT("text [ID]","Prints the text of the element with the specified [ID]"),
    DELETE("delete [ID] [KEY]","Deletes the attribute with the specified [ID] and [KEY]"),
    NEWCHILD("newchild [ID]","Adds a new child to the element with the specified [ID]"),
    XPATH("xpath [ID] [XPATH]","Runs the XPath 2.0 query on the element with the specified [ID]");

    CommandEnum(String usage, String description) {
        this.usage = usage;
        this.description = description;
    }

    private final String usage;
    private final String description;

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%-30s%s", usage, description);
    }
}
