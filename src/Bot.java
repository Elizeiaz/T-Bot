import com.sun.tools.javac.Main;
import сommands.CommandHandler;
import сore.FileWorker;
import сore.Paths;
import сore.User;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import java.io.File;

public class Bot {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final Paths path = new Paths();

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
        CommandHandler command = new CommandHandler();
        FileWorker fileWorker = new FileWorker();
        User user = new User(input());

        logger.info("hello");
//        user.addUserInfo("City", input());
//        user.saveUserInfo();

//        String curCommand = "/start";
//        while (!curCommand.equals("/exit")) {
//            command.doCommand(curCommand);
//            output(command.commandMessage());
//            curCommand = input();
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

