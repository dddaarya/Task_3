package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public static final String URL = "https://stellarburgers.education-services.ru/login";

    private final WebDriver driver;

    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By recoverPasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    private final By loginHeading = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы логина")
    public void open() {
        driver.get(URL);
        waitForLoad();
    }

    @Step("Ожидание загрузки страницы логина")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginHeading));
    }

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Вход в аккаунт с email: {email}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MainPage.URL));
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickRecoverPasswordLink() {
        driver.findElement(recoverPasswordLink).click();
    }

    @Step("Проверка отображения страницы логина")
    public boolean isLoginPageDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginHeading));
        return driver.findElement(loginHeading).isDisplayed();
    }
}
