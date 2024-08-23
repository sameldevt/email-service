package br.com.samiac.emailservice.env;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
	private static Dotenv dotenv;
    public static final String botEmail;
    public static final String botPass;
    
    static {
        dotenv = Dotenv.load();
        botEmail = dotenv.get("BOT_EMAIL");
        botPass = dotenv.get("BOT_PASS");
    }
}
