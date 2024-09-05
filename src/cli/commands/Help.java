package cli.commands;

import cli.Command;
import cli.GenericCommand;

public class Help extends GenericCommand {
    @Override
    public void execute() {
        this.getParser().help();
    }
}
