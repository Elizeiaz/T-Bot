package сore;

import java.util.HashMap;

public class UserState {
    private HashMap<String, String> userState = new HashMap<>();

    UserState(){
        userState.put("lastCommand", null);
        userState.put("commandState", "ended");
        userState.put("tmpUserInfo", null);
    }

    public String getLastCommand() {
        return userState.get("lastCommand");
    }

    public void setLastCommand(String value) {
        userState.put("lastCommand", value);
    }

    public String getUserState() {
        return userState.get("commandState");
    }

    public void setUserState(String value) {
        userState.put("commandState", value);
    }

    public String getTmpUserInfo() {
        return userState.get("tmpUserInfo");
    }

    public void setTmpUserInfo(String value) {
        userState.put("tmpUserInfo", value);
    }
}
