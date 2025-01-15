import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.SignInPage;

public class SignInTest extends BaseTest {

    @Test
    public void userSignInTest() {
        HomePage homePage = new HomePage();
        SignInPage signInPage = homePage.clickSignInLink();
        ProductsPage productsPage = signInPage.logIntoApplication("wordword2@gmail.com", "Test@1234");
        Assert.assertTrue(productsPage.isUsernameDisplayed(), "\n Username is not displayed after signing in.");
        HomePage returnedHomePage = productsPage.clickSignOut();
        Assert.assertTrue(returnedHomePage.isDisplayed(), "\n Home page is not displayed after signing out.");
    }
}
