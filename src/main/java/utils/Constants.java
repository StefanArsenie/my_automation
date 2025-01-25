package utils;

import java.util.Random;

public class Constants {

    public static final String CONTENT_TYPE = "application/json";
    public static final String CLIENT_NAME = "Stefan";

    public static final String CLIENT_EMAIL = "testing" + randomNumber() + "@example.com";

    public static int randomNumber() {
        Random random = new Random();
        return random.nextInt(10000);
    }

}
