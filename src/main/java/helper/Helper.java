package helper;

import com.github.javafaker.Faker;

public class Helper {
    Faker faker = new Faker();
    public String emailGeneration() {
        return faker.internet().emailAddress();
    }

    public String firstNameGeneration() {
        return faker.name().firstName();
    }
}
