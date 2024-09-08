package cli.commands;

import cli.GenericCommand;

public class Save extends GenericCommand {

    @Override
    public void execute() {
        this.getParser().save();
    }
}
