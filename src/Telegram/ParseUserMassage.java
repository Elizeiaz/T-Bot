package Telegram;

import com.sun.tools.javac.Main;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class ParseUserMassage {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private final int userId;
    private final String message;

    public ParseUserMassage(Update update){
        this.userId = update.getMessage().getFrom().getId();
        this.message = update.getMessage().getText();
        logger.fine(MessageFormat.format("User Id: {0}, User Message: {1}", this.userId, this.message));
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
