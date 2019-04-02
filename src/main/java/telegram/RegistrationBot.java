package telegram;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.ADMIN;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.MessageContext;

public class RegistrationBot extends AbilityBot {

    private Map<String, Set<Long>> emails = new ConcurrentHashMap<>();

    public RegistrationBot(String botToken, String botUsername) {
        super(botToken, botUsername);
    }

    public Ability registerEmail() {
        return Ability
            .builder()
            .name("register")
            .info("register email")
            .input(0)
            .locality(USER)
            .privacy(ADMIN)
            .action(this::register)
            //.post(System.out::println)
            .build();
    }

    public Ability deregisterEmail() {
        return Ability
            .builder()
            .name("deregister")
            .info("deregister email")
            .input(0)
            .locality(USER)
            .privacy(ADMIN)
            .action(this::deregister)
            //.post(System.out::println)
            .build();
    }

    @Override
    public int creatorId() {
        return 261195424;
    }

    private void register(MessageContext messageContext) {
        System.out.println("Registration of:" + messageContext.toString());
        Optional<Boolean> added = Optional.ofNullable(emails.get(messageContext.firstArg()))
            .map(it -> it.add(messageContext.chatId()));

        if (!added.isPresent()) {
            Set<Long> set = new HashSet<>();
            set.add(messageContext.chatId());
            emails.put(messageContext.firstArg(), set);
        }
    }

    private void deregister(MessageContext messageContext) {
        Optional.ofNullable(emails.get(messageContext.firstArg()))
            .ifPresent(set -> set.remove(messageContext.chatId()));
    }
}
