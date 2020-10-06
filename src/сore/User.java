package сore;

import java.util.HashMap;

public class User {
    private HashMap<String, String> userDictInfo = new HashMap<>();

    public User(String id){
        this.userDictInfo.put("id", id);
    }
}
