package commands;

import com.sun.tools.javac.Main;

import java.util.Random;
import java.util.logging.Logger;

public class RandomItem implements Command {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private final Random random = new Random();

    @Override
    public String execute() {
        return getRandomItem();
    }

    public String getRandomItem(){
//        UrlsForParse urlsForParse = new UrlsForParse();
//        Yoox yoox = new Yoox(urlsForParse.getYoox());
//        yoox.startParse();
//
//        int randomInt = random.nextInt(yoox.itemCount());
//        Item item = yoox.getItem(randomInt);
//        return item.itemToString();
        return null;
    }
}
