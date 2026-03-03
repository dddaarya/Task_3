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
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private UserApiClient userApiClient;
    private String accessToken;
    private final Faker faker = new Faker();

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        userApiClient = new UserApiClient();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации с валидными данными")
    public void successfulRegistration() {
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(6, 10);

        registerPage.open();
        registerPage.register(name, email, password);

        assertTrue("Должна отобразиться страница логина после успешной регистрации",
                loginPage.isLoginPageDisplayed());

        User user = new User(email, password, name);
        accessToken = userApiClient.loginUser(user);
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    @Description("Проверка ошибки при вводе пароля менее 6 символов")
    public void registrationWithShortPasswordShowsError() {
        registerPage.open();
        registerPage.register(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                "12345"
        );

        assertTrue("Должна отобразиться ошибка 'Некорректный пароль'",
                registerPage.isPasswordErrorDisplayed());
    }
}
