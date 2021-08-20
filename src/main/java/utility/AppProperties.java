package utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    private static final String FILENAME = "src/main/resources/conf.properties";
    private static final AppProperties properties_file = new AppProperties();
    private final Properties prop = new Properties();

    private AppProperties() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(FILENAME);
            prop.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static AppProperties getInstance() {
        return properties_file;
    }
}

