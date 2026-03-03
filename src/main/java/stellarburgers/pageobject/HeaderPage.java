package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage {

    private final WebDriver driver;

    private final By constructorLink = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.cssSelector("div.AppHeader_header__logo__2D0X2");
    private final By personalAccountLink = By.xpath("//p[text()='Личный Кабинет']");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructor() {
        driver.findElement(constructorLink).click();
    }

    @Step("Клик по логотипу Stellar Burgers")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickPersonalAccount() {
        driver.findElement(personalAccountLink).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.or(
                        ExpectedConditions.urlContains("/account"),
                        ExpectedConditions.urlContains("/login")
                ));
    }
}
