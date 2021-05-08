package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.ExecuteScriptDescription;
import utils.CollectionManager;
import utils.Response;

/**
 * Класс команды выполнения скрипта
 */

public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("execute_script", "Выполнение скрипта",collectionManager);
    }

    @Override
    public Response execute(CommandDescription commandDescription) {
        ExecuteScriptDescription executeScriptDescription = (ExecuteScriptDescription) commandDescription;
        System.out.println(executeScriptDescription.getName().name());
        return new Response(200,"execute_script");
    }
}