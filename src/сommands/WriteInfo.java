package сommands;

import com.sun.tools.javac.Main;
import сore.User;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteInfo implements Command{
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    User user;

    @Override
    public String execute() {
        if (user.getTmpUserInfo() == null){
            user.isUserCommandEnded = false;
            return "Введите данные по типу: ключ=значение";
        } else {
            pillUserInfo();
            return "Информация успешно добавлена";
        }
    }

    public WriteInfo(User user){
        this.user = user;
    }

    public void pillUserInfo() {
        String[] keyAndValue = user.getTmpUserInfo().split("=");
        try {
            this.user.addUserInfo(keyAndValue[0], keyAndValue[1]);
        } catch (Exception e) {
            logger.log(Level.INFO, "Неккоректный ввод данных", e);
        }

        logger.fine(this.user.getUserInfo().toString());
        this.user.saveUserInfo();
    }
}
