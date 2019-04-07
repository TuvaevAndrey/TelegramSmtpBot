import java.util.Collections;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import smtp.EmailListener;
import telegram.RegistrationBot;

public class App {

    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();

        String token = PropertyUtils.getProperty("token");
        String name = PropertyUtils.getProperty("name");

        // run telegram bot
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        RegistrationBot bot = new RegistrationBot(token, name);
        telegramBotsApi.registerBot(bot);

        // run smtp server
        SimpleMessageListenerAdapter adapter = new SimpleMessageListenerAdapter(
            Collections.singletonList(new EmailListener(bot)));
        SMTPServer smtpServer = new SMTPServer(adapter);
        smtpServer.start();
    }
}
