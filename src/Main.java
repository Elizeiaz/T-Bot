import сommands.CommandHandler;
import сore.FileHandler;
import сore.Paths;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandHandler command = new CommandHandler();
        FileHandler fileHandler = new FileHandler();
        String curCommand = "/start";

        while (!curCommand.equals("/exit")) {
            command.doCommand(curCommand);
            output(command.commandMessage());
            curCommand = input();
        }
    }

    public static String input() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void output(String message) {
        System.out.println(message);
    }
}

