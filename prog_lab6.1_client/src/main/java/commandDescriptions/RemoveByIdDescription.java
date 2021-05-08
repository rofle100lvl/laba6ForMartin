package commandDescriptions;

import support.CommandName;

public class RemoveByIdDescription extends CommandDescription{
    long id;
    public RemoveByIdDescription(long id) {
        super(CommandName.REMOVEBYID);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
