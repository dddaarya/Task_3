package stellarburgers;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.api.UserApiClient;
import stellarburgers.driver.WebDriverFactory;
import stellarburgers.model.User;
import stellarburgers.pageobject.*;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private HeaderPage headerPage;
    private UserApiClient userApiClient;
    private User user;
    private String accessToken;
    private final Faker faker = new Faker();

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        headerPage = new HeaderPage(driver);
        userApiClient = new UserApiClient();

        user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(6, 10),
                faker.name().firstName()
        );
        userApiClient.createUser(user);
        accessToken = userApiClient.loginUser(user);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Проверка входа через кнопку на главной странице")
    public void loginViaMainPageButton() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Должен отобразиться конструктор после входа",
                mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку 'Личный кабинет' в хедере")
    public void loginViaPersonalAccountButton() {
        mainPage.open();
        headerPage.clickPersonalAccount();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Должен отобразиться конструктор после входа",
                mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через ссылку 'Войти' на странице регистрации")
    public void loginViaRegistrationForm() {
        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Должен отобразиться конструктор после входа",
                mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через ссылку 'Войти' на странице восстановления пароля")
    public void loginViaForgotPasswordForm() {
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Должен отобразиться конструктор после входа",
                mainPage.isConstructorDisplayed());
    }
}
