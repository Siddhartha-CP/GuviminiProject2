package minitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.BaseClass;
import pages.AddContactPage;

public class AddContactTest extends BaseClass {

	public static WebDriver driver;
	AddContactPage addcontactpage;
	LoginTest logintst;
	private static final Logger logger = LogManager.getLogger(AddContactTest.class);

	@Test(priority = 1, groups = { "sanity", "regression" })
	public void addContacts() {
		logger.info("**** Starting Test: addContacts ****");

		addcontactpage = new AddContactPage(BaseClass.driver);

		String email = BaseClass.get("login.email");
		String password = BaseClass.get("login.password");

		doLogin(email, password);

		for (int i = 0; i < 3; i++) {
			try {
				Assert.assertTrue(addcontactpage.clickAddNewContact());
				logger.info("Add New Contact button clicked successfully");
			} catch (Exception e) {
				logger.error("Error while clicking Add New Contact", e);
			}
			String str = addcontactpage.randomString();
			addcontactpage.enterFname(str);
			String str1 = addcontactpage.randomString();
			addcontactpage.enterLname(str1);
			addcontactpage.enterDOB("2010-10-10");
			addcontactpage.enterEmail("word@test.com");
			addcontactpage.enterphone("+918553702954");
			addcontactpage.enterstreetOne("Wdc 123");
			addcontactpage.enterstreetTwo("san 123");
			addcontactpage.enterCity("new dheli");
			addcontactpage.enterState("New York");
			addcontactpage.enterPostCode("3245");
			addcontactpage.enterCountry("US");
			logger.info("Filled contact form with test data");

			Assert.assertTrue(addcontactpage.clickSubmitContact());
			logger.info("Contact submitted successfully");
		}
		Assert.assertTrue(addcontactpage.clickSubmitContact());
	}

	@Test(priority = 2, groups = { "sanity", "regression" })
	public void testSortAndExtension() {
		logger.info("**** Starting Test: testSortAndExtension ****");
		Assert.assertTrue(addcontactpage.isLastNameSorted(), "Contacts are NOT sorted alphabetically by last name");
		Assert.assertTrue(addcontactpage.fetchPhoneDisplayed(), "Phone extension is not present");
	}

	@Test(priority = 3, groups = { "regression" })
	public void addOptionalDetails() {
		logger.info("**** Starting Test: addOptionalDetails ****");
		try {
			Assert.assertTrue(addcontactpage.clickAddNewContact());
		} catch (Exception e) {
			logger.error("Error while clicking Add New Contact", e);
		}

		addcontactpage.enterDOB("2010-10-10");
		addcontactpage.enterEmail("wort@test.com");
		addcontactpage.enterphone("+918554702786");
		addcontactpage.enterstreetOne("wer 123");
		addcontactpage.enterstreetTwo("xer 123");
		addcontactpage.enterCity("new kolkata");
		addcontactpage.enterState("New York");
		addcontactpage.enterPostCode("18765");
		addcontactpage.enterCountry("US");

		Assert.assertTrue(addcontactpage.clickSubmitContact());
		logger.info("Submitted form without first and last name");

		String exp = "Contact validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required.";
		String act = addcontactpage.getAdduserErrorMsg();
		Assert.assertEquals(act, exp);
		addcontactpage.clickCancel();
	}

	@Test(priority = 4, invocationCount = 2)
	public void AddDuplicateContacts() {
		logger.info("**** Starting Test: AddDuplicateContacts ****");
		try {
			Assert.assertTrue(addcontactpage.clickAddNewContact());
		} catch (Exception e) {
			logger.error("Error while clicking Add New Contact", e);
		}
		addcontactpage.enterFname("MARK");
		addcontactpage.enterLname("SP");
		addcontactpage.enterEmail("test@test.com");
		addcontactpage.enterphone("+919404360836");

		Assert.assertTrue(addcontactpage.clickSubmitContact());
		logger.info("Duplicate contact submitted successfully");
	}

	@Test(priority = 5, groups = { "regression" })
	public void testInvalidDate() {
		logger.info("**** Starting Test: testInvalidDate ****");
		try {
			Assert.assertTrue(addcontactpage.clickAddNewContact());
		} catch (Exception e) {
			logger.error("Error while clicking Add New Contact", e);
		}
		addcontactpage.enterFname("MANU");
		addcontactpage.enterLname("HMMM");
		addcontactpage.enterDOB("2010-10-101");
		Assert.assertTrue(addcontactpage.clickSubmitContact());

		String expected = "Contact validation failed: birthdate: Birthdate is invalid";
		String actual = addcontactpage.getDateDoberrormsg();
		Assert.assertEquals(actual, expected);
		logger.info("DOB validation message verified");
	}

	@Test(priority = 6, groups = { "sanity" })
	public void testEmptyFields() {
		logger.info("**** Starting Test: testEmptyFields (expected failure for screenshot) ****");
		addcontactpage.clickCancel();
		try {
			Assert.assertTrue(addcontactpage.clickAddNewContact());
		} catch (Exception e) {
			logger.error("Error while clicking Add New Contact", e);
		}
		addcontactpage.enterFname("MANU");
		addcontactpage.enterLname("HMMM");
		addcontactpage.enterDOB("");
		addcontactpage.enterEmail("");
		addcontactpage.enterState("");
		System.out.println("App allows saving empty optional fields, no error shown.");
		Assert.assertFalse(addcontactpage.clickSubmitContact()); // Failing intentionally for screnshot on failure
	}
}
