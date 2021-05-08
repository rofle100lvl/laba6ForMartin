package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.HeadDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды вывода первого элемента коллекции
 */
public class HeadCommand extends AbstractCommand {
    public HeadCommand(CollectionManager collectionManager) {
        super("head", "Вывод первого элемента коллекции", collectionManager);
    }


    @Override
    public Response execute(CommandDescription commandDescription) {
        HeadDescription headDescription = (HeadDescription) commandDescription;
        return new Response(200, getCollectionManager().head());
    }
}