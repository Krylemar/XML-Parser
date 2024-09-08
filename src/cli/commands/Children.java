package cli.commands;

import cli.GenericCommand;

public class Children extends GenericCommand {
    private final String id;

    public Children(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        this.getParser().listChildren(id);
    }
}
