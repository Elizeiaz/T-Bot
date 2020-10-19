package сore;

import com.sun.tools.javac.Main;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.logging.Logger;

public class User {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    //Нормально?
    private final Paths path = new Paths();
    private final FileWorker fileWorker = new FileWorker();

    public String userId;
    private HashMap<String, String> userDictInfo = new HashMap<>();
    public String lastUserCommand;
    public boolean isUserCommandEnded = true;
    private String tmpUserInfo = null;

    //Плохо ли в конструкторе подгружать инфу?
    public User(String id) {
        this.userId = id;
        createUserDir();
        uploadUserInfo();
    }

    public boolean checkUserDir() {
        File file = new File(path.pathToUsers);
        return file.exists();
    }

    public void createUserDir() {
        if (!checkUserDir()) {
            File file = new File(path.pathToUsers);
            file.mkdir();
            logger.finer("User directory created");
        }
    }

    public boolean checkUser(String id) {
        File file = new File(id);
        return file.exists();
    }

    public void setTmpUserInfo(String value){
        this.tmpUserInfo = value;
    }

    public void setNullTmpUserInfo(){
        this.tmpUserInfo = null;
    }

    public String getTmpUserInfo(){
        return this.tmpUserInfo;
    }

    private void uploadUserInfo() {
        if (!checkUser(path.getPathToUser(userId))) {
            logger.finer("User info not founded");
            return;
        }

        String userInfo;
        try {
            userInfo = fileWorker.readFile(path.getPathToUser(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!userInfo.equals("")) {
            userInfo = userInfo.substring(1, userInfo.length() - 1);
            String[] splitedStr = userInfo.split(", ");
            for (String str : splitedStr) {
                String[] keyAndValue = str.split("=");
                userDictInfo.put(keyAndValue[0], keyAndValue[1]);
            }
            logger.finer("User info uploaded");
        } else {
            logger.finer("User info is empty");
        }
    }

    public void saveUserInfo() {
        fileWorker.writeFile(path.getPathToUser(userId), userDictInfo.toString());
        logger.finer("User info saved");
    }

    public void addUserInfo(String key, String value) {
        userDictInfo.put(key, value);
        logger.finer(MessageFormat.format("key: {0}, value: {1} added", key, value));
    }

//    public boolean deleteUserInfo(String key) {
//        if (checkUser(path.getPathToUser(userId))) {
//            userDictInfo.remove(key);
//            return true;
//        }
//        return false;
//    }

    public HashMap<String, String> getUserInfo() {
        return this.userDictInfo;
    }
}
