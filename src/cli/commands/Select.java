package cli.commands;

import cli.GenericCommand;

public class Select extends GenericCommand {
    private final String path;
    private final String key;

    public Select(String path, String key) {
        this.path = path;
        this.key = key;
    }

    @Override
    public void execute() {
        this.getParser().select(path, key);
    }
}
