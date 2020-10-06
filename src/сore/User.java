package сore;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class User {
    //Нормально?
    private Paths path = new Paths();
    private FileHandler fileHandler = new FileHandler();

    private HashMap<String, String> userDictInfo = new HashMap<>();

    public User(String id) {
        this.userDictInfo.put("id", id);
    }

    public boolean checkUser() {
        File file = new File(path.getPathToUser(userDictInfo.get("id")));
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public Boolean createUser() {
        if (!checkUser()) {
            fileHandler.writeFile(path.getPathToUser(userDictInfo.get("id")));
            return true;
        }
        return false;
    }

    public void uploadUserInfo() {
        String userInfo;

        try {
            userInfo = fileHandler.readFile(path.getPathToUser(userDictInfo.get("id")));
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        userInfo = userInfo.substring(1, userInfo.length() - 1);
        String[] splitedStr = userInfo.split(", ");
        for (String str: splitedStr){
            String[] strForDict = str.substring(0, str.length() - 1).split("=", 1);
            this.userDictInfo.put(strForDict[0], strForDict[1]);
        }
    }

    public void saveUserInfo() {
        if (!checkUser()){
            createUser();
        }
        fileHandler.writeFile(path.getPathToUser(userDictInfo.get("id")), userDictInfo.toString());
    }

    public void addUserInfo(String key, String value){
        userDictInfo.put(key, value);
    }
}
