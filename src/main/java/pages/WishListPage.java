package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.JavaScriptUtility.scrollToElementJS;

public class WishListPage extends JacketsPage {

    private final By removePriceLink = By.xpath("//ol[@class='items']//li[@class='item']//a[@class='action remove' and @title='Remove Price $50.00 - $59.99']");
    By clickHere = By.xpath("//a[text()='here']");


    public void clickRemovePrice() {
        WebElement removePriceButton = driver.findElement(removePriceLink);
        removePriceButton.click();
    }

    public void addToWishList(String productPath, int i) {
        delay(2000);
        WebElement product = driver.findElement(By.xpath(productPath));
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();
        delay(2000);

        WebElement addToWishListButton = product.findElement(By.xpath("(//a[@class='action towishlist'])[" + i + "]"));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToWishListButton));

        addToWishListButton.click();
    }

    public void clickHere() {
        click(clickHere);
        new JacketsPage();
    }

    public boolean isItemAddedToWishList(String productName) {
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(), '" + productName + " has been added to your Wish List')]"));
        return successMessage.isDisplayed();
    }

    public void openAccountDropdown() {
        WebElement accountDropdown = driver.findElement(By.xpath("//button[@class='action switch' and @data-action='customer-menu-toggle']"));
        accountDropdown.click();
        delay(2000);
    }

    public int getWishListItemCount(WebDriver driver) {
        String xpath = "//div[@class='customer-menu' and @aria-hidden='false']//span[@class='counter qty' and contains(text(), 'items')]";


        WebElement wishlistCounterElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        String text = wishlistCounterElement.getText();
        String numberText = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberText);
    }

    public void hoverAndAddToCart(String productPath) {
        WebElement product = driver.findElement(By.xpath(productPath));
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();
        delay(2000);

        WebElement addToCartButton = product.findElement(By.xpath("(//div[@class='products-grid wishlist']//button[contains(@class, 'action tocart primary') and ./span])[1]"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        addToCartButton.click();
    }


    public void selectProductOptions(String sizeLocator, String colorLocator) {

        By size = By.xpath(sizeLocator);
        scrollToElementJS(size);
        click(size);
        By color = By.xpath(colorLocator);
        scrollToElementJS(color);
        click(color);
    }

    public void clickAddToCart(String addToCartButtonLocator) {
        driver.findElement(By.xpath(addToCartButtonLocator)).click();
    }

    public boolean isMessageDisplayed(String expectedMessage) {
        String actualMessage = driver.findElement(By.cssSelector("//div[@class='page messages']//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        return actualMessage.contains(expectedMessage);
    }

    public boolean isShoppingCartPageDisplayed(String cartHeaderLocator) {
        return driver.findElement(By.xpath(cartHeaderLocator)).isDisplayed();
    }

    public double getPrice(String priceLocator) {
        String priceText = driver.findElement(By.xpath(priceLocator)).getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

}



