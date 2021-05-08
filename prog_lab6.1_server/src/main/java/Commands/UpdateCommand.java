package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.UpdateIdDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды, обновляещей значение элемента коллекции, id которого равен заданному
 */

public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "Обновляет значение элемента коллекции, id" +
                " которого равен заданному", collectionManager);

    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        UpdateIdDescription updateIdDescription = (UpdateIdDescription) commandDescription;
        return new Response(200, getCollectionManager().updateId(updateIdDescription.getId(),
                updateIdDescription.getFlat()));
    }
}


