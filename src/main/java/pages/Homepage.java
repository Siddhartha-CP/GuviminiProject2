package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage extends Basepage {

	public Homepage(WebDriver driver) {
		super(driver);

	}
	
	@FindBy(xpath = "//span[contains(text(),'Incorrect')]")
	private WebElement invalidTxt;
	
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submitBtn;
	
	@FindBy(css="#email")
	private WebElement loginEmail;
	
	@FindBy(css="#password")
	private WebElement loginPassword;

	@FindBy(css = "#signup")
	private WebElement signUpBtn;

	@FindBy(xpath = "//h1[text()='Add User']")
	private WebElement addUserTxt;

	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
	
	
	public boolean issubmitBtnDispayed() {
		wait.until(ExpectedConditions.visibilityOf(submitBtn));
		return submitBtn.isDisplayed();
	}
	public boolean issubmitBtnclickable() {
		return submitBtn.isEnabled();
	}
	public boolean clickSubmitBtn() {
		submitBtn.click();
		return true;
	}
	
	public void getloginEmail(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(loginEmail));
		loginEmail.sendKeys(email);
		
	}
	
	public void getloginPassword(String pwd) {
		loginPassword.sendKeys(pwd);
	}
	
	
	public boolean isSignUpBtnDisplay() {
		return signUpBtn.isDisplayed();

	}

	public boolean isSignUpBtnclickable() {
		return signUpBtn.isEnabled();

	}

	public boolean clicksignUpBtn() {
		 signUpBtn.click();
		return true;
		
	}

	public String getAddUserText() {
		wait.until(ExpectedConditions.visibilityOf(addUserTxt));
		String actualtxt = addUserTxt.getText();

		System.out.println("Redirect to add user page");

		return actualtxt;

	}
	
	public String getInvalidTxt() {
		String actualErrortxt = invalidTxt.getText();
		return actualErrortxt;
		
	}

}
