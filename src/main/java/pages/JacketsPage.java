package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static utilities.JavaScriptUtility.scrollToElementJS;

public class JacketsPage extends BasePage {
    private By colorDropdown = By.xpath("//div[@class='filter-options-title' and contains(text(), 'Color')]");
    private By redColorOption = By.xpath("//a[@aria-label='Red']//div[@option-label='Red' and contains(@style, '#ff0000')]");
    private By priceFilter = By.xpath("//div[@class='filter-options-title' and contains(text(), 'Price')]");
    private By firstPriceOption = By.xpath("//a[.//span[@class='price' and text()='$50.00']]");
    private By product1Price = By.cssSelector("#product-price-1300 .price");
    private By product2Price = By.cssSelector("#product-price-1252 .price");
    private By allProducts = By.id("toolbar-amount");

    public void selectRedColor() {
        delay(2000);
        waitForElementToBeClickable(colorDropdown, 10);
        click(colorDropdown);
        delay(2000);
        waitForElementToBeClickable(redColorOption, 10);
        click(redColorOption);
        waitForElementToBeVisible(allProducts, 10);
    }

    public boolean isRedColorSelected() {

            WebElement redFilterOption = driver.findElement(By.id("option-label-color-93-item-58"));
            return Objects.requireNonNull(redFilterOption.getAttribute("class")).contains("selected");
    }


    public void selectFirstPriceRange() {
        scrollToElementJS(priceFilter);
        click(priceFilter);
        waitForElementToBeClickable(firstPriceOption, 10);
        click(firstPriceOption);
        waitForElementToBeVisible(allProducts, 10);
    }

    public int getNumberOfDisplayedProducts() {
        String productCountText = driver.findElement(allProducts).getText();
        String[] parts = productCountText.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public String getFirstProductPrice() {
        return find(product1Price).getText();
    }

    public String getSecondProductPrice() {
        return find(product2Price).getText();
    }

    public boolean verifyExpectedPrices() {
        double price1 = Double.parseDouble(getFirstProductPrice().replace("$", ""));
        double price2 = Double.parseDouble(getSecondProductPrice().replace("$", ""));

        return (price1 > 50 && price1 < 60) && (price2 > 50 && price2 < 60);
    }
}