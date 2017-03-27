package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.applitools.eyes.RectangleSize;
import pos.Footer;
import pos.Homepage;

public class FooterVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Footer footer;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight,  @Optional("optional value") String pdevice) throws MalformedURLException {		
		browser = pbrowser;
		version = pversion;
		width = pwidth;
		height = pheight;
		device = pdevice;
		os = pos;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));

		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}
	
	@Test(alwaysRun = true, groups = { "header_visual" }, priority=0) 
	public void eyesSetup() {
		driver = eyes.open(driver, "AAA footer visual tests", "AAA footer tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
		//eyes.setForceFullPageScreenshot(true);
	}
	
	@Test(alwaysRun = true, groups = { "footer_visual" }, priority=1)
	public void goToHomePage() {
		footer = new Footer(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "footer_visual" }, priority=3)
	public void heroCheck() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");	
	}
	
	@Test(groups = { "footer_visual" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(footer.footerDisplayed(), "The footer is not displayed");	
	}
	
	@Test(groups = { "footer_visual" }, priority=6)
	public void homeUrlCheck() {
		Assert.assertEquals(driver.getCurrentUrl(), hp.getBaseUrl(), "Urls do not match");
	}
	
	@Test(groups = { "footer_visual" }, priority=9)
	public void scrollToFooter() {
	
		if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("11")) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, 8177)", "");
		}
		else if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("10")) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, 7406)", "");
		}
		else if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("9")) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, 7407)", "");
		}
		else {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("scroll(0, 7558)", "");
		}
		
	}
	
	@Test(groups = { "footer_visual" }, priority=12)
	public void footerScreenshot() {		

		if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("11")) {
			eyes.checkRegion(footer.getFooter());
		}
		else if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("10")) {
			WebElement foot = driver.findElement(footer.getFooter());
			this.checkRegWithShift(eyes, foot, 0, "Footer");
		}
		else if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("9")) {
			eyes.checkRegion(footer.getFooter());
		}
		else {
			WebElement foot = driver.findElement(footer.getFooter());
			this.checkRegWithShift(eyes, foot, 0, "Footer");
		}

	}
	
	@Test(groups = { "footer_visual" }, priority=15)
	public void clickNewsletterSubmitToDisplayErrors() {
		footer.clickNewsletterSignupButton();
	}
	
	@Test(groups = { "footer_visual" }, priority=16)
	public void requiredFieldMessageDisplayed() {
		Assert.assertTrue(footer.requiredFieldMessageDisplayed(), "Required field error message not displayed");
	}
	
	@Test(groups = { "footer_visual" }, priority=18)
	public void newsletterValidationErrorScreenshot() {
		
		if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("10")) {
			System.out.println("Newsletter Validation error screenshot in IE10");

			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, 7391)", "");
			
			eyes.checkRegion(footer.getFooter());
		}
		else if (browser.equalsIgnoreCase("internet explorer") && version.equalsIgnoreCase("9")) {
			System.out.println("Newsletter Validation error screenshot in IE9");
			
			eyes.checkRegion(footer.getFooter());
			
		}
		else {
			System.out.println("Newsletter Validation error screenshot in a browser other than IE9, IE10");
			
			WebElement news = driver.findElement(footer.getFooter());
			this.checkRegWithShift(eyes, news, 0, "Newsletter validation error - required field");
		}
		
	}
	
	@Test(groups = { "footer_visual" }, priority=21)
	public void enterEmailAlreadyInUse() {
		footer.enterEmailForNewsletter("lukefitztest@gmail.com");
		footer.clickNewsletterSignupButton();
	}
	
	@Test(groups = { "footer_visual" }, priority=22)
	public void emailAlreadyInUseMessageDisplayed() {
		Assert.assertTrue(footer.emailAlreadyInUseMessageDisplayed(), "Email already in use error message not displayed");
	}
	
	@Test(groups = { "footer_visual" }, priority=24)
	public void newsletterValidationErrorScreenshot2() {
		WebElement news = driver.findElement(footer.getFooter());
		this.checkRegWithShift(eyes, news, 0, "Newsletter validation error - email already in use");
	}
	
	@Test(groups = { "footer_visual" }, priority=27)
	public void clearNewsletterField() {
		footer.clearNewsletterField();
	}
	
	@Test(groups = { "footer_visual" }, priority=30)
	public void enterValidEmail() {
		footer.enterEmailForNewsletter("lukefitztest9@gmail.com");
		footer.clickNewsletterSignupButton();
	}
	
	@Test(groups = { "footer_visual" }, priority=33)
	public void waitForNewsletterToSubmit() {
		Assert.assertTrue(footer.newsletterSubmitComplete(), "Loader still displayed after 10 seconds");
	}
	
	@Test(groups = { "footer_visual" }, priority=36)
	public void emailSuccessMessageDisplayed() {
		Assert.assertTrue(footer.emailSuccessMessageDisplayed(), "Email success message not displayed");
	}
	
	@Test(groups = { "footer_visual" }, priority=39)
	public void newsletterSuccess() {
		WebElement news = driver.findElement(footer.getFooter());
		this.checkRegWithShift(eyes, news, 0, "Newsletter success message");
	}
	
	@Test(alwaysRun = true, groups = { "footer_visual" }, priority=54)
	public void compareScreenshots() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}
}
