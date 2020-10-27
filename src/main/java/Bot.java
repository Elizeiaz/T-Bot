import telegram.TelegramProvider;
import com.sun.tools.javac.Main;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import webParser.WebParserTest;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.*;
import java.util.Date;

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
//        String searchUrl = "https://www.yoox.com/ru/%D0%B4%D0%BB%D1%8F%20%D0%BC%D1%83%D0%B6%D1%87%D0%B8%D0%BD/%D0%BE%D0%B1%D1%83%D0%B2%D1%8C%20sale/shoponline#/dept=shoesmensl&gender=U&page=1&attributes=%7b%27ctgr%27%3a%5b%27snkrs5%27%5d%7d&season=X";
//
//        WebParserTest webParser = new WebParserTest();
//        webParser.grepHtmlByClass(webParser.getHtmlXml(searchUrl), ".//*[@class='itemData text-center']");

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

