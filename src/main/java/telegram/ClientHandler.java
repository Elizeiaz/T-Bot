package telegram;

import com.sun.tools.javac.Main;
import commands.CommandHandler;
import core.User;
import core.UserStateEnum;

import java.util.HashMap;
import java.util.logging.Logger;

public class ClientHandler {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private HashMap<Integer, User> usersDict = new HashMap<>();
    private int maxCountUsers;

    public ClientHandler() {
        this.maxCountUsers = 30;
    }

    public User selectUser(int userId) {
        addUser(userId);
        clearUsersDict();
        return usersDict.get(userId);
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
            int countForDelete = usersDict.size() - maxCountUsers;
            if (countForDelete <= 0) {
                countForDelete = 0;
            }

            for (int i = 0; i < countForDelete; i++){
                if (usersDict.get(users[countForDelete]).userStateEnum != UserStateEnum.ENDED){
                    usersDict.remove(users[countForDelete]);
                    countForDelete += 1;
                }
            }
        }
    }

    public String executeCommand(int userId, ParseUserMessage userMassage) {
        User user = selectUser(userId);
        CommandHandler commandHandler = new CommandHandler(user);
        if (user.userStateEnum == UserStateEnum.ENDED) {
            user.userState.setLastCommand(userMassage.getMessage());
        } else {
            user.userState.setTmpUserInfo(userMassage.getMessage());
        }

        return commandHandler.doCommand(user.userState.getLastCommand());
    }
}
