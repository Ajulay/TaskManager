package com.ajulay.endpoint.util;

import com.ajulay.controller.util.PropertyReader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SessionSignature {

    public static String sign(@NotNull final String value) throws IOException {
        String signature = "";
        final Properties properties = PropertyReader.loadProperty();
        final String salt = properties.getProperty("secret.word");
        final Integer cycle = Integer.parseInt(properties.getProperty("secret.cycle"));
        for (int i = 0; i < cycle; i++) {
            signature += (value + salt) + new Date();
            signature = signature.hashCode() + "";
        }

        return signature;
    }

}
