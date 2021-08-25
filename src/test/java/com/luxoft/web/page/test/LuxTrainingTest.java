package com.luxoft.web.page.test;

import com.luxoft.web.page.page.CataloguePage;
import com.luxoft.web.page.page.HomePage;
import com.luxoft.web.page.page.LoginPage;
import com.luxoft.web.page.settings.SetUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LuxTrainingTest extends BaseTest {
    HomePage homePage = new HomePage(SetUp.driver);
    LoginPage loginPage = new LoginPage(SetUp.driver);
    CataloguePage cataloguePage = new CataloguePage(SetUp.driver);
    @Test
    @DisplayName("TC.HM.1.1 - Проверка корректного открытия меню")
    public void openMenuTest() {
        homePage
                .openMenu();
    }


    @ParameterizedTest(name = "TC.HM.1.1.2 - Проверка наличия {0} в меню")
    @CsvFileSource(resources = "/links.csv", numLinesToSkip = 1)
    public void checkMenuList(String links) {
        HomePage homePage = new HomePage(SetUp.driver);
        List<String> listString = Arrays.asList(links.split(","));
        homePage.openMenu();
        homePage
                .compareLinksTexts(listString);
    }

    @ParameterizedTest(name = "TC.HM.1.1.3 - Проверка изменения цвета при наведении на {0}")
    @CsvSource({"Каталог", "Расписание", "Оценка персонала"})
    public void checkColorChangeOnMouseHoverTest(String menuName) {
        String colorBeforeHover = homePage.openMenu()
                .getColorOfLinkByPartialLinkText(menuName);
        assertEquals(Constants.BLUE_COLOR, colorBeforeHover, "ПЕРЕД НАВЕДЕНИЕМ ЦВЕТ НЕ СИНИЙ");

        String colorAfterHover = homePage.hoverMouse(menuName)
                .getColorOfLinkByPartialLinkText(menuName);
        assertEquals(Constants.ORANGE_COLOR, colorAfterHover, "ПОСЛЕ НАВЕДЕНИЯ ЦВЕТ НЕ ОРАНЖЕВЫЙ");

        homePage.closeMenu();
    }

    @Test
    @DisplayName("TC.LP.1.1 - Проверка отображения элементов на странице логина")
    public void loginPageVisibilityTest() {
        homePage
                .openMenu()
                .openLoginPage();
        loginPage
                .checkVisibilityElementsOnPage();
    }

    @Test
    @DisplayName("TC.HM_LP.1.2 - Проверка авторизации")
    public void authorizeTest() {
        homePage
                .openMenu()
                .openLoginPage();
        loginPage
                .checkVisibilityElementsOnPage();
        loginPage
                .writeLogin("admin")
                .writePassword("admin")
                .setRememberMeCheck()
                .clickLoginButton()
                .checkErrorMessage();
    }

    @Test
    @DisplayName("TC.CTG.1.1 - Проверка открытия каталога")
    public void openCataligueTest() {
        homePage.openMenu().clickLinkByText("Каталог");
        cataloguePage
                .clickDownloadCatalogue()
                .switchTab(1);
        assertTrue(cataloguePage.checkPdfFileOpened());
    }

    @Test
    @DisplayName("TC.CTG.1.2 - Поиск курса на странице каталога")
    public void findCourseTest() {
        homePage.openMenu().clickLinkByText("Каталог");
        cataloguePage.searchCourse("SQA-050", "Школа автоматизированного тестирования");
        String courseLink = cataloguePage.getCourseLink("Школа автоматизированного тестирования");
        cataloguePage.openNewTab(courseLink);
        cataloguePage.verifyCollectionSize(2);
        cataloguePage.switchTab(0);
    }

}
