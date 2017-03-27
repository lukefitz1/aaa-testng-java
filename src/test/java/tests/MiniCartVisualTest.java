package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
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

import pos.Header;
import pos.ProductPage;

import com.applitools.eyes.RectangleSize;

public class MiniCartVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private ProductPage prod;
	private Header header;
	int x = 0;
	int numAttr = 0;
	int numAttrOptions1 = 0;
	int numAttrOptions2 = 0;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice", "porientation"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("width") int pwidth, @Optional("height") int pheight,  @Optional("device") String pdevice,  @Optional("orientation") String porientation) throws MalformedURLException {		
		System.out.println("Desktop tests");
		browser = pbrowser;
		version = pversion;
		os = pos;
		capabilities = new DesiredCapabilities();
		width = pwidth;
		height = pheight;
		device = pdevice;
		
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		
		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));

	}
	
	@Test(alwaysRun = true, groups = { "visual" }, priority=0) 
	public void eyesSetup() {
		driver = eyes.open(driver, "AAA mini cart visual tests", "AAA mini cart visual tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
		eyes.setForceFullPageScreenshot(true);
	}
	
	@Test(alwaysRun = true, groups = { "mini_cart_visual" }, priority=1)
	public void goToHomePage() {
		header = new Header(driver);
		prod = new ProductPage(driver);
		prod.goToSimpleTestProduct();
		//prod.goToConfigTestProductColor();
		//prod.goToConfigTestProductFinish();
		//prod.goToConfigTestProductColorFinish();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=3)
	public void stateCheck() {
		Assert.assertTrue(prod.simpleProductAddToCartButtonPresent(), "Add to cart button is not present");
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=6)
	public void addToCart() {
		//when navigating to new page, the command that takes you to the new page should return a page object for that new page
		header = prod.clickSimpleProductAddToCart();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=9)
	public void miniCartDisplayedCheck() {
		header.miniCartSpinnerNotDisplayed();
		Assert.assertTrue(header.miniCartDisplayed(), "Mini cart is not displayed");
	}
	
	//Wait for cart to close, as it closes automatically, and then reopen
	@Test(groups = { "mini_cart_visual" }, priority=12)
	public void waitForCartToClose() {
		Assert.assertTrue(header.miniCartClosed(), "Mini cart never closed");
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=15)
	public void openCartManually() {
		header.clickToOpenMiniCart();
	}
	
	@Test(groups = { "visual" }, priority=18)
	public void miniCartScreenshot() {
		
		/*if (browser.equalsIgnoreCase("chrome")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 10, "Mini cart block");
		}
		else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("internet explorer")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 18, "Mini cart block");
		}*/
	}
	
	@Test(groups = { "visual" }, priority=21)
	public void miniCartIconScreenshot() {
		//WebElement e = driver.findElement(header.getMiniCartHeaderIcon());
		//this.checkRegWithShift(eyes, e, 0, "Mini cart block");
	}
	
	/*@Test(groups = { "mini_cart_visual" }, priority=24)
	public void goToConfigProductPage1() {
		prod.goToConfigTestProductColor();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=27)
	public void stateCheck2() {
		Assert.assertTrue(prod.configProductAddToCartButtonPresent(), "Add to cart button is not present");
	}

	@Test(groups = { "mini_cart_visual" }, priority=30)
	public void selectAttributeOptions() {
		numAttr = prod.countProductAttributes();
		
		if (numAttr == 1) {
			System.out.println(prod.countAttributeOptions(1));
			
		}
		else if (numAttr == 2) {
			for (int i = 1; i <=2; i++) {
				System.out.println(prod.countAttributeOptions(i));
				
			}
		}
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=33)
	public void selectFirstColor() {
		prod.clickColorSwatchOption();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=36)
	public void addToCart2() {
		prod.clickConfigProductAddToCart();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=39)
	public void miniCartDisplayedCheck2() {
		header.miniCartSpinnerNotDisplayed();
		Assert.assertTrue(header.miniCartDisplayed(), "Mini cart is not displayed");
	}
	
	//Wait for cart to close, as it closes automatically, and then reopen
	@Test(groups = { "mini_cart_visual" }, priority=42)
	public void waitForCartToClose2() {
		Assert.assertTrue(header.miniCartClosed(), "Mini cart never closed");
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=45)
	public void openCartManually2() {
		header.clickToOpenMiniCart();
	}
	
	@Test(groups = { "visual" }, priority=48)
	public void miniCartScreenshot2() {
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 10, "Mini cart block");
		}
		else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("internet explorer")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 18, "Mini cart block");
		}
	}
	
	@Test(groups = { "visual" }, priority=51)
	public void miniCartIconScreenshot2() {
		WebElement e = driver.findElement(header.getMiniCartHeaderIcon());
		this.checkRegWithShift(eyes, e, 0, "Mini cart block");
	}
	
	
	@Test(groups = { "mini_cart_visual" }, priority=54)
	public void goToConfigProductPage2() {
		prod.goToConfigTestProductFinish();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=57)
	public void stateCheck3() {
		Assert.assertTrue(prod.configProductAddToCartButtonPresent(), "Add to cart button is not present");
	}

	@Test(groups = { "mini_cart_visual" }, priority=60)
	public void selectAttributeOptions2() {
		numAttr = prod.countProductAttributes();
		
		if (numAttr == 1) {
			System.out.println(prod.countAttributeOptions(1));
			
		}
		else if (numAttr == 2) {
			for (int i = 1; i <=2; i++) {
				System.out.println(prod.countAttributeOptions(i));
				
			}
		}
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=63)
	public void selectFirstFinish() {
		prod.selectFinishOption();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=66)
	public void addToCart3() {
		prod.clickConfigProductAddToCart();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=69)
	public void miniCartDisplayedCheck3() {
		header.miniCartSpinnerNotDisplayed();
		Assert.assertTrue(header.miniCartDisplayed(), "Mini cart is not displayed");
	}
	
	//Wait for cart to close, as it closes automatically, and then reopen
	@Test(groups = { "mini_cart_visual" }, priority=72)
	public void waitForCartToClose3() {
		Assert.assertTrue(header.miniCartClosed(), "Mini cart never closed");
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=75)
	public void openCartManually3() {
		header.clickToOpenMiniCart();
	}
	
	@Test(groups = { "visual" }, priority=78)
	public void miniCartScreenshot3() {
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 10, "Mini cart block");
		}
		else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("internet explorer")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 18, "Mini cart block");
		}
	}
	
	@Test(groups = { "visual" }, priority=81)
	public void miniCartIconScreenshot3() {
		WebElement e = driver.findElement(header.getMiniCartHeaderIcon());
		this.checkRegWithShift(eyes, e, 0, "Mini cart block");
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=84)
	public void goToConfigProductPage3() {
		prod.goToConfigTestProductColorFinish();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=87)
	public void stateCheck4() {
		Assert.assertTrue(prod.configProductAddToCartButtonPresent(), "Add to cart button is not present");
	}

	@Test(groups = { "mini_cart_visual" }, priority=90)
	public void selectAttributeOptions3() {
		numAttr = prod.countProductAttributes();
		
		if (numAttr == 1) {
			System.out.println(prod.countAttributeOptions(1));
			
		}
		else if (numAttr == 2) {
			for (int i = 1; i <=2; i++) {
				System.out.println(prod.countAttributeOptions(i));
				
			}
		}
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=93)
	public void selectFirstColor2() {
		prod.clickColorSwatchOption();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=96)
	public void selectFirstFinish2() {
		prod.selectFinishOption2();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=99)
	public void addToCart4() {
		prod.clickConfigProductAddToCart();
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=102)
	public void miniCartDisplayedCheck4() {
		header.miniCartSpinnerNotDisplayed();
		Assert.assertTrue(header.miniCartDisplayed(), "Mini cart is not displayed");
	}
	
	//Wait for cart to close, as it closes automatically, and then reopen
	@Test(groups = { "mini_cart_visual" }, priority=105)
	public void waitForCartToClose4() {
		Assert.assertTrue(header.miniCartClosed(), "Mini cart never closed");
	}
	
	@Test(groups = { "mini_cart_visual" }, priority=108)
	public void openCartManually4() {
		header.clickToOpenMiniCart();
	}
	
	@Test(groups = { "visual" }, priority=111)
	public void miniCartScreenshot4() {
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 10, "Mini cart block");
		}
		else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("internet explorer")) {
			WebElement e = driver.findElement(header.getOpenMiniCartBlock());
			this.checkRegWithLeftShift(eyes, e, 18, "Mini cart block");
		}
	}
	
	@Test(groups = { "visual" }, priority=114)
	public void miniCartIconScreenshot4() {
		WebElement e = driver.findElement(header.getMiniCartHeaderIcon());
		this.checkRegWithShift(eyes, e, 0, "Mini cart block");
	}
	
//	@Test(groups = { "mini_cart_visual" }, priority=123)
//	public void waitUp() throws InterruptedException {
//		Thread.sleep(3000);
//	}*/
	
	@Test(groups = { "visual" }, priority=126)
	public void compareScreenshots() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}
}
