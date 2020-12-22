import com.sun.tools.javac.Main;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import sites.Asos;
import sites.URIForParse;
import telegram.ItemConverter;
import telegram.TelegramProvider;
import webParser.Item;
import webParser.ItemCategoryEnum;
import webParser.ItemFactory;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
//        test JSON parser
//        ItemConverter itemConverter = new ItemConverter();
//
//        //test JSONParser
//        Asos asos = new Asos();
//        URIForParse uriForParse = new URIForParse(
//                "https://www.asos.com/men/outlet/cat/?cid=27396&nlid=mw|outlet|shop+by+product",
//                ItemCategoryEnum.SNEAKERS);
//        asos.parse(uriForParse);
//        logger.info(itemConverter.itemToString(asos.getItem(0)));


//        Запуск бота
//        ApiContextInitializer.init();
//        TelegramBotsApi botsApi = new TelegramBotsApi();
//        try {
//            botsApi.registerBot(new TelegramProvider());
//
//            logger.info(MessageFormat.format("Telegram bot is started: {0}", new Date().toString()));
//        } catch (TelegramApiException e) {
//            logger.log(Level.WARNING, "Telegram start exception", e);
//        }
    }
}

