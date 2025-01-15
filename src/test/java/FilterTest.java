import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

import static pages.BasePage.delay;
import static utilities.WaitUtility.explicitWaitUntilVisible;

public class FilterTest extends BaseTest {

    @Test
    public void testProductFilters() {
        HomePage homePage = new HomePage();
        SignInPage signInPage = homePage.clickSignInLink();
        ProductsPage productsPage = signInPage.logIntoApplication("wordword2@gmail.com", "Test@1234");

        JacketsPage jacketsPage = productsPage.navigateToWomenJackets();
        jacketsPage.selectRedColor();
        jacketsPage.selectFirstPriceRange();

        Assert.assertEquals(jacketsPage.getNumberOfDisplayedProducts(), 2);

        Assert.assertTrue(jacketsPage.isRedColorSelected());

        Assert.assertTrue(jacketsPage.verifyExpectedPrices());
    }

    @Test
    public void testWishList() {
        WishListPage wishListPage = new WishListPage();

        wishListPage.clickRemovePrice();
        Assert.assertEquals(wishListPage.getNumberOfDisplayedProducts(), 5);

        wishListPage.addToWishList("//li[contains(@class, 'item product product-item')]//a[contains(@class, 'product-item-link') and contains(@href, 'inez-full-zip-jacket.html')]", 1);
        Assert.assertTrue(wishListPage.isItemAddedToWishList("Inez Full Zip Jacket"));

        wishListPage.clickHere();

        wishListPage.addToWishList("//li[contains(@class, 'item product product-item')]//a[contains(@class, 'product-item-link') and contains(@href, 'riona-full-zip-jacket.html')]", 2);
        Assert.assertTrue(wishListPage.isItemAddedToWishList("Riona Full Zip Jacket"));

        wishListPage.openAccountDropdown();
        Assert.assertEquals(wishListPage.getWishListItemCount(driver), 2);

        wishListPage.openAccountDropdown();

        explicitWaitUntilVisible(10, By.xpath("//ol[@class='product-items']//li[contains(., 'Inez Full Zip Jacket')]//a[@class='product-item-photo']"));
        wishListPage.hoverAndAddToCart("//ol[@class='product-items']//li[contains(., 'Inez Full Zip Jacket')]");
        wishListPage.selectProductOptions("//div[@class='swatch-option text' and @aria-label='S' and @option-id='167']",
                "//div[@class='swatch-option color' and @aria-label='Red' and @option-id='58']");
        explicitWaitUntilVisible(10, By.id("product-addtocart-button"));
        wishListPage.clickAddToCart("//button[@id='product-addtocart-button']");


        explicitWaitUntilVisible(10, By.xpath("//ol[@class='product-items']//li[contains(., 'Riona Full Zip Jacket')]//a[@class='product-item-photo']"));
        wishListPage.hoverAndAddToCart("//ol[@class='product-items']//li[contains(., 'Riona Full Zip Jacket')]");
        wishListPage.selectProductOptions("//div[@class='swatch-option text' and @aria-label='S' and @option-id='167']",
                "//div[@class='swatch-option color' and @aria-label='Red' and @option-id='58']");
        explicitWaitUntilVisible(10, By.id("product-addtocart-button"));
        wishListPage.clickAddToCart("//button[@id='product-addtocart-button']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        delay(5000);

        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action showcart' and @href='https://magento.softwaretestingboard.com/checkout/cart/']")));
        cartLink.click();
        wait.until(ExpectedConditions.attributeToBe(cartLink, "class", "action showcart active"));

        WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action viewcart' and @href='https://magento.softwaretestingboard.com/checkout/cart/']")));

        viewCartLink.click();


        double subtotal1 = wishListPage.getPrice("//table[@id='shopping-cart-table']//tbody[1]//td[@class='col subtotal']//span[@class='price']");
        double subtotal2 = wishListPage.getPrice("//table[@id='shopping-cart-table']//tbody[2]//td[@class='col subtotal']//span[@class='price']");
        String orderTotalPath = "//tr[@class='grand totals']//td[@class='amount']//span[@class='price']";
        WebElement orderTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orderTotalPath)));

        double orderTotal = wishListPage.getPrice("//tr[@class='grand totals']//td[@class='amount']//span[@class='price']");

        Assert.assertEquals(subtotal1 + subtotal2, orderTotal,
                "The sum does not match the Total.");

        int initialCartCount = getCartItemCount(wait);

        removeProductFromCart();

        int afterFirstRemovalCount = getCartItemCount(wait);
        Assert.assertEquals(afterFirstRemovalCount, initialCartCount - 1);
        removeProductFromCart();

        WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-empty' and contains(., 'You have no items in your shopping cart.')]")));
        Assert.assertTrue(emptyCartMessage.isDisplayed(), "Cart is not empty.");
    }

    private int getCartItemCount(WebDriverWait wait) {
        WebElement cartItemCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='counter qty']//span[@class='counter-number']")
        ));
        return Integer.parseInt(cartItemCountElement.getText().trim());
    }

    private void removeProductFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[@class='action action-delete'])[1]")
        ));
        removeButton.click();
    }


}