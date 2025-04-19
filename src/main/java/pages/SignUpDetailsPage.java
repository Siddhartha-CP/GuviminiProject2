package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpDetailsPage extends Basepage{

	public SignUpDetailsPage(WebDriver driver) {
		super(driver);
		
	}

	
	
	@FindBy(css="#firstName")
	private WebElement fNameTetxBox;
	
	@FindBy(css="#lastName")
	private WebElement lNameTetxBox;
	
	@FindBy(css="#email")
	private WebElement emailTetxBox;
	
	@FindBy(css="#password")
	private WebElement passwordTetxBox;
	
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submitBtn;
	
	
	@FindBy(xpath="//span[contains(text(),'already in use')]")
	private WebElement errorMessage;
	
	public void getfNameTetxBox(String fname) {
		fNameTetxBox.sendKeys(fname);
	}
	
	public void getlNameTetxBox(String lname) {
		lNameTetxBox.sendKeys(lname);
	}
	public void getfemailTetxBox(String email) {
		emailTetxBox.clear();
	    System.out.println(">>> Email entered: " + email); // 
	    emailTetxBox.sendKeys(email);
	}
	public void getpasswordTetxBox(String pwd) {
		passwordTetxBox.sendKeys(pwd);
	}
	public void getsubmitBtn() {
		submitBtn.click();
	}
	public boolean geterrorMessage() {
		errorMessage.isDisplayed();
		System.out.println("Email address is already in use");
		return true;
	}

	

	
}
