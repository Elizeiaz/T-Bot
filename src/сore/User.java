package сore;

import java.io.File;
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
}
