package cli.commands;

import cli.GenericCommand;

public class Close extends GenericCommand {

    @Override
    public void execute() {
        this.getParser().close();
    }
}
