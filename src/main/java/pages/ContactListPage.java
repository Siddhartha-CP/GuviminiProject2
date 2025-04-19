package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactListPage extends Basepage {

	public ContactListPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[text()='Logout']")
	private WebElement logOutBtn;
	
	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td")
	private List<WebElement> contactListTableRow;

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[2]")
	private WebElement clickoncontact;

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[2]")
	private WebElement fetchName;

	@FindBy(xpath = "//table[@class='contactTable']/tr/td[2]")
	private List<WebElement> fetchTotalNames; 

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[3]")
	private WebElement fetchDOB;

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[4]")
	private WebElement fetchEmail;

	@FindBy(xpath = "//table[@class='contactTable']/tr/td[4]")
	private List<WebElement> fetchTotalEmail;

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[5]")
	private WebElement fetchPhone;

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[6]")
	private WebElement fetchaddress;

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[7]")
	private WebElement fetchStateCityCode;

	@FindBy(xpath = "//table[@class='contactTable']/tr[1]/td[8]")
	private WebElement fetchCountry;

	@FindBy(xpath = "//button[text()='Edit Contact']")
	private WebElement clickonEdit;

	@FindBy(css = "#firstName")
	private WebElement fname;

	@FindBy(css = "#phone")
	private WebElement phone;

	@FindBy(css = "#email")
	private WebElement email;

	@FindBy(css = "#submit")
	private WebElement submitBtn;

	@FindBy(css = "#firstName")
	private WebElement getfname;

	@FindBy(css = "#phone")
	private WebElement getphone;

	@FindBy(css = "#email")
	private WebElement getemail;

	@FindBy(css = "#lastName")
	private WebElement getLastname;

	@FindBy(css = "#stateProvince")
	private WebElement getstate;

	@FindBy(xpath = "//button[text()='Return to Contact List']")
	private WebElement returnToContactBtn;

	@FindBy(xpath = "//button[text()='Delete Contact']")
	private WebElement deleteContactBtn;

	@FindBy(xpath = "//table[@class='contactTable']/tr")
	private List<WebElement> tableCount;

	@FindBy(xpath = "//table[@class='contactTable']/tr[2]/td[4]")
	private WebElement getEmailTxt;
	
	

	@FindBy(xpath = "//label[@for='street1']/following-sibling::span")
	private WebElement getStreet1DetailsPage;
	
	@FindBy(xpath = "//label[@for='stateProvince']/following-sibling::span")
	private WebElement getStateonDetailsPage;
	

//--------------------------------------------------------------	

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // explicit making global for this class

	public void getContactTable() {

		for (int i = 0; i < contactListTableRow.size(); i++) {
			System.out.println(contactListTableRow.get(i).getText());

		}
	}

	public void getClickOnContact() {
		clickoncontact.click();
	}

	public void getClickOnEditContact() {
		clickonEdit.click();
	}

	public String getFname1() throws InterruptedException {
		Thread.sleep(3000);   
		String str = fname.getText();
		System.out.println(str);
		return str;
	}

	public String geteMail() {
		String str = getemail.getText();
		return str;
	}

	public String getPhone() {
		String str = getphone.getText();
		return str;
	}

	public String getStreetAddress() {
		try {
		wait.until(ExpectedConditions.visibilityOf(getStreet1DetailsPage));
		String address =  getStreet1DetailsPage.getText();
		return address;
		}catch(TimeoutException s) {
			s.getMessage();
		}
		return null;
		
	}
	public String getStateonBfeforeEdit() {
		try {
		wait.until(ExpectedConditions.visibilityOf(getStateonDetailsPage));
		String state =  getStateonDetailsPage.getText();
		return state;
		}catch(TimeoutException s) {
			s.getMessage();
		}
		return null;
		
	}
//------------------------------------------------------

	public void sendFname(String fname) {
		try {
			
			Thread.sleep(2000); 
			getfname.clear();
			getfname.sendKeys(fname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendeMail(String email) {
		getemail.clear();
		getemail.sendKeys(email);

	}

	public void sendPhone(String phone) {
		getphone.clear();
		getphone.sendKeys(phone);
	}

	public void clkSubmit() {
		submitBtn.click();
	}

	public String getLastName() {
		return getLastname.getText();
	}

	public String getState() throws Exception {
		Thread.sleep(2000); // -----------------------wait
		return getstate.getText();
	}

	public void clickReturn() {
		returnToContactBtn.click();
	}

	public void clickDeleteBtn() {
		deleteContactBtn.click();
		driver.switchTo().alert().accept();

	}

	public int gettableCount() {
		return tableCount.size();
	}

	public String getEmailTxt() {
		return getEmailTxt.getText();
	}

//------------------------------------

	public boolean fetchNameDisplyed() {
		for (int i = 0; i < fetchTotalNames.size(); i++) {
			String actName = fetchTotalNames.get(i).getText();
			String expName = "DAVID HAUL";

			String actEmail = fetchTotalNames.get(i).getText();

			if (expName.equals(actName)) {
				return true;
			}
		}
		return false;

	}

	public String fetchDOBDispalyed() {
		return fetchDOB.getText();
	}

	public boolean fetchEmailDisplayed() {
		wait.until(ExpectedConditions.visibilityOfAllElements(fetchTotalEmail));
		for (int i = 0; i < fetchTotalEmail.size(); i++) {

			String actEmail = fetchTotalEmail.get(i).getText();
			String expEmail = "test@test.com";

			if (expEmail.equals(actEmail)) {
				return true;
			}
		}
		return false;

	}

	public String fetchAddressDisplayed() {
		return fetchaddress.getText();
	}

	public String fetchCityStateCodeDisplayed() {
		return fetchStateCityCode.getText();
	}

	public String fetchCountryDisplayed() {
		return fetchCountry.getText();
	}
	public boolean getLogOutBtn() {
		return logOutBtn.isDisplayed();
	}
	public void getLogOutBtnClick() {
		logOutBtn.click();
	}
}
