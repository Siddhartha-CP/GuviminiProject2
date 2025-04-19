package minitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.BaseClass;
import pages.SignUpDetailsPage;
import pages.Homepage;

public class HomeTest extends BaseClass {

	public static WebDriver driver;
	Homepage homepage;
	SignUpDetailsPage adduserpage;

	private static final Logger logger = LogManager.getLogger(HomeTest.class);

	@Test(priority = 1, groups = { "sanity" })
	public void testSignUpVisibility() {
		logger.info("**** Starting Test: testSignUpVisibility ****");
		homepage = new Homepage(BaseClass.driver);
		logger.info("Intentionally failing this test case for screenshot on failure");
		Assert.assertFalse(homepage.isSignUpBtnDisplay()); // intentionally failing
	}

	@Test(priority = 2)
	public void testSignUpClickable() {
		logger.info("**** Starting Test: testSignUpClickable ****");
		Assert.assertTrue(homepage.isSignUpBtnclickable(), "Sign Up button is not clickable");
		logger.info("Sign Up button is clickable");
	}

	@Test(priority = 3)
	public void testSignUpClick() {
		logger.info("**** Starting Test: testSignUpClick ****");
		Assert.assertTrue(homepage.clicksignUpBtn(), "Failed to click Sign Up button");
		logger.info("Clicked on Sign Up button successfully");
	}

	@Test(priority = 4)
	public void testAddusertxt() {
		logger.info("**** Starting Test: testAddusertxt ****");
		String actualText = homepage.getAddUserText();
		String expectedText = "Add User";
		Assert.assertEquals(actualText, expectedText, "Add User text does not match");
		logger.info("Verified Add User text successfully");
	}

	@Test(priority = 5)
	public void testSignupUser() {
		logger.info("**** Starting Test: testSignupUser ****");
		adduserpage = new SignUpDetailsPage(BaseClass.driver);

		adduserpage.getfNameTetxBox("Siddhartha");
		adduserpage.getlNameTetxBox("c");
		adduserpage.getfemailTetxBox("Siddu@123.com");
		adduserpage.getpasswordTetxBox("Siddu@123");

		logger.info("Filled signup form with valid data");

		adduserpage.getsubmitBtn();
		boolean isErrorDisplayed = adduserpage.geterrorMessage();

		Assert.assertTrue(isErrorDisplayed, "Expected error message not displayed");
		logger.info("Error message verified after sign up submission");
	}
}
