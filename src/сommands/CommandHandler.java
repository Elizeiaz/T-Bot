package сommands;


import сore.User;

import java.util.HashMap;

public class CommandHandler {
    private HashMap<String, Command> commandDict = new HashMap<>();
    private String message;

    public CommandHandler(User user) {
        this.commandDict.put("/start", new Start());
        this.commandDict.put("/help", new Help());
        this.commandDict.put("UnexpectedCommand", new UnexpectedCommand());
        this.commandDict.put("/write", new WriteInfo(user));
        this.commandDict.put("/read", new ReadInfo(user));
    }

    public void doCommand(String curCommand) {
        if (this.commandDict.containsKey(curCommand)){
            this.message = commandDict.get(curCommand).execute();
        } else {
            this.message = commandDict.get("UnexpectedCommand").execute();
        }
    }

    public String commandMessage() {
        return message;
    }
}
