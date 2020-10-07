package сore;

import java.io.File;
import java.util.HashMap;

public class User {
    //Нормально?
    private Paths path = new Paths();
    private FileWorker fileWorker = new FileWorker();

    private HashMap<String, String> userDictInfo = new HashMap<>();

    //Плохо ли в конструкторе подгружать инфу?
    public User(String id) {
        this.userDictInfo.put("id", id);
        uploadUserInfo();
    }

    public boolean checkUser(String id) {
        File file = new File(id);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public boolean createUser(String id) {
        if (!checkUser(path.getPathToUser(userDictInfo.get("id")))) {
            fileWorker.writeFile(id, "");
            return true;
        }
        return false;
    }

    public void uploadUserInfo() {
        if (!checkUser(path.getPathToUser(userDictInfo.get("id")))) {
            return;
        }

        String userInfo;

        try {
            userInfo = fileWorker.readFile(path.getPathToUser(userDictInfo.get("id")));
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        userInfo = userInfo.substring(1, userInfo.length() - 1);
        String[] splitedStr = userInfo.split(", ");
        for (String str: splitedStr){
            String[] keyAndValue = str.split("=");
            userDictInfo.put(keyAndValue[0], keyAndValue[1]);
        }
    }

    public void saveUserInfo() {
        if (!checkUser(path.getPathToUser(userDictInfo.get("id")))){
            createUser(path.getPathToUser(userDictInfo.get("id")));
        }
        fileWorker.writeFile(path.getPathToUser(userDictInfo.get("id")), userDictInfo.toString());
    }

    public boolean addUserInfo(String key, String value){
        if (checkUser(path.getPathToUser(userDictInfo.get("id")))){
            userDictInfo.put(key, value);
            return true;
        }
        return false;
    }

    public boolean deleteUserInfo(String key){
        if (checkUser(path.getPathToUser(userDictInfo.get("id")))){
            userDictInfo.remove(key);
            return true;
        }
        return false;
    }
}
