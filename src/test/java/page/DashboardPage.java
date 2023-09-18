package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.awt.SystemColor.text;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private static final SelenideElement errorNotification = $("[data-test-id='error-notification']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static void verifyErrorNotificationVisibility() {
        errorNotification.shouldBe(visible);
    }



}