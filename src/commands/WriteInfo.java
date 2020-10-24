package commands;

import com.sun.tools.javac.Main;
import commands.Command;
import сore.User;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteInfo implements Command {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    User user;

    @Override
    public String execute() {
        switch (user.userState.getUserState()){
            case (0):
                user.userState.setUserState(1);
                return "Введите данные по типу: ключ=значение";
            default:
                user.userState.setUserState(0);
                pillUserInfo(user.userState.getTmpUserInfo());
                return "Информация успешно добавлена";
        }
    }

    public WriteInfo(User user){
        this.user = user;
    }

    public void pillUserInfo(String inputStr) {
        String[] keyAndValue = inputStr.split("=");
        try {
            this.user.addUserInfo(keyAndValue[0], keyAndValue[1]);
        } catch (Exception e) {
            logger.log(Level.INFO, "Неккоректный ввод данных", e);
        }

        logger.fine(this.user.getUserInfo().toString());
        this.user.saveUserInfo();
    }
}
