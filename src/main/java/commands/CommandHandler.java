package commands;


import core.User;
import core.UserStateEnum;

import java.util.HashMap;

public class CommandHandler {
    private HashMap<String, Command> commandDict = new HashMap<>();
    private User user;

    public CommandHandler(User user) {
        this.user = user;

        this.commandDict.put("/start", new Start());
        this.commandDict.put("/help", new Help());
        this.commandDict.put("UnexpectedCommand", new UnexpectedCommand());
        this.commandDict.put("/write", new WriteInfo(user));
        this.commandDict.put("/read", new ReadInfo(user));
    }

    public String doCommand(String curCommand) {
        if (user.userStateEnum == UserStateEnum.ENDED && !this.commandDict.containsKey(curCommand)) {
            return commandDict.get("UnexpectedCommand").execute();
        }
        return commandDict.get(curCommand).execute();
    }
}
