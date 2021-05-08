package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.RemoveByIdDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды, удаляющей элемент с заданным id
 */
public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "Удаляет элемент с заданным id", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        RemoveByIdDescription removeByIdDescription = (RemoveByIdDescription) commandDescription;
        Response response;
        if(getCollectionManager().removeId(removeByIdDescription.getId()))
            response = new Response(200,"Элемент успешно удалён.");
        else response = new Response(200,"Элемент с данным id отсутствует в коллекции");
        return response;
    }


}