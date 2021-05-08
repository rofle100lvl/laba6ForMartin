package Commands;

import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.Response;

public abstract class AbstractCommand {
    /**
     * Имя команды
     */
    private final String name;

    /**
     * Описание команды
     */
    private final String description;
    /**
     * Collection manager в котором будут производится изменения
     */
    private final CollectionManager collectionManager;

    public AbstractCommand(String name,String description, CollectionManager collectionManager) {
        this.name = name;
        this.description = description;
        this.collectionManager = collectionManager;
    }
    abstract public Response execute(CommandDescription commandDescription);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}