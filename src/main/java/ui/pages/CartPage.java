package ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Slf4j
public class CartPage extends BasePage {

    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement cartPageTitle;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(xpath = "//*[@data-test=\"inventory-item-name\"]")
    private WebElement productDescription;


    public CartPage(WebDriver webdriver) {
        super(webdriver);
    }

    public boolean isCartPageOpened() {
       return isPageOpened("Your Cart");
    }


    public void pressCheckoutButton() {
        log.info("Pressing checkout button");
        waitUntilElementClickable(checkoutButton).click();
    }

    public String getProductDescription() {
        log.info("Getting product description on the checkout page");
        return waitUntilElementVisible(productDescription).getText();
    }
}
