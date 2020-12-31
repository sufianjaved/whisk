package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DefaultConfiguration {

    protected static Logger logger = LogManager.getLogger("connector");
    protected static final String DEFAULT_CONFIGURATION	= "conf/default.ini";
    protected static Properties props = new Properties();
    protected static String defaultBrowserName = "Chrome";

    private static WebDriver defaultBrowser;

    static{
        loadProperties(props, DEFAULT_CONFIGURATION);
        loadPropsIntoMemory();
    }

    protected static Properties loadProperties(Properties props, String configurationFile) {
        try {
            logger.debug("Loading Default configuration:");
            props.load(new FileInputStream(new File(configurationFile)));
        } catch (Exception e) {
            logger.error("Error while loading Default configuration: " + e);
            e.printStackTrace();
            props = null;
        }
        return props;
    }

    private static void loadPropsIntoMemory(){
        defaultBrowserName = props.getProperty("default.browser");
    }

    public static String getProperty(String name){
        return props.getProperty(name, null);
    }
    public static String getProperty(String name, String defaultValue){
        return props.getProperty(name, defaultValue);
    }
    public static String getDefaultBrowserName(){
        return defaultBrowserName;
    }

    public static WebDriver getDefaultBrowser() {
        return defaultBrowser;
    }
    public static void setDefaultBrowser(WebDriver defaultBrowser) {
        DefaultConfiguration.defaultBrowser = defaultBrowser;
    }
}

