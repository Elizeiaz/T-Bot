import сommands.CommandHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandHandler command = new CommandHandler();
        String curCommand = "/help";

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

    public static void output(Object message) {
        System.out.println(message);
    }
}

