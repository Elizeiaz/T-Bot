import com.sun.tools.javac.Main;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.ItemConverter;
import telegram.TelegramProvider;
import webParser.Item;
import webParser.ItemCategoryEnum;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.*;

public class Bot {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    //Настройка для логгера
    static {
        Handler consoleHandler = new ConsoleHandler();
//        Handler fileHandler = null;
        logger.setUseParentHandlers(false);

        try {
//            fileHandler = new FileHandler(path.pathToLogFile);
            LogManager.getLogManager().readConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(consoleHandler);
    }

    public static void main(String[] args) {
//        List<Float> sizes = new ArrayList<>();
//        sizes.add((float) 39.5);
//        sizes.add((float) 41.5);
//        sizes.add((float) 41.5);
//        sizes.add((float) 45);
//        Item item = new Item("Yoox", "url", ItemCategoryEnum.OUTWEAR,
//                "nike", "95", "ItemUrl", "photoUrl", sizes, 4, 2);
//        ItemConverter itemConverter = new ItemConverter();
//        logger.info(itemConverter.itemToString(item));

//        for (Field field : item.getFields()) {
//            try {
//                logger.info(field.get(item));
//            } catch (Exception e) {
//                logger.info("exc");
//            }
//
//        }


//        Запуск бота
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new TelegramProvider());

            logger.info(MessageFormat.format("Telegram bot is started: {0}", new Date().toString()));
        } catch (TelegramApiException e) {
            logger.log(Level.WARNING, "Telegram start exception", e);
        }
    }
}

