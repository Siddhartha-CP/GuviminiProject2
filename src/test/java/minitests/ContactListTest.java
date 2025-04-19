package minitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.BaseClass;
import pages.ContactListPage;

public class ContactListTest extends BaseClass {

	ContactListPage contactlistpage;
	LoginTest logintst;
	String beforeAddress;
	String beforeState;
	private static final Logger logger = LogManager.getLogger(ContactListTest.class);

	@Test(priority = 1, groups = { "sanity", "regression" })
	public void testContactsDisplayed() {
		logger.info("**** Starting Test: testContactsDisplayed ****");
		

		String email = BaseClass.get("login.email");
		String password = BaseClass.get("login.password");

		doLogin(email, password);
		
		contactlistpage = new ContactListPage(BaseClass.driver);

		try {
			Assert.assertTrue(contactlistpage.fetchNameDisplyed());
			Assert.assertTrue(contactlistpage.fetchEmailDisplayed());
			logger.info("Name and Email are displayed successfully.");
		} catch (Exception e) {
			logger.error("Error during contact display verification", e);
		}
	}

	@Test(priority = 2, groups = { "regression" })
	public void testEditContact() throws InterruptedException {
		logger.info("**** Starting Test: testEditContact ****");

		contactlistpage.getClickOnContact();

		beforeAddress = contactlistpage.getStreetAddress();
		beforeState = contactlistpage.getStateonBfeforeEdit();
		logger.info("Before Edit - Address: " + beforeAddress + ", State: " + beforeState);

		contactlistpage.getClickOnEditContact();
		contactlistpage.sendFname("SHARK");
		contactlistpage.sendeMail("edit@edit.com");
		contactlistpage.sendPhone("1234567891");
		contactlistpage.clkSubmit();

		String actname = contactlistpage.getFname1();
		String actemail = contactlistpage.geteMail();
		String actphone = contactlistpage.getPhone();

		Assert.assertEquals(actname, "SHARK");
		Assert.assertEquals(actemail, "edit@edit.com");
		Assert.assertEquals(actphone, "1234567891");

		logger.info("Edit contact test passed with values - Name: " + actname + ", Email: " + actemail + ", Phone: " + actphone);
	}

	@Test(priority = 3, dependsOnMethods = "testEditContact")
	public void validateOtherFields() throws Exception {
		logger.info("**** Starting Test: validateOtherFields ****");

		try {
			Assert.assertEquals(beforeAddress, contactlistpage.getStreetAddress());
			Assert.assertEquals(beforeState, contactlistpage.getStateonBfeforeEdit());
			logger.info("Other fields remained unchanged after editing contact.");
		} catch (Exception e) {
			logger.error("Field validation after editing failed", e);
		}
	}

	@Test(priority = 4, groups = { "sanity" })
	public void testDeleteConatct() throws InterruptedException {
		logger.info("**** Starting Test: testDeleteConatct ****");

		contactlistpage.clickReturn();
		logger.info("Contact list count before delete: " + contactlistpage.gettableCount());

		contactlistpage.getClickOnContact();
		contactlistpage.clickDeleteBtn();

		Assert.assertTrue(contactlistpage.fetchEmailDisplayed(), "Email not found after delete operation");
		logger.info("Contact deleted. Updated contact list count: " + contactlistpage.gettableCount());
	}

	@Test(priority = 5)
	public void testVisibilityOfLogoutBtn() {
		logger.info("**** Starting Test: testVisibilityOfLogoutBtn (expected to fail for screenshot) ****");
		Assert.assertFalse(contactlistpage.getLogOutBtn()); // failing for screenshot purpose
	}

	@Test(priority = 6,groups = { "sanity", "regression" })
	public void testClickLogoutBtn() {
		logger.info("**** Starting Test: testClickLogoutBtn ****");

		contactlistpage.getLogOutBtnClick();
		logintst = new LoginTest();
		logintst.testSubumitBtnVisible();
		logger.info("Logout successful, and Submit button is visible on login page.");
	}
}
