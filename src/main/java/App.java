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

        SimpleMessageListenerAdapter adapter = new SimpleMessageListenerAdapter(
            Collections.singletonList(new EmailListener()));
        SMTPServer smtpServer = new SMTPServer(adapter);
        smtpServer.start();

        ApiContextInitializer.init();

        String token = "889002123:AAHplyrpTt0GvP2EEY2AbN_Ho6tgFwi4LM0";
        String name = "SmtpProxyBot";

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        RegistrationBot bot = new RegistrationBot(token, name);
        telegramBotsApi.registerBot(bot);

    }
}
