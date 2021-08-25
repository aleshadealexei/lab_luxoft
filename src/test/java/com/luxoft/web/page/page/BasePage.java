package com.luxoft.web.page.page;

import com.luxoft.web.page.settings.SetUp;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть {0} в новой вкладке")
    public void openNewTab(String url) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.open()");
        switchTab(1);
        driver.get(url);
    }

    @Step("Переключить вкладку на индекс = {0}")
    public void switchTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber));
    }

    @Step("Получить текущий адрес")
    public String getCurrentUrl() {
        return SetUp.driver.getCurrentUrl();
    }

    @Step("Проверка открытия пдф")
    public boolean checkPdfFileOpened() {
        System.out.println("CATALOG URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl().contains(".pdf");
    }

    @Step("Нажать на ссылку {0}")
    public void clickLinkByText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

}
