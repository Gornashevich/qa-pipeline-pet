package ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Slf4j
public class LoginPage extends BasePage {

  public LoginPage(WebDriver webdriver) {
    super(webdriver);
  }

  @FindBy(id = "user-name")
  private WebElement usernameInput;

  @FindBy(id ="password")
  private WebElement passwordInput;

  @FindBy(id = "login-button")
  private WebElement loginButton;

  public boolean isPageOpened() {
    log.info("Checking login page is opened...");

    try {
      waitUntilElementsAreVisible(usernameInput, passwordInput, loginButton);

      log.info("Login page is opened.");

      return true;
    } catch (TimeoutException e){

      log.info("Login page is not opened within timeout.");
      return false;
    }
  }

  public void login(String name, String pass) {
    log.info("Submitting logging form for user '{}'", name);

    waitUntilElementClickable(usernameInput).sendKeys(name);
    waitUntilElementClickable(passwordInput).sendKeys(pass);
    waitUntilElementClickable(loginButton).click();

    log.info("Login form was submitting");
  }



}
