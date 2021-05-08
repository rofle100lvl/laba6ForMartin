package commandDescriptions;

import startClasses.Flat;
import support.CommandName;


public class AddDescription extends CommandDescription{
    Flat flat;

    public AddDescription(Flat flat) {
        super(CommandName.ADD);
        this.flat=flat;
    }

    public Flat getFlat() {
        return flat;
    }
}
