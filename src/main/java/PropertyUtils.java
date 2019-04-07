import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    private static final String FILE_NAME = "application.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = PropertyUtils.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
