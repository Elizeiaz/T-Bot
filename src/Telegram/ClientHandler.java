package Telegram;

import com.sun.tools.javac.Main;
import commands.CommandHandler;
import сore.User;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class ClientHandler {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private final User user;
    private final CommandHandler commandHandler;
    private final ParseUserMassage userMessage;

    public ClientHandler(User user, ParseUserMassage message) {
        this.userMessage = message;
        this.user = new User(message.getUserId());
        this.commandHandler = new CommandHandler(user);
    }

    public String executeCommand() {
        logger.info(String.valueOf(user.userState.getUserState()));
        if (user.userState.getUserState() == 0) {
            user.userState.setLastCommand(userMessage.getMessage());
        } else {
            logger.info("!!!!!!!!!!!");
            user.userState.setTmpUserInfo(userMessage.getMessage());
        }

        return commandHandler.doCommand(user.userState.getLastCommand());
    }
}
