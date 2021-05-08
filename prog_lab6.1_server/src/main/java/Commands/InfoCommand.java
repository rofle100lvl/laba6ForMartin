package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.InfoCommandDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды выхода информации о коллекции
 */
public class InfoCommand extends AbstractCommand{
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "Выводит информацию о коллекции", collectionManager);
    }


    @Override
    public Response execute(CommandDescription commandDescription) {
        InfoCommandDescription infoCommandDescription = (InfoCommandDescription) commandDescription;
        return new Response(200, getCollectionManager().getInfo());
    }
}
