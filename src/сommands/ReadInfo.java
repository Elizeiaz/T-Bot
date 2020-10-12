package сommands;

import сore.User;

import java.util.HashMap;

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
        StringBuilder outString = new StringBuilder();
        HashMap<String, String> strDict = user.getUserInfo();

        for (String str: strDict.keySet()){
            outString.append(str).append(": ").append(strDict.get(str)).append("\n");
        }
        outString.delete(outString.length()-1, outString.length());
        return outString.toString();
    }
}
