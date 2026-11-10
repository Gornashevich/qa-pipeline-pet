package ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class CheckoutCompletePage extends BasePage {

    @FindBy(xpath = "//*[@data-test='complete-header']")
    private WebElement completeHeader;


    public CheckoutCompletePage(WebDriver webdriver) {
        super(webdriver);
    }

    public boolean isCheckoutCompletePageOpened() {
        log.info("Checking completion page is opened");

        try {
          return  waitUntilTextEquals(completeHeader,"Thank you for your order!");

        } catch (TimeoutException e) {

            log.info("Checkout complete page is not opened");
            return false;
        }


    }



}
