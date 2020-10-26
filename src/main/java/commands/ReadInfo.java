package commands;


import com.sun.tools.javac.Main;
import core.User;

import java.util.HashMap;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class ReadInfo implements Command {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    User user;

    @Override
    public String execute() {
        return getUserInfo();
    }

    public ReadInfo(User user) {
        this.user = user;
    }

    public String getUserInfo() {
        StringJoiner outString = new StringJoiner("\n");
        HashMap<String, String> strDict = user.getUserInfo();

        for (String str : strDict.keySet()) {
            String keyAndValueStr = str + ": " + strDict.get(str);
            outString.add(keyAndValueStr);
        }

        if (outString.toString().equals("")) {
            return "Информация не найдена";
        }

        return outString.toString();
    }
}
