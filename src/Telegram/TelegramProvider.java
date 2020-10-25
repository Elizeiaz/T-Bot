package Telegram;


import com.sun.tools.javac.Main;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import сore.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramProvider extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    User user;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            ParseUserMassage userMessage = new ParseUserMassage(update);
            ClientHandler clientHandler = new ClientHandler(this.user, userMessage);
            String messageFromCommand = clientHandler.executeCommand();

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
        return "1003156865:AAGHW-MmBXGKumExOj_ZVWvQELPpT6zaISU";
    }
}
