package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private static Properties configFile = new Properties();

    static {
        String fileNameWithPath = System.getProperty("user.dir")
                .replace("\\", "/") + "/src/main/resources/config.properties";
        try {
            FileInputStream file = new FileInputStream(fileNameWithPath);
            configFile.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        return configFile.getProperty(propertyName);
    }

    public static Map<String, String> getProperties() {
        Map<String, String> map = (Map) configFile;
        return map;
    }
}
