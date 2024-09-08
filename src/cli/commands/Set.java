package cli.commands;

import cli.GenericCommand;

public class Set extends GenericCommand {
    private final String id;
    private final String key;
    private final String value;

    public Set(String id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute() {
        this.getParser().set(id, key, value);
    }
}
