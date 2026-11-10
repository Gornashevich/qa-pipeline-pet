package ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class CheckoutOverviewPage extends BasePage {

    @FindBy(xpath = "//*[@data-test=\"tax-label\"]")
    private WebElement taxValue;

    @FindBy(xpath = "//*[@data-test=\"total-label\"]")
    private WebElement totalValue;

    @FindBy(id = "finish")
    private WebElement finish;


    public CheckoutOverviewPage(WebDriver webdriver) {
        super(webdriver);
    }


    public boolean isCheckoutOverviewPageOpened() {
        return isPageOpened("Checkout: Overview");
    }

    public int getTaxValue() {
        log.info("Getting tax value");
        return Integer.parseInt(taxValue.getText().replaceAll("\\D", ""));
    }

    public int getTotalValue(){
        log.info("Getting total value");
        return Integer.parseInt(totalValue.getText().replaceAll("\\D", ""));
    }

    public void placeTheOrder() {
        log.info("Place the order");
        waitUntilElementClickable(finish).click();
    }

}
