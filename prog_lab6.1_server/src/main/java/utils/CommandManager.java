package utils;

import Commands.*;
import commandDescriptions.CommandDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Класс CommandManager используется для работы с камандами
 */
public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;
    private List<AbstractCommand> commands = new ArrayList<>();
    private AddCommand addCommand;
    private InfoCommand infoCommand;
    private ShowCommand showCommand;
    private PrintUniquePriceCommand printUniquePrice;
    private UpdateCommand updateCommand;
    private RemoveByIdCommand removeByIdCommand;
    private ClearCommand clearCommand;
    private SaveCommand saveCommand ;
    private ExitCommand exitCommand;
    private AddIfMaxCommand addIfMaxCommand;
    private HeadCommand headCommand;
    private PrintFieldDescendingHouseCommand printFieldDescendingHouseCommand;
    private RemoveHeadCommand removeHeadCommand;
    private FilterLessThanNumberOfRoomsCommand filterLessThanNumberOfRoomsCommand;
    private ExecuteScriptCommand executeScriptCommand;
    private HelpCommand helpCommand;

    /**
     * Конструктор класса
     */
    public CommandManager(CollectionManager collectionManager){
        printFieldDescendingHouseCommand = new PrintFieldDescendingHouseCommand(collectionManager);
        addCommand = new AddCommand(collectionManager);
        infoCommand = new InfoCommand(collectionManager);
        showCommand = new ShowCommand(collectionManager);
        printUniquePrice = new PrintUniquePriceCommand(collectionManager);
        updateCommand = new UpdateCommand(collectionManager);
        removeByIdCommand = new RemoveByIdCommand(collectionManager);
        clearCommand = new ClearCommand(collectionManager);
        saveCommand = new SaveCommand(collectionManager);
        exitCommand = new ExitCommand(collectionManager);
        addIfMaxCommand = new AddIfMaxCommand(collectionManager);
        headCommand = new HeadCommand(collectionManager);
        removeHeadCommand = new RemoveHeadCommand(collectionManager);
        filterLessThanNumberOfRoomsCommand = new FilterLessThanNumberOfRoomsCommand(collectionManager);
        executeScriptCommand = new ExecuteScriptCommand(collectionManager);
        helpCommand = new HelpCommand(collectionManager);


        commands.add(addCommand);
        commands.add(executeScriptCommand);
        commands.add(printUniquePrice);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(helpCommand);
        commands.add(addIfMaxCommand);
        commands.add(exitCommand);
        commands.add(headCommand);
        commands.add(removeHeadCommand);
        commands.add(printFieldDescendingHouseCommand);
        commands.add(filterLessThanNumberOfRoomsCommand);
    }


    public Response launchCommand(CommandDescription commandDescription){
        Optional<AbstractCommand> command = commands.stream()
                                                    .filter(x->x.getName().equals(commandDescription.getName().getName()))
                                                    .findFirst();
        return command.get().execute(commandDescription);
    }

    public List<AbstractCommand> getCommands() {
        return commands;
    }

    /**
     * Выводит информацию о классе
     * @return Возвращает строку информации о классе
     */
    @Override
    public String toString() {
        return "CommandManager - класс для работы с коммандами.";
    }
}