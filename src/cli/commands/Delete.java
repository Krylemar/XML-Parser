package cli.commands;

import cli.GenericCommand;

public class Delete extends GenericCommand {
    private final String id;
    private final String key;

    public Delete(String id, String key) {
        this.id = id;
        this.key = key;
    }

    @Override
    public void execute() {
        this.getParser().delete(id, key);
    }
}
