import java.util.HashMap;


public class CommandHandler {
    //Экземпляр класса всех команд
    //!Криво!, как улучшить!
    Commands commands = new Commands();

    //Словарь (название метода, сам метод)
    private HashMap<String, Object> commandDict = new HashMap<>();

    //Конструктор, создающий базовые команды
    public CommandHandler() {
        //!Вроде можно как-то улучшить!
        set("/start", commands.getCommand(commands.start()));
        set("/help", commands.getCommand(commands.help()));
    }

    private Object get(String key) {
        return this.commandDict.get(key);
    }

    private void set(String key, Object value) {
        this.commandDict.put(key, value);
    }
    //Метод, возвращающий запрашиваемую команду

    public Object doCommand(String command) {
        if (!commandDict.containsKey(command)) {
            return commands.notExist();
        }
        return get(command);
    }

    //Метод, добавляющий в словарь новую команду
    void newCommand(String commandName, Object commandMethod) {
        set(commandName, commandMethod);
    }

}
