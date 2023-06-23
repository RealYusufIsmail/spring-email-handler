package io.github.realyusufismail.springemailhandler.env;

import io.github.realyusufismail.jconfig.JConfig;
import io.github.realyusufismail.jconfig.classes.JConfigImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {
    public static JConfig config = null;

    public JsonConfig() {
        config = JConfig.build();

        config.getEntries().forEach();
    }
}
