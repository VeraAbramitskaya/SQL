package data;

import com.github.javafaker.Code;
import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    public static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getAuthInfoWithTestData() {

        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getExistLogin(){
        return ("vasya");
    }

    public static String generateRandomLogin() {
        return faker.name().username();
    }

    public static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static AuthInfo generateExistLoginAndRandomPassword(){
        return new AuthInfo(getExistLogin(), generateRandomPassword());
    }



    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode generateVerificationCode(int length){
        return new VerificationCode(String.valueOf(faker.code()));
    }



    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}
