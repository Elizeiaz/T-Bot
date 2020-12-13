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
        //test JSONParser
        Asos asos = new Asos();
        URIForParse uriForParse = new URIForParse(
                "https://www.asos.com/ru/men/tufli-botinki-i-krossovki/krossovki/cat/" +
                        "?cid=5775&nlid=mw|%D0%BE%D0%B1%D1%83%D0%B2%D1%8C|%D1%81%D0%BE%D1%80%D1" +
                        "%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D1%82%D1%8C" +
                        "+%D0%BF%D0%BE+%D1%82%D0%B8%D0%BF%D1%83+%D0" +
                        "%BF%D1%80%D0%BE%D0%B4%D1%83%D0%BA%D1%82%D0%B0",
                ItemCategoryEnum.SNEAKERS);
        asos.parse(uriForParse);
        asos.getItem(0);

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

