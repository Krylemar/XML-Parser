package cli.commands;

import cli.GenericCommand;

public class Text extends GenericCommand {
    private final String id;

    public Text(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        this.getParser().text(id);
    }
}
