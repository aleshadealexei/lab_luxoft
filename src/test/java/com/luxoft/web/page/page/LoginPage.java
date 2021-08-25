package com.luxoft.web.page.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPage extends BasePage {
    @FindBy(name = "USER_LOGIN")
    WebElement loginField;

    @FindBy(name = "USER_PASSWORD")
    WebElement passwordField;

    @FindBy(name = "Login")
    WebElement loginButton;

    @FindBy(className = "errortext")
    WebElement errorMessage;

    @FindBy(id = "lb03-styler")
    WebElement rememberMeCheckBox;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка отрисовки элементов")
    public LoginPage checkVisibilityElementsOnPage() {
        assertAll(() -> assertTrue(loginField.isDisplayed(), "Поле ввода Логина не отобразилось"),
                () -> assertTrue(passwordField.isDisplayed(), "Поле ввода пароля не отобразилось"),
                () -> assertTrue(loginButton.isDisplayed(), "Кнопка авторизации не отобразилась"),
                () -> assertTrue(rememberMeCheckBox.isDisplayed(), "Чекбокс не отобразился")
        );

        return this;
    }

    @Step("Заполнить поле логина")
    public LoginPage writeLogin(String login) {
        loginField.sendKeys(login);
        return this;
    }

    @Step("Заполнить поле пароля")
    public LoginPage writePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Поставить чекбокс 'Запомнить меня'")
    public LoginPage setRememberMeCheck() {
        rememberMeCheckBox.click();
        WebElement checkedCheckBox = driver.findElement(By.cssSelector(".jq-checkbox.checked"));
        assertNotNull(checkedCheckBox, "ЧекБокс не выставился!");
        return this;
    }

    @Step("Нажать кнопку авторизации")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Проверка вышло ли окно ошибки")
    public LoginPage checkErrorMessage() {
        assertEquals("Неверный логин или пароль.", errorMessage.getText());
        return this;
    }
}
