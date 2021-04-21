import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Utlis {
    static InputStream inputStream;
    static String result = "";
    public static String getPropValues(String value) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = Utlis.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            String user = prop.getProperty(value);

            result = user;
            System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
