package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pos.Header;
import pos.Homepage;
import tests.Base;

public class HeaderAccountRegisterTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	String fn = "Luke"; 
	String ln = "Fitzgerald";
	String em = "lukefitztest8@gmail.com";
	String pw = "pass4luke";
	
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
	
	@Test(alwaysRun = true, groups = { "header_register" }, priority=1)
	public void goToHomePage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "header_register" }, priority=3)
	public void heroCheck() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");	
	}
	
	@Test(groups = { "header_register" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "The header is not displayed");	
	}
	
	@Test(groups = { "header_register" }, priority=6)
	public void homeUrlCheck() {
		Assert.assertEquals(driver.getCurrentUrl(), hp.getBaseUrl(), "Urls do not match");
	}
	
	@Test(groups = { "header_register" }, priority=9)
	public void verifySignInDropdownClosed() {
		Assert.assertTrue(header.signInDropdownNotDisplayed(), "Sign in dropdown is displayed");
	}
	
	@Test(groups = { "header_register" }, priority=12)
	public void clickSignInLink() {
		
		if (browser.equalsIgnoreCase("MicrosoftEdge")) {
			System.out.println("This is Edge and it sucks");	
			Assert.assertTrue(header.signInLinkExists(), "Sign in link exists");
			Assert.assertTrue(header.signInLinkDisplayed(), "Sign in link is not visible");
		}
		else {
			header.clickSignInLink();
		}
		
	}
	
	@Test(groups = { "header_register" }, priority=15)
	public void verifySignInDropdownOpen() {
		Assert.assertTrue(header.signInDropdownDisplayed(), "Sign in dropdown not displayed");
	}
	
	@Test(groups = { "header_register" }, priority=18)
	public void registerNewAccount() {
		header.fillRegisterForm(fn, ln, em, pw, pw);
		header.clickRegisterButton();
	}
	
	@Test(groups = { "header_register" }, priority=21)
	public void heroCheck1() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");	
	}
	
	@Test(groups = { "header_register" }, priority=24)
	public void headerCheck1() {
		Assert.assertTrue(header.headerDisplayed(), "The header is not displayed");	
	}
	
	@Test(groups = { "header_register" }, priority=33)
	public void confirmLoggedInNameDisplayed() {
		header.loggedInLabelNameDisplayed();
	}
	
	@Test(groups = { "header_register" }, priority=33)
	public void confirmLoggedInSignOutDisplayed() {
		header.loggedInLabelSignOutDisplayed();
	}
	
	@Test(groups = { "header_register" }, priority=36)
	public void confirmLoggedInOnReload() {
		Assert.assertEquals(header.getLoggedInName(), "HI, " + fn.toUpperCase() + " /", "Logged in name is not correct!");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
