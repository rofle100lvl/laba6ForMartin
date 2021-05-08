package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.PrintUniquePriceDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды, выводящей уникальные значения поля price всех элементов в коллекции
 */
public class PrintUniquePriceCommand extends AbstractCommand {
    public PrintUniquePriceCommand(CollectionManager collectionManager) {
        super("print_unique_price", "Выводит уникальные значения поля" +
                " price всех элементов в коллекции", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        PrintUniquePriceDescription printUniquePriceDescription = (PrintUniquePriceDescription) commandDescription;
        return new Response(200, getCollectionManager().getUniquePrice());

    }

}