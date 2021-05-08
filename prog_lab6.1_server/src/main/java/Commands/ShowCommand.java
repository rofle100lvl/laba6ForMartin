package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.ShowCommandDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды, выводящей элементы коллекции
 */

public class ShowCommand extends AbstractCommand {
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "Выводит элементы коллекции", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        ShowCommandDescription showCommandDescription = (ShowCommandDescription) commandDescription;
        return new Response(200, getCollectionManager().show());
    }
}