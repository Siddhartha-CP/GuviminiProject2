package minitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.BaseClass;
import pages.SignUpDetailsPage;
import pages.AddContactPage;
import pages.Homepage;

public class LoginTest extends BaseClass {

	public static WebDriver driver;
	Homepage homepage = new Homepage(BaseClass.driver);
	SignUpDetailsPage adduserpage;
	AddContactPage contactpage;

	private static final Logger logger = LogManager.getLogger(LoginTest.class);

	@Test(priority = 1, groups = { "sanity" })
	public void testSubumitBtnVisible() {
		logger.info("**** Starting Test: testSubumitBtnVisible ****");
		homepage = new Homepage(BaseClass.driver);
		Assert.assertTrue(homepage.issubmitBtnDispayed(), "Submit button is not visible on the login page");
		logger.info("Submit button is visible");
	}

	@Test(priority = 2, groups = { "sanity" })
	public void testSubmitClickAble() {
		logger.info("**** Starting Test: testSubmitClickAble ****");
		Assert.assertTrue(homepage.issubmitBtnclickable(), "Submit button is not clickable");
		logger.info("Submit button is clickable");
	}

	@Test(priority = 3, groups = { "sanity", "regression" })
	public void testLogin() {
		logger.info("**** Starting Test: testLogin ****");

		String email = BaseClass.get("login.email");
		String password = BaseClass.get("login.password");

		doLogin(email, password);

		Assert.assertTrue(homepage.issubmitBtnclickable(),
				"Submit button should be clickable after entering credentials");
		
		logger.info("Login successful");
	}

	@Test(priority = 5, groups = { "regression" })
	public void callTestLogout() {
		logger.info("**** Starting Test: callTestLogout ****");
		contactpage = new AddContactPage(BaseClass.driver);
		Assert.assertTrue(contactpage.iscontactListTxDispayed(), "Contact List text not displayed after login");
		contactpage.isLogout();
		logger.info("Logout executed successfully");
	}

	@Test(priority = 6, groups = { "regression" })
	public void testInvalidDetails() {
		logger.info("**** Starting Test: testInvalidDetails ****");
		homepage.getloginEmail("test");
		homepage.getloginPassword("123");
		Assert.assertTrue(homepage.clickSubmitBtn(), "Submit failed with invalid credentials");

		String expected = "Incorrect username or password";
		String actual = homepage.getInvalidTxt();

		Assert.assertEquals(actual, expected, "Invalid login error message mismatch");
		logger.info("Invalid login scenario validated successfully");
	}
}
