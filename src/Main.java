import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        Commands commands = new Commands();
        String curCommand = "/start";
        commandHandler.newCommand("/weather", commands.weather());

        while (true) {
            if (curCommand.equals("/exit")) {
                break;
            }
            BotCore.output(commandHandler.doCommand(curCommand));
            curCommand = BotCore.input();
        }

    }
}
