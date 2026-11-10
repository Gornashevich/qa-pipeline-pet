package ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

@Slf4j
public abstract class BasePage {


    @FindBy(xpath = "//span[@data-test=\"title\"]")
    private WebElement pageTitle;

    protected WebDriver driver;

    public BasePage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitUntilElementClickable(WebElement element) {
       return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));

    }

    protected WebElement waitUntilElementVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean waitUntilTextEquals(WebElement element, String expectedValue) {
        return new WebDriverWait(driver,Duration.ofSeconds(15))
                .ignoring(StaleElementReferenceException.class)
                .until(ignoredDriver -> element.getText().equals(expectedValue));

    }

    protected void waitUntilElementsAreVisible(WebElement... elements) {
       new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    protected boolean isPageOpened(String titleText) {
        log.info("Checking page with title {}", titleText);

        try {
            waitUntilTextEquals(pageTitle, titleText);

            log.info("Page with title {} is opened.", titleText);
            return true;
        } catch (TimeoutException e) {

            log.info("Page with title {} is not opened within timeout.", titleText);
            return false;
        }
    }

}

