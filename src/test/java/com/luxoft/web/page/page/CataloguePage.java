package com.luxoft.web.page.page;

import com.luxoft.web.page.settings.SetUp;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CataloguePage extends BasePage {
    @FindBy(className = "js-tracking")
    WebElement downloadCatalogueButton;

    @FindBy(id = "search-text")
    WebElement searchField;

    @FindBy(linkText = "Записаться на курс")
    List<WebElement> signUpButton;

    public CataloguePage(WebDriver driver) {
        super(driver);
    }

    @Step("Совершить клик по 'Скачать каталог'")
    public CataloguePage clickDownloadCatalogue() {
        downloadCatalogueButton.click();
        System.out.println("BEFORE URL: " + driver.getCurrentUrl());
        return this;
    }

    @Step("Поиск курса {0}")
    public CataloguePage searchCourse(String query, String expectedCourseName) {
        searchField.sendKeys(query);
        searchField.sendKeys(Keys.ENTER);
        SetUp.wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(expectedCourseName)));
        return this;
    }

    @Step("Получить ссылку на курс")
    public String getCourseLink(String partialLink) {
        return driver.findElement(By.partialLinkText(partialLink)).getAttribute("href");
    }

    public void verifyCollectionSize(int size) {
        assertEquals(signUpButton.size(), size);
    }
}
