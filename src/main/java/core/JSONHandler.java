package core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import webParser.Item;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONHandler {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    ObjectMapper mapper = new ObjectMapper();
    JsonFactory jsonFactory = new JsonFactory();


    public String objectToJson(Object objectName) {
        String jsonObject = null;
        try {
            jsonObject = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectName);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Can't serialize object to JSON", e);
        }

        return jsonObject;
    }

    public Object jsonToObject(String jsonString, Object objectName) {
        Object newObjectFromJSON = null;
        try {
            newObjectFromJSON = mapper.readValue(jsonString, Item.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Can't realize JSON to object");
        }

        return newObjectFromJSON;
    }

    public HashMap<String, String> readJSON(String JSONString) {
        HashMap<String, String> JSONDict = new HashMap<>();
        try {
            JsonParser jsonParser = jsonFactory.createParser(JSONString);
            jsonParser.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            jsonParser.nextToken();

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                jsonParser.nextToken();
                JSONDict.put(jsonParser.getCurrentName(), jsonParser.getText());
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Can't read JSONString", e);
        }

        return JSONDict;
    }
}
