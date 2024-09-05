package cli.commands;

import cli.GenericCommand;
import parser.Parser;

public class Print extends GenericCommand {
    @Override
    public void execute() {
        this.getParser().printFile();
    }
}
