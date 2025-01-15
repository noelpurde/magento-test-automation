package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductsPage extends BasePage {

    private By username = By.xpath("//span[contains(text(), 'John Doe!')]");
    private By accountDropdown = By.xpath("//button[@class='action switch']");
    private By signOutOption = By.linkText("Sign Out");
    private By successMessage = By.xpath("//div[@data-ui-id='message-success']\n");
    private By womenMenuDropdown = By.id("ui-id-4");
    private By topsSubMenu = By.id("ui-id-9");
    private By jacketsOption = By.id("ui-id-11");

    public boolean isUsernameDisplayed() {
        waitForElementToBeVisible(username, 10);
        return find(username).isDisplayed();
    }

    public boolean isSuccessMessageDisplayed() {
        waitForElementToBeVisible(successMessage, 10);
        return find(successMessage).isDisplayed();
    }

    public void openAccountDropdown() {
        click(accountDropdown);
    }

    public HomePage clickSignOut() {
        openAccountDropdown();
        click(signOutOption);
        return new HomePage();
    }

    public JacketsPage navigateToWomenJackets() {
        Actions actions = new Actions(driver);

        WebElement womenMenu = find(womenMenuDropdown);
        actions.moveToElement(womenMenu).perform();

        WebElement topsMenu = find(topsSubMenu);
        actions.moveToElement(topsMenu).perform();

        waitForElementToBeVisible(jacketsOption, 10);
        click(jacketsOption);

        return new JacketsPage();
    }
}




