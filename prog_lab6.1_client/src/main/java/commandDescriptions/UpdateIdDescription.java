package commandDescriptions;

import startClasses.Flat;
import support.CommandName;

public class UpdateIdDescription extends CommandDescription{
    Flat flat;
    Long id;

    public UpdateIdDescription(Flat flat,Long id) {
        super(CommandName.UPDATEID);
        this.flat=flat;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Flat getFlat() {
        return flat;
    }
}
