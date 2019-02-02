package com.ajulay.controller.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader() {
    }

    private static final String NAME = "/application.properties";

    public static Properties loadProperty() throws IOException {
        final Properties properties = new Properties();
        final InputStream inputStream = PropertyReader.class.getResourceAsStream(NAME);
        properties.load(inputStream);
        return properties;
    }

    public static class SessionSignature {

        public static String sign(@NotNull final String value) throws IOException {
            String signature = "";
            final Properties properties = loadProperty();
            final String salt = properties.getProperty("secret.word");
            final Integer cycle = Integer.parseInt(properties.getProperty("secret.cycle"));
            for (int i = 0; i < cycle; i++) {
                signature += (value + salt) + new Date();
                signature = signature.hashCode() + "";
            }

            return signature;
        }

    }
}
