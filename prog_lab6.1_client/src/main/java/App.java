import commandDescriptions.CommandDescription;
import exceptions.ArgumentsCountException;
import exceptions.LimitOfReconnectionsException;
import exceptions.UnknownCommandNameException;
import support.CommandName;
import utils.Response;
import utils.UserAsker;

import java.io.*;
import java.net.UnknownHostException;
import java.util.Stack;

public class App {
    private final CommandDescriptionFactory commandDescriptionFactory;
    private final Connector connector;
    private Response serverResponse;
    private Stack<String> stackOpenScripts = new Stack<>();
    private UserAsker userAsker;

    public App() throws LimitOfReconnectionsException, UnknownHostException {
        connector = new Connector(45001);
        userAsker = new UserAsker(new BufferedReader(new InputStreamReader(System.in)));
        commandDescriptionFactory = new CommandDescriptionFactory(userAsker);
    }
    public void receive() throws LimitOfReconnectionsException {
        serverResponse = connector.receive();
        if(serverResponse != null){
            serverResponse.printResponse();
        }
    }

    public boolean run() throws IOException, LimitOfReconnectionsException {
        while (true) {
            if(System.in.available() > 0){
                String s = userAsker.getUserScanner().readLine();
                try {
                    String[] words = splitWords(s);
                    CommandDescription commandDescription = commandDescriptionFactory.
                            getCommandDescription(words[0],words[1]);
                    if(commandDescription.getName() == CommandName.EXECUTESCRIPT){
                        execute_script(words[1]);
                        continue;
                    }
                    connector.send(commandDescription);
                    if(commandDescription.getName() == CommandName.EXIT){
                        receive();
                        return true;
                    }
                } catch (UnknownCommandNameException e) {
                    e.printStackTrace();
                } catch (ArgumentsCountException e) {
                    e.printStackTrace();
                }
            }
            receive();

        }
    }

    public String[] splitWords(String request){
        request = request.trim().replaceAll(" +", " ");
        String[] words = new String[2];
        String commandName = request.split(" ")[0];
        words[0] = commandName;
        try {
            String argument = request.split(" ")[1];
            words[1] = argument;
        }
        catch (ArrayIndexOutOfBoundsException e){
            words[1] = "";
        }
        return words;
    }

    public boolean interactive_run() throws IOException, LimitOfReconnectionsException {
        while (true) {
                String s = userAsker.getUserScanner().readLine();
                if(s == null)return true;
                try {
                    String[] words = splitWords(s);
                    if(words[0].equals("execute_script")){
                        execute_script(words[1]);
                        continue;
                    }
                    CommandDescription commandDescription = commandDescriptionFactory.
                            getCommandDescription(words[0],words[1]);
                    if(commandDescription == null)return false;
                    connector.send(commandDescription);
                } catch (UnknownCommandNameException e) {
                    e.printStackTrace();
                } catch (ArgumentsCountException e) {
                    e.printStackTrace();
                }
            receive();
        }
    }

    public boolean execute_script(String File) throws IOException, LimitOfReconnectionsException {
        if (!findCycles(File)) return false;
        System.out.println("====  Начало выполнения скрипта по адресу " + File + "  ====");
        try {
            userAsker.setUserScanner(new BufferedReader(new FileReader(File)));
            if (!interactive_run()) {
                userAsker.setUserScanner(new BufferedReader(new InputStreamReader(System.in)));
                System.out.println("В одном из файлов обнаружена ошибка. Перепроверьте скрипты.");
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден или нарушены права доступа");
        }
        System.out.println("====  Скрипт " + File + " успешно выполнен  ====\n");
        userAsker.setUserScanner(new BufferedReader(new InputStreamReader(System.in)));
        return true;
    }

    public boolean findCycles(String argument){
        try {
            if(stackOpenScripts.search(argument)!=-1){
                return false;
            }
            BufferedReader reader = new BufferedReader(new FileReader(argument));
            Boolean T = true;
            stackOpenScripts.push(argument);
            String line = new String("");
            while ((line = reader.readLine()) != null) {
                if(line.split(" ").length==2 && line.split(" ")[0].equals("execute_script")){
                    T &= findCycles(line.split(" ")[1]);
                }
            }
            stackOpenScripts.pop();
            if(!T){
                System.out.println("#############################################\nОшибка! Один или несколько скриптов зациклены.\n#############################################");
            }
            return T;

        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден или у файла выставлены неправильные прова доступа.",argument);
            return false;

        } catch (IOException e) {
            return true;
        }
    }


}
