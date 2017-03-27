package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.Region;
import com.applitools.eyes.Eyes;
import pos.Header;
import pos.Homepage;

public class MobileHeaderVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	
	//final int URL_BAR_SIZE = 65; //This is the size of the URL bar, may need adjustments according to the device.
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice", "porientation"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("width") int pwidth, @Optional("height") int pheight,  @Optional("device") String pdevice,  @Optional("orientation") String porientation) throws MalformedURLException {		
		browser = pbrowser;
		version = pversion;
		os = pos;
		capabilities = new DesiredCapabilities();

		if (pdevice.equalsIgnoreCase("iPad 2") || pdevice.equalsIgnoreCase("iPhone 6") || pdevice.equalsIgnoreCase("iPad Air")) {
			System.out.println("Mobile iOS tests");
			width = pwidth;
			height = pheight;
			device = pdevice;
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, setPlatform(pos));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);
			
			/*capabilities.setBrowserName(pbrowser);
			capabilities.setVersion("9.0.3");
			capabilities.setPlatform(setPlatform("mac"));*/
			
//			if (porientation.equalsIgnoreCase("landscape")) {
//				capabilities.setCapability("orientation", "LANDSCAPE");
//			}
//			else if (porientation.equalsIgnoreCase("portrait")) {
//				capabilities.setCapability("orientation", "PORTRAIT");
//			}
			
			if (pdevice.equalsIgnoreCase("iPad 2") || pdevice.equalsIgnoreCase("iPad Air")) {
				System.out.println("Tablet safari tests");
				driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				
//				driver = new RemoteWebDriver(new URL(purl), capabilities);
//				driver.manage().window().setSize(new Dimension(pwidth, pheight));
			}
			else if (pdevice.equalsIgnoreCase("iPhone 6")) {
				System.out.println("Mobile (phone) safari tests");
				//driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				
//				driver = new RemoteWebDriver(new URL(purl), capabilities);
//				driver.manage().window().setSize(new Dimension(pwidth, pheight));
			}
			
		}
		else if (pdevice.equalsIgnoreCase("Samsung Galaxy S5 - 4.4.4 - API 19 - 1080x1920")) {
			
		}
	}
	
	@Test(alwaysRun = true, groups = { "header_visual" }, priority=0) 
	public void eyesSetup() {
		driver = eyes.open(driver, "AAA mobile header visual tests", "AAA mobile header tests in " + browser + " " + version + " on " + os + " " + device);
		eyes.setForceFullPageScreenshot(true);
		//eyes.setImageCut(new FixedCutProvider(URL_BAR_SIZE, 0, 0, 0));
	}
	
	@Test(alwaysRun = true, groups = { "header_visual" }, priority=1)
	public void goToHomePage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "header_visual" }, priority=3)
	public void heroCheck() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");	
	}
	
	@Test(groups = { "header_visual" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "The header is not displayed");	
	}
	
	@Test(groups = { "header_visual" }, priority=6)
	public void homeUrlCheck() {
		Assert.assertEquals(driver.getCurrentUrl(), hp.getBaseUrl(), "Urls do not match");
	}
	
	@Test(groups = { "header_visual" }, priority=9)
	public void headerScreenshot() {
		//WebElement e = driver.findElement(header.getHeader());
		//this.checkRegWithShift(eyes, e, 100, "Mobile header screenshot");

		//eyes.checkRegion(header.getHeader(), "Header screenshot");
	}
	
	@Test(groups = { "header_visual" }, priority=12)
	public void verifySignInDropdownClosed() {
		Assert.assertTrue(header.signInDropdownNotDisplayed(), "Sign in dropdown is displayed");
	}
	
	/*@Test(groups = { "header_visual" }, priority=15)
	public void clickSignInLink() throws InterruptedException {
		header.clickSignInLink();
	}
	
	@Test(groups = { "header_visual" }, priority=18)
	public void verifySignInDropdownOpen() {
		Assert.assertTrue(header.signInDropdownDisplayed(), "Sign in dropdown not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=21)
	public void checkMobileCreateAccountButton() {
		Assert.assertTrue(header.mobileCreateAccountButtonDisplayed(), "Create account button not displayed in mobile header");
	}
	
	@Test(groups = { "header_visual" }, priority=24)
	public void clearInputFields() {
		header.clearSignInMobileFields();
	}
	
	@Test(groups = { "header_visual" }, priority=27)
	public void signInDropdownScreenshot() {
		WebElement signIn = driver.findElement(header.getMobileSignInForm());
		this.checkRegWithShift(eyes, signIn, 0, "Sign in dropdown");
	}

	@Test(groups = { "header_visual" }, priority=30)
	public void openMobileRegisterForm() {
		header.clickMobileCreateAccountButton();
	}
	
	@Test(groups = { "header_visual" }, priority=33)
	public void checkMobileRegisterForm() {
		Assert.assertTrue(header.mobileCreateAccountFormDisplayed(), "Create account form not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=36)
	public void checkMobileSignInButton() {
		Assert.assertTrue(header.mobileSignInButtonDisplayed(), "Sign in / register button not displayed in mobile header");
	}
	
	@Test(groups = { "header_visual" }, priority=39)
	public void mobileRegisterDropdownScreenshot() {
		WebElement e = driver.findElement(header.getMobileCreateAccountForm());
		this.checkRegWithShift(eyes, e, 0, "Create an account dropdown");
	}
	
	@Test(groups = { "header_visual" }, priority=42)
	public void openMobileSignInForm() {
		header.clickMobileSignInButton();
	}
	
	@Test(groups = { "header_visual" }, priority=45)
	public void mobileSignInFormIsDisplayed() {
		Assert.assertTrue(header.mobileSignInFormDisplayed(), "Sign in form is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=48)
	public void mobileSignInButtonDisplayed() {
		Assert.assertTrue(header.mobileCreateAccountButtonDisplayed(), "The mobile create an account button is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=51)
	public void mobileLoginButtonDisplayed() {
		Assert.assertTrue(header.signInButtonDisplayed(), "The mobile sign in button is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=54)
	public void clickSignInToShowValidationErrors() {
		header.clickSignInButton();
		header.clickMobileSignInLabel();
	}
	
	@Test(groups = { "header_visual" }, priority=57)
	public void validationErrorsSignInScreenshot() {
		WebElement signInForm = driver.findElement(header.getSignInForm());
		this.checkRegWithShift(eyes, signInForm, 0, "Sign in form with validation errors");
	}
	
	@Test(groups = { "header_visual" }, priority=60)
	public void openMobileRegisterForm2() {
		header.clickMobileCreateAccountButton();
	}
	
	@Test(groups = { "header_visual" }, priority=63)
	public void checkMobileRegisterForm2() {
		Assert.assertTrue(header.mobileCreateAccountFormDisplayed(), "Create account form not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=66)
	public void checkMobileSignInButton2() {
		Assert.assertTrue(header.mobileSignInButtonDisplayed(), "Sign in / register button not displayed in mobile header");
	}
	
	@Test(groups = { "header_visual" }, priority=69)
	public void clickRegisterToShowValidationErrors() {
		header.clickRegisterButton();
		header.clickMobileCreateAccountLabel();
	}
	
	@Test(groups = { "header_visual" }, priority=72)
	public void validationErrorsRegisterScreenshot() {
		WebElement registerForm = driver.findElement(header.getRegisterForm());
		this.checkRegWithShift(eyes, registerForm, 0, "Register form with validation errors");
	}
	
	@Test(groups = { "header_visual" }, priority=75)
	public void clickMobileSignInButton() {
		header.clickMobileSignInButton();
	}
	
	@Test(groups = { "header_visual" }, priority=78)
	public void mobileSignInFormIsDisplayed2() {
		Assert.assertTrue(header.mobileSignInFormDisplayed(), "Sign in form is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=81)
	public void mobileSignInButtonDisplayed2() {
		Assert.assertTrue(header.mobileCreateAccountButtonDisplayed(), "The mobile create an account button is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=84)
	public void loginIn() {
		header.fillSignInForm("lukefitztest@gmail.com", "pass4luke");
		header.clickSignInButton();
	}
	
	@Test(groups = { "header_visual" }, priority=87)
	public void heroCheck1() throws InterruptedException {
		//Tossing in an sleep here, since safari likes to ignore waits
		Thread.sleep(3000);
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=90)
	public void closedLoggedInDropdownCheck() {
		Assert.assertTrue(header.mobileLoggedInDropdownClosed(), "The logged in menu is displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=93)
	public void openLoggedInDropdown() {
		header.clickToOpenLoggedInDropdown();
	}
	
	@Test(groups = { "header_visual" }, priority=96)
	public void openLoggedInDropdownCheck() {
		Assert.assertTrue(header.mobileLoggedInDropdownOpen(), "The logged in menu is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=99)
	public void openLoggedInDropdownLabel() {
		Assert.assertTrue(header.mobileLoggedInDropdownLabelDisplayed(), "The logged in menu label is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=102)
	public void loggedInHeaderScreenshot() {
		WebElement e = driver.findElement(header.getLoggedInDropdown());
		this.checkRegWithShift(eyes, e, 0, "Logged in user dropdown");
	}
	
	@Test(groups = { "header_visual" }, priority=105)
	public void openLoggedInUserDropdown() {
		header.clickToCloseLoggedInDropdown();
	}*/
	
	/*
	 * 
	 * Need to log out here, but on mobile, there is no logout button right now (change order)
	 * 
	 */
	
	@Test(alwaysRun = true, groups = { "header_visual" }, priority=120)
	public void compareScreenshots() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}
}
