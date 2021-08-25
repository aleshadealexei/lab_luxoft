package com.luxoft.web.page.test;

import com.luxoft.web.page.settings.SetUp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected static SetUp setUp ;

    @BeforeAll
    public static void init() {
        setUp = new SetUp();
    }

    @AfterEach
    public void close() {
        SetUp.goToHomePage();
    }

    @AfterAll
    public static void quit() {
        SetUp.driver.quit();
    }

}
