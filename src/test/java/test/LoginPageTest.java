package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.*;
import page.DashboardPage;
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
        verificationPage.verificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    @DisplayName("Should get error message when use exist login and password and random verification code")
    void shouldGetErrorNotificationIfRandomVerificationCode(){
        var loginPage = open("http://Localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisibility();
        var verificationCode = DataHelper.generateVerificationCode(4);
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisibility();
    }



    @Test
    @DisplayName("Should get error notification if use random pass with exist login")
        //    логин от существующего юзера, пароль рандомный. должно появляться сообщеное об ошибке
        //    "введен неверный пароль".
    void shouldGetErrorNotificationIfRandomPassAndExistUser(){
        var loginPage = open("http://Localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateExistLoginAndRandomPassword();
        var verificationPage = loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotificationVisibility();

    }

    @Test
    @DisplayName("Should get error notification if user is not exist")
    void shouldGerErrorNotificationIfUserIsNotExist(){
        var loginPage = open("http://Localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        var verificationPage = loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotificationVisibility();


    }
}