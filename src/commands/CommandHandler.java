package commands;


import сore.User;

import java.util.HashMap;

public class CommandHandler {
    private HashMap<String, Command> commandDict = new HashMap<>();

    public CommandHandler(User user) {
        this.commandDict.put("/start", new Start());
        this.commandDict.put("/help", new Help());
        this.commandDict.put("UnexpectedCommand", new UnexpectedCommand());
        this.commandDict.put("/write", new WriteInfo(user));
        this.commandDict.put("/read", new ReadInfo(user));
    }

    public String doCommand(String curCommand) {
        if (this.commandDict.containsKey(curCommand)) {
            return commandDict.get(curCommand).execute();
        } else {
            return commandDict.get("UnexpectedCommand").execute();
        }
    }
}
