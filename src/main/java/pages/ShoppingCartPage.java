package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtility;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    private By firstProductPriceLocator = By.xpath("//td[@class='col subtotal']//span[@class='cart-price']//span[text()='60']");
    private By secondProductPriceLocator = By.xpath("//td[@class='col subtotal']//span[@class='cart-price']//span[text()='59']");
    private By orderTotalLocator = By.xpath("//tr[@class='grand totals']//td[@class='amount']//span[@class='price']");
    private By pageTitleLocator = By.xpath("//div[@class='page-title-wrapper']//h1[@class='page-title']//span[text()='Shopping Cart']");

    public double getOrderTotal() {
        WebElement orderTotalElement = driver.findElement(orderTotalLocator);
        return Double.parseDouble(orderTotalElement.getText().replace("$", "").trim());
    }
}
