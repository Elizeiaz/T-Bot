package telegram;

import com.sun.tools.javac.Main;
import webParser.Item;

import java.util.LinkedHashMap;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class ItemConverter {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public String itemToString(Item item) {
        StringJoiner outString = new StringJoiner("\n");
        LinkedHashMap<String, String> itemDict = item.getFieldsValue();
        for (String key : itemDict.keySet()) {
            logger.info(itemDict.get(key));
        }
        return outString.toString();
    }
}
