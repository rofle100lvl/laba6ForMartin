package Commands;

import commandDescriptions.AddDescription;
import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды добавления экземпляра в коллекцию
 */
public class AddCommand extends AbstractCommand {
    public AddCommand(CollectionManager collectionManager) {
        super("add", "Добавляет новый элемент в коллекцию", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        AddDescription addDescription = (AddDescription) commandDescription;
        getCollectionManager().add(addDescription.getFlat());
        System.out.println(addDescription.getFlat().niceToString());
        return new Response(201,"Элемент успешно добавлен");
    }
}
