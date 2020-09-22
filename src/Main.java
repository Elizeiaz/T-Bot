import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        BotCore.output(commandHandler.doCommand("/help"));
    }
}
