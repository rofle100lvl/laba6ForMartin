package Commands;

import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды, сохраняющей коллекцию в файл
 */

public class SaveCommand extends AbstractCommand {
    public SaveCommand(CollectionManager collectionManager) {

        super("save", "Сохранение коллекции в файл", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        return new Response(200,"Save");
    }
}