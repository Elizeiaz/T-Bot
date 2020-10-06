package сommands;


import java.util.HashMap;

public class CommandHandler {
    private HashMap<String, Command> commandDict = new HashMap<>();
    private String message;

    public CommandHandler() {
        this.commandDict.put("/help", new Help());
        this.commandDict.put("/weather", new Weather());
        this.commandDict.put("/other", new OtherCommand());
    }

    public void doCommand(String curCommand) {
        try {
            this.message = commandDict.get(curCommand).execute();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String commandMessage() {
        return message;
    }
}
