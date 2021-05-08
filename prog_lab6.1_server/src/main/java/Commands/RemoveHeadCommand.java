package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.RemoveHeadDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды, выводящей первый элемент и удаляющей его
 */
public class RemoveHeadCommand extends AbstractCommand {
    public RemoveHeadCommand(CollectionManager collectionManager) {

        super("remove_head", "Вывод первого элемента коллекции и удаление его", collectionManager);
    }


    @Override
    public Response execute(CommandDescription commandDescription) {
        RemoveHeadDescription removeHeadDescription = (RemoveHeadDescription) commandDescription;
        return new Response(200, getCollectionManager().removeHead());
    }
}