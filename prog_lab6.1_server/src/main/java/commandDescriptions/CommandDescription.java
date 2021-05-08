package commandDescriptions;

import support.CommandName;

import java.io.Serializable;

public abstract class CommandDescription implements Serializable {
    private static final long serialVersionUID = -7879056808897113735L;
    private final CommandName name;


    public CommandDescription(CommandName name) {
        this.name = name;
    }

    public CommandName getName() {
        return name;
    }


}
