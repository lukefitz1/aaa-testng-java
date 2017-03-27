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

public class HeaderVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice", "porientation"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("width") int pwidth, @Optional("height") int pheight,  @Optional("device") String pdevice,  @Optional("orientation") String porientation) throws MalformedURLException {		
		System.out.println("Desktop tests");
		browser = pbrowser;
		version = pversion;
		os = pos;
		width = pwidth;
		height = pheight;
		device = pdevice;
		
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		
		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));

	}
	
	@Test(alwaysRun = true, groups = { "visual" }, priority=0) 
	public void eyesSetup() {
		System.out.println("Set window size for desktop!");
		driver = eyes.open(driver, "AAA header visual tests", "AAA header tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
	}
	
	@Test(alwaysRun = true, groups = { "header_visual" }, priority=1)
	public void goToHomepage() {
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
	
	@Test(groups = { "visual" }, priority=9)
	public void headerScreenshot() {	
		eyes.checkRegion(header.getHeader(), "Header"); 	
	}
	
	@Test(groups = { "header_visual" }, priority=12)
	public void verifySignInDropdownClosed() {
		Assert.assertTrue(header.signInDropdownNotDisplayed(), "Sign in dropdown is displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=15)
	public void clickSignInLink() throws InterruptedException {
		
		if (browser.equalsIgnoreCase("MicrosoftEdge")) {
			System.out.println("This is Edge and it sucks");
			
			Assert.assertTrue(header.signInLinkExists(), "Sign in link exists");
			Assert.assertTrue(header.signInLinkDisplayed(), "Sign in link is not visible");
		}
		else {
			header.clickSignInLink();
		}
		
	}
	
	@Test(groups = { "header_visual" }, priority=18)
	public void verifySignInDropdownOpen() {
		Assert.assertTrue(header.signInDropdownDisplayed(), "Sign in dropdown not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=21)
	public void clearInputFields() {
		header.clearSignInDropdownFields();
	}
	
	@Test(groups = { "visual" }, priority=24)
	public void signInDropdownScreenshot() {
		WebElement signIn = driver.findElement(header.getOpenSignInDropdown());
		this.checkRegWithShift(eyes, signIn, 0, "Sign in dropdown");
	}

	@Test(groups = { "header_visual" }, priority=27)
	public void clickSignInToShowValidationErrors() {
		header.clickSignInButton();
		header.clickSignInLabel();
	}
	
	@Test(groups = { "visual" }, priority=30)
	public void validationErrorsSignInScreenshot() {
		WebElement signInForm = driver.findElement(header.getSignInForm());
		this.checkRegWithShift(eyes, signInForm, 0, "Sign in form with validation errors");
	}
	
	@Test(groups = { "header_visual" }, priority=33)
	public void clickRegisterToShowValidationErrors() {
		header.clickRegisterButton();
		header.clickSignInLabel();
	}
	
	@Test(groups = { "visual" }, priority=36)
	public void validationErrorsRegisterScreenshot() {
		WebElement registerForm = driver.findElement(header.getRegisterForm());
		this.checkRegWithShift(eyes, registerForm, 0, "Register form with validation errors");
	}
	
	@Test(groups = { "header_visual" }, priority=39)
	public void loginIn() {
		header.fillSignInForm("lukefitztest@gmail.com", "pass4luke");
		header.clickSignInButton();
	}
	
	@Test(groups = { "header_visual" }, priority=42)
	public void verifyLoggedInLabel() {
		Assert.assertTrue(header.loggedInLabelDisplayed(), "Not logged in successfully");
	}
	
	@Test(groups = { "header_visual" }, priority=45)
	public void heroCheck2() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=48)
	public void loggedInHeaderScreenshot() {
		eyes.checkRegion(header.getHeader(), "Header");
	}
	
	@Test(groups = { "header_visual" }, priority=48)
	public void openLoggedInUserDropdown() {
		header.clickLoggedInUserLabel();
	}
	
	@Test(groups = { "visual" }, priority=51)
	public void loggedInUserDropdownScreenshot() {
		WebElement loggedInUserDropdownMenu = driver.findElement(header.getOpenLoggedInUserDropdown());
		this.checkRegWithShift(eyes, loggedInUserDropdownMenu, 0, "Logged in user dropdown menu");
	}
	
	@Test(groups = { "header_visual" }, priority=54)
	public void checkLogoutButton() {
		Assert.assertTrue(header.logoutButtonIsDisplayed(), "Logout button is not displayed");
	}
	
	@Test(groups = { "header_visual" }, priority=57)
	public void clickLogoutButton() {
		header.clickLogoutButton();
	}
	
	@Test(groups = { "header_visual" }, priority=60)
	public void heroCheck3() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");	
	}
	
	@Test(groups = { "header_visual" }, priority=63)
	public void headerCheck2() {
		Assert.assertTrue(header.headerDisplayed(), "The header is not displayed");	
	}

	@Test(groups = { "header_visual" }, priority=66)
	public void signInLinkCheck() {
		Assert.assertTrue(header.signInLinkDisplayed(), "Sign in link is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=69)
	public void headerScreenshotAfterLogout() throws InterruptedException {
		
		if (browser.equalsIgnoreCase("safari")) {
			Thread.sleep(5000);
		}
		else {
			eyes.checkRegion(header.getHeader(), "Header after logout");
		}
		
	}
	
	@Test(groups = { "header_visual" }, priority=72)
	public void heroCheck4ForSafari() throws InterruptedException {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=75)
	public void headerScreenshotAfterLogoutForSafari() throws InterruptedException {
		
		if (browser.equalsIgnoreCase("safari")) {
			eyes.checkRegion(header.getHeader(), "Header after logout");
		}
		
	}
	
	@Test(groups = { "visual" }, priority=78)
	public void compareScreenshots() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}
}
