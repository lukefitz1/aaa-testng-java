/*
 * 
 * Not completed ... need to find a better way to scroll down a page to take a screenshot
 * 
 */

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

public class MobileFooterVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Footer footer;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight,  @Optional("optional value") String pdevice) throws MalformedURLException {		
		browser = pbrowser;
		version = pversion;
		os = pos;
		capabilities = new DesiredCapabilities();

		if (pdevice.equalsIgnoreCase("iPad 2") || pdevice.equalsIgnoreCase("iPhone 6") || pdevice.equalsIgnoreCase("iPad Air")) {
			System.out.println("Mobile iOS tests");
			width = pwidth;
			height = pheight;
			device = pdevice;
			
			/*capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, setPlatform(pos));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);*/
			
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion("9.0.3");
			capabilities.setPlatform(setPlatform("mac"));
			
//			if (porientation.equalsIgnoreCase("landscape")) {
//				capabilities.setCapability("orientation", "LANDSCAPE");
//			}
//			else if (porientation.equalsIgnoreCase("portrait")) {
//				capabilities.setCapability("orientation", "PORTRAIT");
//			}
			
			if (pdevice.equalsIgnoreCase("iPad 2") || pdevice.equalsIgnoreCase("iPad Air")) {
				System.out.println("Tablet safari tests");
				//driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				
				driver = new RemoteWebDriver(new URL(purl), capabilities);
				driver.manage().window().setSize(new Dimension(pwidth, pheight));
			}
			else if (pdevice.equalsIgnoreCase("iPhone 6")) {
				System.out.println("Mobile (phone) safari tests");
				//driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				
				driver = new RemoteWebDriver(new URL(purl), capabilities);
				driver.manage().window().setSize(new Dimension(pwidth, pheight));
			}
			
		}
		else if (pdevice.equalsIgnoreCase("Samsung Galaxy S5 - 4.4.4 - API 19 - 1080x1920")) {
			
		}
	}
	
	@Test(alwaysRun = true, groups = { "header_visual" }, priority=0) 
	public void eyesSetup() {
		driver = eyes.open(driver, "AAA footer visual tests", "AAA footer tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
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
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("scroll(0, 4706)", "");
		//footer.clickFooterContainer();
		//footer.clickTC();
	}
	
	@Test(groups = { "footer_visual" }, priority=12)
	public void footerScreenshot() {		
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("scroll(0, 4706)", "");
//		
//		eyes.checkRegion(footer.getFooter());
		
		WebElement e = driver.findElement(footer.getFooter());
		this.checkRegWithShift(eyes, e, 4706, "Create an account dropdown");
	}
	
	@Test(groups = { "footer_visual" }, priority=15)
	public void clickNewsletterSubmitToDisplayErrors() {
		footer.clickNewsletterSignupButton();
	}
	
	/*@Test(groups = { "footer_visual" }, priority=16)
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
	}*/
	
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
