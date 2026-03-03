package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    public static final String URL = "https://stellarburgers.education-services.ru/forgot-password";

    private final WebDriver driver;

    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By recoverHeading = By.xpath("//h2[text()='Восстановление пароля']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы восстановления пароля")
    public void open() {
        driver.get(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverHeading));
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
