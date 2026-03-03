package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    public static final String URL = "https://stellarburgers.education-services.ru/account/profile";

    private final WebDriver driver;

    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By profileLink = By.xpath("//a[text()='Профиль']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы профиля")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLink));
    }

    @Step("Проверка отображения страницы профиля")
    public boolean isProfilePageDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLink));
        return driver.findElement(profileLink).isDisplayed();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickLogoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }
}
