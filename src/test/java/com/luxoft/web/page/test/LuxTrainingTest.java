package com.luxoft.web.page.test;

import com.luxoft.web.page.page.HomePage;
import com.luxoft.web.page.page.LoginPage;
import com.luxoft.web.page.settings.SetUp;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

public class LuxTrainingTest extends BaseTest {
    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(SetUp.driver);
        homePage
                .clickMenuButton()
                .clickLoginButton();
        LoginPage loginPage = new LoginPage(SetUp.driver);
        loginPage
                .checkVisibilityElementsOnPage();
        loginPage
                .writeLogin("admin")
                .writePassword("admin")
                .setRememberMeCheck()
                .clickLoginButton()
                .checkErrorMessage();
    }
}
