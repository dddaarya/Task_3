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
import stellarburgers.pageobject.HeaderPage;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.MainPage;
import stellarburgers.pageobject.ProfilePage;

import static org.junit.Assert.assertTrue;

public class ProfileNavigationTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private HeaderPage headerPage;
    private ProfilePage profilePage;
    private UserApiClient userApiClient;
    private User user;
    private String accessToken;
    private final Faker faker = new Faker();

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        headerPage = new HeaderPage(driver);
        profilePage = new ProfilePage(driver);
        userApiClient = new UserApiClient();

        user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(6, 10),
                faker.name().firstName()
        );
        userApiClient.createUser(user);
        accessToken = userApiClient.loginUser(user);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода по клику на 'Личный Кабинет'")
    public void navigateToProfile() {
        headerPage.clickPersonalAccount();
        profilePage.waitForLoad();

        assertTrue("Должна отобразиться страница профиля",
                profilePage.isProfilePageDisplayed());
    }
}
