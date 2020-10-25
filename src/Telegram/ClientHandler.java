package Telegram;

import com.sun.tools.javac.Main;
import commands.CommandHandler;
import core.User;

import java.util.HashMap;
import java.util.logging.Logger;

public class ClientHandler {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private HashMap<Integer, User> usersDict = new HashMap<>();
    private User user;
    private int maxCountUsers;
//    private final CommandHandler commandHandler;
//    private final ParseUserMassage userMessage;

    public ClientHandler() {
        this.maxCountUsers = 30;
//        this.userMessage = message;
//        this.user = new User(message.getUserId());
//        this.commandHandler = new CommandHandler(user);
    }

    public void selectUser(int userId) {
        addUser(userId);
        clearUsersDict();
        this.user = usersDict.get(userId);
    }

    public void addUser(int userId) {
        if (!usersDict.containsKey(userId)){
            usersDict.put(userId, new User(userId));
        }
    }

    public void changeCountUsers(int count) {
        if (count > 10) {
            this.maxCountUsers = count;
        }
    }

    private void clearUsersDict() {
        if (usersDict.size() > this.maxCountUsers && !usersDict.isEmpty()) {
            Integer[] users = usersDict.keySet().toArray(new Integer[usersDict.size()]);
            int countForDelete = 0;

            while (countForDelete < usersDict.size() && countForDelete < maxCountUsers){
                if (usersDict.get(users[countForDelete]).userState.getUserState() != 0){
                    usersDict.remove(users[countForDelete]);
                    countForDelete += 1;
                }
            }
        }
    }

    public String executeCommand(ParseUserMassage userMassage) {
        CommandHandler commandHandler = new CommandHandler(this.user);
        if (user.userState.getUserState() == 0) {
            user.userState.setLastCommand(userMassage.getMessage());
        } else {
            user.userState.setTmpUserInfo(userMassage.getMessage());
        }

        return commandHandler.doCommand(user.userState.getLastCommand());
    }
}
