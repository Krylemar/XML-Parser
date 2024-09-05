package cli;

import parser.Parser;

public abstract class GenericCommand implements Command {
    private final Parser parser = Parser.getInstance();

    protected Parser getParser() {
        return parser;
    }
}
