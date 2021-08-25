package com.luxoft.web.page.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BasePage {


    @FindBy(css = ".header__control._nav")
    WebElement menuIcon;

    @FindBy(className = "two-links")
    WebElement loginIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть меню")
    public HomePage clickMenuButton() {
        menuIcon.click();
        return this;
    }

    @Step("Нажать на кнопку Логин")
    public HomePage clickLoginButton() {
        loginIcon.click();
        assertEquals("https://www.luxoft-training.ru/personal_test/", driver.getCurrentUrl());
        return this;
    }
}
