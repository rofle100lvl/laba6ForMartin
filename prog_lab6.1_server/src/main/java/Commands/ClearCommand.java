package Commands;

import commandDescriptions.ClearDescription;
import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды чистки коллекции
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "Добавляет новый элемент в коллекцию", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        ClearDescription clearDescription = (ClearDescription) commandDescription;
        Response response;
        if(getCollectionManager().clear()){
            response = new Response(200,"Коллекция успешно очищена");
        }
        else {
            response = new Response(200,"Коллекция пустая");
        }
        return response;
    }


}