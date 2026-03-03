package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    public static final String URL = "https://stellarburgers.education-services.ru/register";

    private final WebDriver driver;

    private final By nameField = By.xpath("//fieldset[1]//input[@name='name']");
    private final By emailField = By.xpath("//fieldset[2]//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By passwordError = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }

    @Step("Ввод имени: {name}")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Регистрация пользователя: {name}, {email}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Проверка отображения ошибки 'Некорректный пароль'")
    public boolean isPasswordErrorDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordError));
        return driver.findElement(passwordError).isDisplayed();
    }
}
