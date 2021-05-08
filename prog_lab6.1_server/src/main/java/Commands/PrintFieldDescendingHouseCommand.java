package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.PrintFieldDescendingDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды, выводящей значения поля house всех элементов в порядке убывания
 */

public class PrintFieldDescendingHouseCommand extends AbstractCommand {
    public PrintFieldDescendingHouseCommand(CollectionManager collectionManager) {
        super("print_field_descending_house", "Выводит значения поля house всех элементов в " +
                "порядке убывания", collectionManager);
    }


    @Override
    public Response execute(CommandDescription commandDescription) {
        PrintFieldDescendingDescription printFieldDescendingHouseCommand =
                (PrintFieldDescendingDescription) commandDescription;
        getCollectionManager().sort();

        return new Response(200, getCollectionManager().getFieldDescendingHouse());
    }
}