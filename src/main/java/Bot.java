import com.sun.tools.javac.Main;
import sites.Yoox;


import java.io.IOException;
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
//        String searchUrl = "https://www.farfetch.com/ru/shopping/men/shoes-2/items.aspx";
//        Farfetch farfetch = new Farfetch();
//        farfetch.startParse();
        Yoox yoox = new Yoox();
        yoox.startParse();


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

