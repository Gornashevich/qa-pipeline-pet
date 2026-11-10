package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    return driver == null ? driver = createDriver() : driver;
  }

  private static WebDriver createDriver() {
    ChromeOptions options = configureChromeOptions();
    driver = WebDriverManager.chromedriver().cachePath("build").capabilities(options).create();
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    return driver;
  }

  private static ChromeOptions configureChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--no-sandbox");
    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    return options;
  }

  public static void closeDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
