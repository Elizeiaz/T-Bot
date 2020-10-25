import Telegram.TelegramProvider;
import com.sun.tools.javac.Main;
import commands.CommandHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import сore.User;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Scanner;
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

