package support;

import exceptions.UnknownCommandNameException;

public enum CommandName {
    HELP("help"),
    INFO("info"),
    FILTERLESSTHANNUMBEROFROOMS("filter_less_than_number_of_rooms"),
    SHOW("show"),
    ADD("add"),
    UPDATEID("update_id"),
    REMOVEBYID("remove_by_id"),
    CLEAR("clear"),
    EXECUTESCRIPT("execute_script"),
    EXIT("exit"),
    HEAD("head"),
    REMOVEHEAD("remove_head"),
    ADDIFMAX("add_if_max"),
    PRINTUNIQUEPRICE("print_unique_price"),
    PRINFFIELDDESCENDINGHOUSE("print_field_descending_house");


    private final String name;

    public String getName() {
        return name;
    }

    CommandName(String name) {
        this.name = name;
    }

    public static CommandName findByName(String name) throws UnknownCommandNameException {
        for (CommandName commandName :
                values()) {
            if (commandName.name.equals(name)) return commandName;
        }
        throw new UnknownCommandNameException();
    }
}
