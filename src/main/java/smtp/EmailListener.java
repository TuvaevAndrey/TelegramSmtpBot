package smtp;

import java.io.InputStream;
import org.subethamail.smtp.helper.SimpleMessageListener;

public class EmailListener implements SimpleMessageListener {

    @Override
    public boolean accept(String from, String recipient) {
        return true;
    }

    @Override
    public void deliver(String from, String recipient, InputStream data) {

    }
}
