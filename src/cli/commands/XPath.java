package cli.commands;

import cli.GenericCommand;

public class XPath extends GenericCommand {
    private final String path;

    public XPath(String path) {
        this.path = path;
    }

    @Override
    public void execute() {
        this.getParser().xpath(path);
    }
}
