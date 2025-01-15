import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.RegistrationPage;

public class RegistrationTest extends BaseTest {

    @Test
    public void userCanRegisterAndSignOut() {
        HomePage homePage = new HomePage();
        RegistrationPage registrationPage = homePage.clickCreateAccountLink();

        Assert.assertEquals(registrationPage.getPageTitle(), "Create New Customer Account", "\n Page title is incorrect.");

        registrationPage.fillRegistrationForm("word", "word", "wordword10@gmail.com", "Test@1234", "Test@1234");
        ProductsPage productsPage = registrationPage.clickCreateAccountButton();

        Assert.assertTrue(productsPage.isSuccessMessageDisplayed());

        HomePage returnedHomePage = productsPage.clickSignOut();
        Assert.assertTrue(returnedHomePage.isDisplayed());
    }
}
