package com.ajulay.controller.util;

import java.io.IOException;
import java.io.InputStream;
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

}
