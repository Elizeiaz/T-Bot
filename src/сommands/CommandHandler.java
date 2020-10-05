package сommands;


import java.util.HashMap;

public class CommandHandler {
    private HashMap<String, Command> commandDict = new HashMap<>();
    private String message;

    public CommandHandler() {
        this.commandDict.put("/help", new OtherCommand());
        this.commandDict.put("/weather", new Weather());
        this.commandDict.put("/other", new OtherCommand());
    }

    public void doCommand(String curCommand) {
        try {
            this.message = commandDict.get(curCommand).execute();
        }
        catch (Exception e) {
            System.out.println("!Несуществующая команда!\n" + e);
        }

    }

    public String commandMessage() {
        return message;
    }
}
