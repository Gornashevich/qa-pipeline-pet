package ui.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CheckoutPage.class);

    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement checkoutPageTitle;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutPage(WebDriver webdriver) {
        super(webdriver);
    }

    public boolean isCheckoutPageOpened() {
      return isPageOpened("Checkout: Your Information");
    }

    public void performCheckout(String firstName_, String lastName_, Integer postalCode_) {
        log.info("Submitting checkout form");

        waitUntilElementClickable(firstName).sendKeys(firstName_);
        waitUntilElementClickable(lastName).sendKeys(lastName_);
        waitUntilElementClickable(postalCode).sendKeys(String.valueOf(postalCode_));
        waitUntilElementClickable(continueButton).click();

        log.info("Checkout form form was submitting");
    }



}
