package сommands;


import java.util.HashMap;

public class CommandHandler {
    private HashMap<String, Command> commandDict = new HashMap<>();
    private String message;

    public CommandHandler() {
        this.commandDict.put("/start", new Start());
        this.commandDict.put("/help", new Help());
        this.commandDict.put("unexpectedCommand", new unexpectedCommand());
        this.commandDict.put("/weather", new Weather());
    }

    public void doCommand(String curCommand) {
        try {
            this.message = commandDict.get(curCommand).execute();
        }
        catch (Exception e) {
            this.message = commandDict.get("unexpectedCommand").execute();
        }

    }

    public String commandMessage() {
        return message;
    }
}
