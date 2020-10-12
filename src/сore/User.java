package сore;

import com.sun.tools.javac.Main;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public class User {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    //Нормально?
    private Paths path = new Paths();
    private FileWorker fileWorker = new FileWorker();

    private HashMap<String, String> userDictInfo = new HashMap<>();

    //Плохо ли в конструкторе подгружать инфу?
    public User(String id) {
        this.userDictInfo.put("id", id);
        createUserDir();
        uploadUserInfo();
    }

    public boolean checkUserDir() {
        File file = new File(path.pathToUsers);
        return file.exists();
    }

    public void createUserDir(){
        if (!checkUserDir()){
            File file = new File(path.pathToUsers);
            file.mkdir();
        }
    }

    public boolean checkUser(String id) {
        File file = new File(id);
        return file.exists();
    }

    public boolean createUser() {
        if (!checkUser(path.getPathToUser(userDictInfo.get("id")))) {
            fileWorker.writeFile(path.getPathToUser(userDictInfo.get("id")), "");
            return true;
        }
        return false;
    }

    private void uploadUserInfo() {
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
        fileWorker.writeFile(path.getPathToUser(userDictInfo.get("id")), userDictInfo.toString());
    }

    public void addUserInfo(String key, String value){
        userDictInfo.put(key, value);
    }

    public boolean deleteUserInfo(String key){
        if (checkUser(path.getPathToUser(userDictInfo.get("id")))){
            userDictInfo.remove(key);
            return true;
        }
        return false;
    }

    public HashMap<String, String> getUserInfo(){
        return this.userDictInfo;
    }
}
