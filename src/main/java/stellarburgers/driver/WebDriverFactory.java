package stellarburgers.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "yandex":
                WebDriverManager.chromedriver().driverVersion("126.0.6478.126").setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(yandexOptions);
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        return createDriver(browser);
    }
}
