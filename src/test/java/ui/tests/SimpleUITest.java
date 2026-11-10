package ui.tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.pages.*;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleUITest extends BaseTest {

    @Test
    public void simpleUiScenario() {

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);

        Assert.assertTrue(productPage.isProductPageOpened(), "Product page is not opened");
        Map<String, String> productMap = productPage.addProductContaining("Red");

        Assert.assertTrue(productPage.isCartUpdated(1), "Incorrect card number");
        productPage.navigateToCartPage();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page is not opened");

        String expectedRedProduct = productMap.keySet().iterator().next();
        String actualProductDescription = cartPage.getProductDescription();

        Assert.assertEquals(actualProductDescription, expectedRedProduct, "The added product description is not equal to the product product from the cart page.");

        cartPage.pressCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page is not opened.");
        checkoutPage.performCheckout("Fn", "Ln", 220050);

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);

        Assert.assertTrue(checkoutOverviewPage.isCheckoutOverviewPageOpened(), "Checkout overview page is not opened");

        int actualTaxPrice = checkoutOverviewPage.getTaxValue();
        int actualTotalPrice = checkoutOverviewPage.getTotalValue();

        String expectedProductPrice = productMap.values().iterator().next();
        int priceWithoutSymbols = Integer.parseInt(expectedProductPrice.replaceAll("\\D", ""));

        Assert.assertEquals(actualTotalPrice, actualTaxPrice + priceWithoutSymbols);

        checkoutOverviewPage.placeTheOrder();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        checkoutCompletePage.isCheckoutCompletePageOpened();
    }

    @Test
    public void simpleUiScenarioSoftAssert() {

        SoftAssert softAssert = new SoftAssert();

        LoginPage loginPage = new LoginPage(driver);

        softAssert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);

        softAssert.assertTrue(productPage.isProductPageOpened(), "Product page is not opened");
        Map<String, String> productMap = productPage.addProductContaining("Red");

        softAssert.assertTrue(productPage.isCartUpdated(1), "Incorrect card number");
        productPage.navigateToCartPage();

        CartPage cartPage = new CartPage(driver);

        softAssert.assertTrue(cartPage.isCartPageOpened(), "Cart page is not opened");

        String expectedRedProduct = productMap.keySet().iterator().next();
        String actualProductDescription = cartPage.getProductDescription();

        softAssert.assertEquals(actualProductDescription, expectedRedProduct, "The added product description is not equal to the product product from the cart page.");

        cartPage.pressCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        softAssert.assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page is not opened.");
        checkoutPage.performCheckout("Fn", "Ln", 220050);

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);

        softAssert.assertTrue(checkoutOverviewPage.isCheckoutOverviewPageOpened(), "Checkout overview page is not opened");

        int actualTaxPrice = checkoutOverviewPage.getTaxValue();
        int actualTotalPrice = checkoutOverviewPage.getTotalValue();

        String expectedProductPrice = productMap.values().iterator().next();
        int priceWithoutSymbols = Integer.parseInt(expectedProductPrice.replaceAll("\\D", ""));

        softAssert.assertEquals(actualTotalPrice, actualTaxPrice + priceWithoutSymbols);

        checkoutOverviewPage.placeTheOrder();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageOpened());

        softAssert.assertAll();
    }

    @Test
    public void getAndCheckRedProduct() {
        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.isPageOpened()).isTrue();
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);

        assertThat(productPage.isProductPageOpened()).isTrue();

        List<String> productItemsNames = productPage.getProductItemsNames();
        assertThat(productItemsNames).isNotEmpty();

        List<String> filteredList = productItemsNames.stream().filter(x -> x.contains("Red")).toList();

        assertThat(filteredList).singleElement().isEqualTo("Test.allTheThings() T-Shirt (Red)");

        productPage.addProductContaining("Red");

        productPage.navigateToCartPage();

        CartPage cartPage = new CartPage(driver);

        assertThat(cartPage.isCartPageOpened()).isTrue();

        String actualProductDescription = cartPage.getProductDescription();

        assertThat(filteredList).containsExactly(actualProductDescription);

        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).singleElement().isEqualTo(actualProductDescription);



    }


}
