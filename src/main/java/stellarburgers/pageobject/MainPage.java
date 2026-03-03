package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    public static final String URL = "https://stellarburgers.education-services.ru/";

    private final WebDriver driver;

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");
    private final By constructorHeading = By.xpath("//h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorHeading));
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверка, что вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        WebElement tab = driver.findElement(bunsTab);
        return tab.getAttribute("class").contains("current");
    }

    @Step("Проверка, что вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        WebElement tab = driver.findElement(saucesTab);
        return tab.getAttribute("class").contains("current");
    }

    @Step("Проверка, что вкладка 'Начинки' активна")
    public boolean isFillingsTabActive() {
        WebElement tab = driver.findElement(fillingsTab);
        return tab.getAttribute("class").contains("current");
    }

    @Step("Проверка отображения конструктора бургеров")
    public boolean isConstructorDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorHeading));
        return driver.findElement(constructorHeading).isDisplayed();
    }
}
