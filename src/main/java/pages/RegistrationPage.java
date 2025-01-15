package pages;

import org.openqa.selenium.By;
import utilities.JavaScriptUtility;

import static utilities.JavaScriptUtility.scrollToElementJS;

public class RegistrationPage extends BasePage {

    private By pageTitle = By.xpath("//span[@class='base' and @data-ui-id='page-title-wrapper']");
    private By firstNameField = By.id("firstname");
    private By lastNameField = By.id("lastname");
    private By emailField = By.id("email_address");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("password-confirmation");
    private By createAccountButton = By.xpath("//button[@class='action submit primary' and @title='Create an Account']");

    public String getPageTitle() {
        return find(pageTitle).getText();
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password, String confirmPassword) {
        set(firstNameField, firstName);
        set(lastNameField, lastName);
        scrollToElementJS(emailField);
        set(emailField, email);
        scrollToElementJS(passwordField);
        set(passwordField, password);
        set(confirmPasswordField, confirmPassword);
    }

    public ProductsPage clickCreateAccountButton() {
        scrollToElementJS(createAccountButton);
        click(createAccountButton);
        return new ProductsPage();
    }
}
