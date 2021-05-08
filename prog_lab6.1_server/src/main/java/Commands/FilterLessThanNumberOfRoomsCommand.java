package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.FilterLessThanNumberOfRoomsCommandDescription;
import startClasses.Flat;
import utils.CollectionManager;
import utils.Response;

import java.util.stream.Collectors;

/**
 * Класс команды вывода всех квартир, значение поля numberOfRooms которых меньше заданного
 */
public class FilterLessThanNumberOfRoomsCommand extends AbstractCommand {
    public FilterLessThanNumberOfRoomsCommand(CollectionManager collectionManager) {
        super("filter_less_than_number_of_rooms", "Выводит элементы, значение поля numberOfRooms " +
                "которых меньше заданного", collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        FilterLessThanNumberOfRoomsCommandDescription filterLessThanNumberOfRoomsCommandDescription
                = (FilterLessThanNumberOfRoomsCommandDescription) commandDescription;
        return new Response(200,
                getCollectionManager().getFlats().stream()
                .filter(x -> x.getNumberOfRooms() < filterLessThanNumberOfRoomsCommandDescription.getNumberOfRooms())
                .map(Flat::niceToString)
                .collect(Collectors.joining("\n")));
    }
}