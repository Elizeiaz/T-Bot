package Telegram;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramProvider extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        System.out.println(update.toString());
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
