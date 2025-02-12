package api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public static String getProperty(String key) {
        Properties properties = new Properties();

        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
