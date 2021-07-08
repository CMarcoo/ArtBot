package me.thevipershow.artbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public final class Application {

    private TokenProvider tokenProvider;
    private JDA jda;

    public static void main(final @NotNull String[] args) {
        final Application application = new Application();
        try {
            application.tokenProvider = new TokenProvider("token.txt");
        } catch (IllegalArgumentException argumentException) {
            System.out.println("Something went wrong with bot token:\n");
            argumentException.printStackTrace();
            System.exit(1);
        }

        try {
            application.jda = JDABuilder.createDefault(application.tokenProvider.getToken()).build();
        } catch (LoginException e) {
            System.out.println("Something went wrong with bot login:\n");
            e.printStackTrace();
        }


    }
}
