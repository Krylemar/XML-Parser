package cli.commands;

import cli.GenericCommand;

public class SaveAs extends GenericCommand {
    private final String filePath;

    public SaveAs(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        this.getParser().saveAs(filePath);
    }
}
