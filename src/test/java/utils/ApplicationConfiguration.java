package utils;

import java.util.Base64;

public class ApplicationConfiguration {

    private static String whisk_applicationURL = DefaultConfiguration.getProperty("whisk.application");
    private static String whisk_username = DefaultConfiguration.getProperty("whisk.username");
    private static String whisk_password = new String (Base64.getDecoder().decode(DefaultConfiguration.getProperty("whisk.password")));
    private static String hostBaseURL = DefaultConfiguration.getProperty("host");
    private static String token = DefaultConfiguration.getProperty("token");

    public static String getWhiskApplicationURL(){
        return whisk_applicationURL;
    }

    public static String getWhiskUsername(){
        return whisk_username;
    }

    public static String getWhiskUserPassword(){
        return whisk_password;
    }

    public static String getHostBaseUrl() {
        return hostBaseURL;
    }

    public static String getToken() {
        return token;
    }

}
