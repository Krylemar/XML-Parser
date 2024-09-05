package cli.commands;

import cli.GenericCommand;

public class Open extends GenericCommand {
    private final String path;

    public Open(String path) {
        this.path = path;
    }

    @Override
    public void execute() {
        this.getParser().openFile(path);
    }
}
