import com.sun.tools.javac.Main;
import sites.URIForParse;
import sites.Yoox;
import webParser.ItemCategoryEnum;


import java.io.IOException;
import java.util.ArrayList;
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
        List<URIForParse> uris = new ArrayList<>();
        URIForParse uri = new URIForParse(
                "https://www.yoox.com/RU/shoponline?dept=samplesaleman&gender=U&page=1&clientabt=SmsMultiChannel_ON%2CSrRecommendations_ON%2CRecentlyViewed_ON%2CRecentlyViewedItemPage_ON%2CmyooxNew_ON%2CImageFormatB_ON%2COnePageCheckout_ON",
                ItemCategoryEnum.SNEAKERS
        );
        uris.add(uri);

        Yoox yoox = new Yoox();
        yoox.startParse(uris);

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

