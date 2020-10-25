package commands;

import com.sun.tools.javac.Main;
import core.User;
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
                if (!pillUserInfo(user.userState.getTmpUserInfo())){
                    return "Неккоректный ввод данных\nВведите данные по типу: ключ=значение";
                }
                user.userState.setUserState(0);
                return "Информация успешно добавлена";
        }
    }

    public WriteInfo(User user){
        this.user = user;
    }

    public boolean pillUserInfo(String inputStr) {
        String[] keyAndValue = inputStr.split("=");
        try {
            this.user.addUserInfo(keyAndValue[0], keyAndValue[1]);
        } catch (Exception e) {
            logger.log(Level.FINE, "Неккоректный ввод данных", e);
            return false;
        }

        logger.fine(this.user.getUserInfo().toString());
        this.user.saveUserInfo();
        return true;
    }
}
