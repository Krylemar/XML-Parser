package cli.commands;

import cli.GenericCommand;

public class Child extends GenericCommand {
    private final String id;
    private final int n;

    public Child(String id, int n) {
        this.id = id;
        this.n = n;
    }

    @Override
    public void execute() {
        this.getParser().child(id, n);
    }
}
