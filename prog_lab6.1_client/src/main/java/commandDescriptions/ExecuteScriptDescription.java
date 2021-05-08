package commandDescriptions;

import support.CommandName;

public class ExecuteScriptDescription extends CommandDescription
{
    String file;
    public ExecuteScriptDescription(String file) {
        super(CommandName.EXECUTESCRIPT);
        this.file = file;
    }
}
