package ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@data-test=\"inventory-list\"]")
    private WebElement productContainer;

    @FindBy(xpath = "//div[@data-test=\"inventory-item\"]")
    private List<WebElement> productItems;

    @FindBy(xpath = "//*[@data-test=\"inventory-item-name\"]")
    private WebElement itemName;

    @FindBy(xpath = "//span[@data-test='shopping-cart-badge']")
    private WebElement shoppingCartBadge;

    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    private WebElement shoppingCartLink;

    public ProductPage(WebDriver webdriver) {
        super(webdriver);
    }

    public boolean isProductPageOpened() {
        return isPageOpened("Products");
    }

    public Map<String, String> addProductContaining(String text) {
        log.info("Searching for product containing text '{}'", text);

        Map<String, String> product = new HashMap<>();

        String productName;
        String productPrice;


        for (WebElement element : productItems) {

            String elementDescription = element.findElement(By.xpath(".//*[@data-test=\"inventory-item-name\"]")).getText();

            if (elementDescription.contains(text)) {

                productName = elementDescription;
                productPrice = (element.findElement(By.xpath(".//*[@data-test=\"inventory-item-price\"]")).getText());

                product.put(productName, productPrice);
                log.info("Found product containing text '{}'", text);

                WebElement cardButton = element.findElement(By.xpath(".//button[starts-with(@data-test, 'add-to-cart')]"));
                waitUntilElementClickable(cardButton).click();

                return product;
            }

        }

        throw new NoSuchElementException("Product containing text " + text + "was not found.");
    }

    public List<String> getProductItemsNames() {
        log.info("Getting define product list");

        return productItems.stream()
                .map(e -> e.findElement(By.xpath(".//*[@data-test=\"inventory-item-name\"]")).getText())
                .toList();
    }

    public boolean isCartUpdated(int expectedValue) {
        log.info("Checking is cart badge updated...");
        try {
            return waitUntilTextEquals(shoppingCartBadge, String.valueOf(expectedValue));
        } catch (TimeoutException timeoutException) {

            log.info("Shopping cart is not updated correctly");
            return false;
        }
    }

    public void navigateToCartPage() {
        log.info("Navigate to Cart page");
        waitUntilElementClickable(shoppingCartLink).click();
    }


}




