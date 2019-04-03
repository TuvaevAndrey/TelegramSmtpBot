package smtp;

import java.io.InputStream;
import org.subethamail.smtp.helper.SimpleMessageListener;
import telegram.RegistrationBot;

public class EmailListener implements SimpleMessageListener {

    private final RegistrationBot bot;

    public EmailListener(RegistrationBot bot) {
        this.bot = bot;
    }

    @Override
    public boolean accept(String from, String recipient) {
        return true;
    }

    @Override
    public void deliver(String from, String recipient, InputStream data) {
        bot.sendPayload(recipient, data);
    }
}
