package сommands;

import сore.User;

import java.util.HashMap;
import java.util.StringJoiner;

public class ReadInfo implements Command{
    User user;

    @Override
    public String execute() {
        return getUserInfo();
    }

    public ReadInfo(User user){
        this.user = user;
    }

    public String getUserInfo(){
        StringJoiner outString = new StringJoiner("\n");
        HashMap<String, String> strDict = user.getUserInfo();
        if (strDict.keySet().toString().equals("")){
            return "Информация не найдена";
        }

        for (String str: strDict.keySet()){
            String keyAndValueStr = str + ": " + strDict.get(str);
            outString.add(keyAndValueStr);
        }

        return outString.toString();
    }
}
