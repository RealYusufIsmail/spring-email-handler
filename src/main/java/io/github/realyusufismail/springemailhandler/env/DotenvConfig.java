package io.github.realyusufismail.springemailhandler.env;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    public DotenvConfig() {
        // Load the environment variables from .env
        Dotenv dotenv = Dotenv.configure().load();
        // Set the loaded environment variables as system properties
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }
}