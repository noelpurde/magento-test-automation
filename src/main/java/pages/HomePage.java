package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By signInLink = By.linkText("Sign In");
    private By createAccountLink = By.linkText("Create an Account");

    public SignInPage clickSignInLink() {
        click(signInLink);
        return new SignInPage();
    }
    public boolean isDisplayed() {
        return find(signInLink).isDisplayed();
    }

    public RegistrationPage clickCreateAccountLink() {
        click(createAccountLink);
        return new RegistrationPage();
    }


}
