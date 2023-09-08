package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDatabase;

public class LoginPageTest {
    @AfterAll
    static void shutdown() {
        cleanDatabase();
    }

    @Test
    @DisplayName("Should successfully login to dashboard with login and password from sut test data")
    void shouldSuccessfulLogin() {
        var loginPage = open("http://Localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifycationPageVisiblity();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    @DisplayName("Should ger error message when use exist login and pasword and random verification code")
    void shouldGetErrorNotificationIfRandomVerificationCode(){
        var loginPage = open("http://Localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifycationPageVisiblity();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisiblity();
    }

    @Test
    @DisplayName("Should get error notification if use random pass with exist login")
        //    логин от существующего юзера, пароль рандомный. должно появляться сообщеное об ошибке
        //    "введен неверный пароль".
    void shouldGetErrorNotificationIfRandomPassAndExistUser(){
        var loginPage = open("http://Localhost:9999", LoginPage.class);
        var existLogin = DataHelper.getExistLogin();
        var randomPassword = DataHelper.generateRandomPassword();
        verificationPage.verifyErrorNotificationVisiblity();

    }

    @Test
    @DisplayName("Shoul get error notification if user is not exist")
    void shouldGerErrorNotificationIfUserIsNotExist(){
        var loginPage = open("http://Localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifycationPageVisiblity();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisiblity();


    }
}