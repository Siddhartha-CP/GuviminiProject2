package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddContactPage extends Basepage {

	public AddContactPage(WebDriver driver) {
		super(driver);

	}
	
	@FindBy(xpath = "//table[@class='contactTable']/tr/td[5]")
	private List<WebElement> fetchPhone;
	
	@FindBy(xpath = "//table[@class='contactTable']/tr/td[2]")
	private List<WebElement> tableList;
	
	@FindBy(xpath = "//h1[text()='Contact List']")
	private WebElement contactListTxt;

	@FindBy(css = "#logout")
	private WebElement logoutBtn;

	@FindBy(css = "#add-contact")
	private WebElement addNewContact;

	@FindBy(xpath = "//input[@id='firstName']")
	private WebElement conatctFname;

	@FindBy(css = "#lastName")
	private WebElement conatctLname;

	@FindBy(css = "#birthdate")
	private WebElement birthDate;

	@FindBy(css = "#email")
	private WebElement contactEmail;

	@FindBy(css = "#phone")
	private WebElement phone;

	@FindBy(css = "#street1")
	private WebElement streetOne;

	@FindBy(css = "#street2")
	private WebElement streetTwo;

	@FindBy(css = "#city")
	private WebElement city;

	@FindBy(css = "#stateProvince")
	private WebElement state;

	@FindBy(css = "#postalCode")
	private WebElement postalCode;

	@FindBy(css = "#country")
	private WebElement country;

	@FindBy(css = "#submit")
	private WebElement contactSubmitBtn;

	@FindBy(xpath = "//span[contains(text(),'Path `firstName` is required')]")
	private WebElement addusererrormsg;

	@FindBy(css = "#cancel")
	private WebElement cancelBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Birthdate is invalid')]")
	private WebElement dateDoberrormsg;
	
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public boolean iscontactListTxDispayed() {

		return contactListTxt.isDisplayed();
	}

	public void isLogout() {
		logoutBtn.click();
	}

	public boolean clickAddNewContact() {
		
		try {
			Thread.sleep(2000);
			addNewContact.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public void enterFname(String fname) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(conatctFname));
			conatctFname.sendKeys(fname);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			driver.navigate().refresh();
			wait.until(ExpectedConditions.elementToBeClickable(conatctFname));
			conatctFname.sendKeys(fname);
		}
		
	}

	public void enterLname(String lanme) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(conatctLname));
			conatctLname.sendKeys(lanme);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}

	}

	public void enterDOB(String DOB) {
		wait.until(ExpectedConditions.elementToBeClickable(birthDate));
		birthDate.sendKeys(DOB);
	}

	public void enterEmail(String email) {
		contactEmail.sendKeys(email);
	}

	public void enterphone(String num) {
		phone.sendKeys(num);
	}

	public void enterstreetOne(String street) {
		streetOne.sendKeys(street);
	}

	public void enterstreetTwo(String streettwo) {
		streetTwo.sendKeys(streettwo);
	}

	public void enterCity(String cityname) {
		city.sendKeys(cityname);
	}

	public void enterState(String statename) {
		state.sendKeys(statename);
	}

	public void enterPostCode(String postcode) {
		postalCode.sendKeys(postcode);
	}

	public void enterCountry(String countrname) {
		country.sendKeys(countrname);
	}

	public boolean clickSubmitContact() {
		try {
			if (contactSubmitBtn.isEnabled()) {
				contactSubmitBtn.click();
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public void addContact(String fname,String lname,String DOB,String email,String phonenum,String street,
			String streettwo,String cityname,String statename,String postcode,String countryname) {
		conatctFname.sendKeys(fname);
		conatctLname.sendKeys(lname);
		birthDate.sendKeys(DOB);
		contactEmail.sendKeys(email);
		phone.sendKeys(phonenum);
		streetOne.sendKeys(street);
		streetTwo.sendKeys(streettwo);
		city.sendKeys(cityname);
		state.sendKeys(statename);
		postalCode.sendKeys(postcode);
		country.sendKeys(countryname);
	}
	

	public String getAdduserErrorMsg() {

		String act = addusererrormsg.getText();
		System.out.println("expcted error occured");
		return act;
	}

	public void clickCancel() {
		cancelBtn.click();
	}
	
	public String getDateDoberrormsg() {

		String act = dateDoberrormsg.getText();
		System.out.println("invalid DOB failed");
		return act;
	}

	public boolean addContact(String fname, String lname, String email) {
		// TODO Auto-generated method stub
		return false;
	}
	public String randomString() {
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < 5; i++) {  // Generates a 7-character name
	        sb.append(chars.charAt(random.nextInt(chars.length())));
	    }
	    return sb.toString().toUpperCase();
	}

	public List<String> getLastNames() {
        List<WebElement> nameElements =tableList;
        List<String> lastNames = new ArrayList<>();

        for (WebElement nameElement : nameElements) {
            String fullName = nameElement.getText();
           String fname_lname = fullName.toUpperCase();
            String[] split_lname = fname_lname.split(" ");
            if (split_lname.length > 1) {
                lastNames.add(split_lname[1]);
                
                System.out.println(lastNames);
            }
        }
        return lastNames;
    }
	 public boolean isLastNameSorted() { 
	        List<String> lastNames = getLastNames();
	        List<String> sortedLastNames = new ArrayList<>(lastNames);
	        Collections.sort(sortedLastNames);
	        
	        return lastNames.equals(sortedLastNames); 
	    }
	 
	 public boolean fetchPhoneDisplayed() {
		 for(int i=0;i<fetchPhone.size();i++) {
			 if(fetchPhone.get(i).getText().contains("+91")) {
				 return true;
			 }
		 }
		return false;
		 
			
		}

}
