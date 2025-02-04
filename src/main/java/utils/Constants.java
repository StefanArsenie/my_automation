package utils;

import java.util.Random;

public class Constants {

    public static final String CONTENT_TYPE = "application/json";
    public static final String CLIENT_NAME = "Stefan";

    public static final String CLIENT_EMAIL = "testing" + randomNumber() + "@example.com";
    public static final String FIXED_EMAIL = "testing569@example.com";
    public static final String DEFORMED_EMAIL_1 = "testing569@example.";
    public static final String DEFORMED_EMAIL_2 = "testing569example.com";
    public static final String DEFORMED_EMAIL_3 = "@example.com";

    public static int randomNumber() {
        Random random = new Random();
        return random.nextInt(10000);
    }

}
