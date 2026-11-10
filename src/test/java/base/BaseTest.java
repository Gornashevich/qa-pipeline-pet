package base;

import config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.DriverManager;

public abstract class BaseTest {
  protected WebDriver driver;

  @BeforeTest(alwaysRun = true)
  public void setUp() {
    driver = DriverManager.getDriver();
    driver.get(ConfigurationManager.getBaseUrl());
  }

  @AfterTest
  public void tearDown() {
    DriverManager.closeDriver();
  }
}
