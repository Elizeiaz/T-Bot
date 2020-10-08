package сommands;

import com.sun.tools.javac.Main;
import сore.User;
import java.util.Scanner;
import java.util.logging.Logger;

public class WriteInfo implements Command{
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    User user;

    @Override
    public String execute() {
        pillUserInfo();
        return "Информация успешно добавлена";
    }

    public WriteInfo(User user){
        this.user = user;
    }

    public void pillUserInfo() {
        Scanner scan = new Scanner(System.in);
        String[] keyAndValue = scan.nextLine().split("=");
        this.user.addUserInfo(keyAndValue[0], keyAndValue[1]);
        logger.fine(this.user.getUserInfo().toString());
        this.user.saveUserInfo();   
    }
}
