package com.luxoft.web.page.settings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SetUp {
    public static WebDriver driver;
    public static WebDriverWait wait;
    private static String baseUrl = "https://www.luxoft-training.ru/";


    public SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver","lib/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        driver.get(baseUrl);
    }

    public static void goToHomePage() {
        driver.get(baseUrl);
    }



}
