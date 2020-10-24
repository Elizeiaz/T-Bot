import Telegram.TelegramProvider;
import com.sun.tools.javac.Main;
import commands.CommandHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import сore.User;

import java.io.IOException;
import java.util.Scanner;
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
        User user = new User("user");
        CommandHandler command = new CommandHandler(user);
        

        user.userState.setLastCommand("/start");

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new TelegramProvider());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
//        while (!user.userState.getLastCommand().equals("/exit")) {
//            output(command.doCommand(user.userState.getLastCommand()));
//
//            if (user.userState.getUserState() == 0){
//                user.userState.setLastCommand(input());
//            } else {
//                user.userState.setTmpUserInfo(input());
//            }
//        }
    }

    public static String input() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void output(String message) {
        System.out.println(message);
    }
}

