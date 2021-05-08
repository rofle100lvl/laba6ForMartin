package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.ExitDescription;
import utils.CollectionManager;
import utils.Parser;
import utils.Response;

/**
 * Класс команды выхода из программы
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand(CollectionManager collectionManager) {
        super("exit", "Завершение программы", collectionManager);
    }


    @Override
    public Response execute(CommandDescription commandDescription) {
        ExitDescription exitDescription = (ExitDescription) commandDescription;
        Parser.convertObjectToXml(getCollectionManager(), "collection.txt");
        System.out.println(exitDescription.getName().name());
        return new Response(200,"ExitCommand");
    }
}