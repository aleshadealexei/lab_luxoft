package com.luxoft.web.page.page;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class HomePage extends BasePage {
    @FindBy(css = ".header__control._nav")
    WebElement menuIcon;

    @FindBy(className = "two-links")
    WebElement loginIcon;

    @FindBy(className = "menu-addit-main")
    WebElement menuList;

    @FindBy(className = "close-menu")
    WebElement closeMenuIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть меню")
    public HomePage openMenu() {
        menuIcon.click();
        assertTrue(menuList.isDisplayed());
        return this;
    }

    @Step("Закрыть меню")
    public HomePage closeMenu() {
        closeMenuIcon.click();
        assertFalse(menuList.isDisplayed());
        return this;
    }

    @Step("Перейти на страницу LoginPage")
    public void openLoginPage() {
        loginIcon.click();
        assertEquals("https://www.luxoft-training.ru/personal_test/", driver.getCurrentUrl());
    }

    private List<WebElement> getMenuItems() {
        return menuList.findElements(By.tagName("a"));
    }

    @Step("Поиск {link} в меню")
    public HomePage compareLinkText(String link) {
        List<WebElement> linkList = getMenuItems();
        try {
            WebElement el = linkList
                    .stream()
                    .filter((x) -> x.getText().equals(link))
                    .findFirst().get();
            assertNotNull(el, "Нет пункта меню " + link);
        }
        catch (NoSuchElementException ex) {
            Assertions.fail( "Нет пункта меню " + link);
        }
        return this;
    }

    @Step("Поиск ссылок в меню")
    public HomePage compareLinksTexts(List<String> links) {
        for (String link : links
             ) {
            compareLinkText(link);
        }
        return this;
    }

    @Step("Получить цвет элемента {0}")
    public String getColorOfLinkByPartialLinkText(String partialLinkText) {
        return driver.findElement(By.partialLinkText(partialLinkText)).getCssValue("color");
    }

    @Step("Навести мышку на элемент {0}")
    public HomePage hoverMouse(String partialLinkText) {
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.partialLinkText(partialLinkText));
        builder.moveToElement(element).build().perform();
        return this;
    }

}
