package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.HelpCommandDescription;
import utils.CollectionManager;
import utils.Response;

import java.util.stream.Collectors;

public class HelpCommand extends AbstractCommand{
    public HelpCommand(CollectionManager collectionManager) {
        super("help", "Выводит справку о командах", collectionManager);
    }


    @Override
    public Response execute(CommandDescription commandDescription) {
        HelpCommandDescription helpCommandDescription = (HelpCommandDescription) commandDescription;
        return new Response(200, getCollectionManager().getCommandManager().getCommands().stream()
                .map(c -> c.getName() + " " + c.getDescription())
                .collect(Collectors.joining("\n")));
    }
}
