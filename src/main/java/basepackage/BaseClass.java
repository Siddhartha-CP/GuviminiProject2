package basepackage;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pages.Homepage;
import utils.ExtentManager;






public class BaseClass {

	public static WebDriver driver;
	protected static Properties prop;
	public Logger logger;
	
	
	@BeforeClass(alwaysRun = true)
	@Parameters("Browser")
	public void setup(String brow) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());
		
		FileReader file = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\resources\\config.properties");

		
			 prop = new Properties();
			prop.load(file);
		
	

			switch (brow.toLowerCase()) {
	        case "chrome":
	        	 ChromeOptions options = new ChromeOptions();
	        	    options.addArguments("--disable-blink-features=AutomationControlled");
	        	    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	        	    options.setExperimentalOption("useAutomationExtension", false);
	        	    options.addArguments("--disable-notifications");
	        	    options.addArguments("--disable-popup-blocking");

	        	    Map<String, Object> prefs = new HashMap<>();
	        	    prefs.put("profile.default_content_setting_values.notifications", 2);
	        	    prefs.put("credentials_enable_service", false);
	        	    prefs.put("profile.password_manager_enabled", false);
	        	    prefs.put("autofill.profile_enabled", false);  // ⛔ DISABLE ADDRESS PROMPT
	        	    prefs.put("autofill.address_enabled", false);  // optional redundancy

	        	    options.setExperimentalOption("prefs", prefs);
	            driver = new ChromeDriver(options);
	            break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid Browser Name: " + brow);
		}

		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
	
	public void doLogin(String email, String password) {
		Homepage homepage = new Homepage(driver);
		homepage.getloginEmail(email);
		homepage.getloginPassword(password);
		homepage.clickSubmitBtn();
		
		
	}
	
	public static String get(String key) {
		if (prop == null) {
			throw new IllegalStateException("Properties not loaded. Make sure setup() is executed before accessing config.");
		}
		return prop.getProperty(key);
	}
	
	public static void takeScreenshot(String testName) {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String basePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
			File directory = new File(basePath);
			if (!directory.exists()) {
			    directory.mkdirs();
			}
			File destFile = new File(basePath + testName + ".png");

			FileHandler.copy(screenshot, destFile);
			System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
		} catch (Exception e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
		

	}
	 @AfterMethod(alwaysRun = true)
	    public void screenshot(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	        	System.out.println("Test Failed: Capturing screenshot...");
	            String testName = result.getName();  // Get the failed test case name
	            Utility.captureScreenshot(driver, testName);
	        }
	    }

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	@AfterTest
    public void tearDownTest() {
        ExtentManager.flush();
       
    }
}
