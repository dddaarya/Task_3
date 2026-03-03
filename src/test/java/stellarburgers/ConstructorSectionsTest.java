package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.driver.WebDriverFactory;
import stellarburgers.pageobject.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorSectionsTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу 'Булки' в конструкторе")
    public void switchToBunsSection() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertTrue("Вкладка 'Булки' должна быть активной",
                mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу 'Соусы' в конструкторе")
    public void switchToSaucesSection() {
        mainPage.clickSaucesTab();

        assertTrue("Вкладка 'Соусы' должна быть активной",
                mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу 'Начинки' в конструкторе")
    public void switchToFillingsSection() {
        mainPage.clickFillingsTab();

        assertTrue("Вкладка 'Начинки' должна быть активной",
                mainPage.isFillingsTabActive());
    }
}
