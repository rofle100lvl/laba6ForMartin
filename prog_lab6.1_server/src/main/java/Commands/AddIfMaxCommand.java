package Commands;

import commandDescriptions.AddIfMaxDescription;
import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды добавления экземпляра в коллекцию, если он максимальный
 */
public class AddIfMaxCommand extends AbstractCommand {


    public AddIfMaxCommand(CollectionManager collectionManager) {
        super("add_if_max", "Добавляет новый элемент в коллекцию, если он максимальный", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        AddIfMaxDescription addIfMaxDescription = (AddIfMaxDescription) commandDescription;
        System.out.println(addIfMaxDescription.getName().name());
        return new Response(200,"AddIfMax");
    }
}