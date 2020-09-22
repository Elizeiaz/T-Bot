import java.util.HashMap;


public class CommandHandler {
    //Экземпляр класса всех команд
    //!Криво!, как улучшить!
    Commands commands = new Commands();

    //Словарь (название метода, сам метод)
    private HashMap<String, Object> myDict = new HashMap<>();

    //Конструктор, создающий базовые команды
    public CommandHandler(){
        //!Вроде можно как-то улучшить!
        set("/start", commands.getCommand(commands.start()));
        set("/help", commands.getCommand(commands.help()));
    }

    private Object get(String key) {
        return this.myDict.get(key);
    }

    private void set(String key, Object value){
        this.myDict.put(key, value);
    }
     //Метод, возвращающий запрашиваемую команду
    //!Надо сделать проверку на существование команды!
    public Object doCommand(String command){
        return get(command);
    }
    //Метод, добавляющий в словарь новую команду
    void newCommand(String commandName, Object commandMethod){
        set(commandName, commandMethod);
    }

}
