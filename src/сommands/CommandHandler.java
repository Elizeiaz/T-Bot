package сommands;


import сore.User;

import java.util.HashMap;

public class CommandHandler {
    private HashMap<String, Command> commandDict = new HashMap<>();
    private String message;

    public CommandHandler(User user) {
        this.commandDict.put("/start", new Start());
        this.commandDict.put("/help", new Help());
        this.commandDict.put("unexpectedCommand", new unexpectedCommand());
        this.commandDict.put("/write", new WriteInfo(user));
        this.commandDict.put("/read", new Readinfo(user));
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
