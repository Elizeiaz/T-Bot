package telegram;


import com.sun.tools.javac.Main;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import core.FileWorker;
import core.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramProvider extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private ClientHandler clientHandler = new ClientHandler();
    private final FileWorker fileWorker = new FileWorker();
    private final Paths paths = new Paths();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            ParseUserMassage userMessage = new ParseUserMassage(update);
            clientHandler.selectUser(userMessage.getUserId());
            String messageFromCommand = clientHandler.executeCommand(userMessage);

            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(messageFromCommand);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                logger.log(Level.WARNING, "Warning in sending message to Telegram", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "WeLookBot";
    }

    @Override
    public String getBotToken() {
        return fileWorker.readFile(paths.pathToToken);
    }
}
