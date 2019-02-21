package com.tajawal.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private static Properties properties;

    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                InputStream stream = ConfigProperties.class.getResourceAsStream("/config.properties");
                properties.load(stream);
            } catch (IOException e) {
            }
        }
        return properties;
    }

    private static String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    public static String getPublicUrl() {
        return getProperty("api-com-url");
    }

   }
