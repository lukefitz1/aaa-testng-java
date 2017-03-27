package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
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
import pos.Header;
import pos.Homepage;
import pos.CategoryPage;
import pos.ProductPage;
import pos.SavedItems;

import com.applitools.eyes.RectangleSize;

public class SavedItemsTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	private CategoryPage cat;
	private ProductPage prod;
	private SavedItems si;
	private int featuredItemsCount;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice", "porientation"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("width") int pwidth, @Optional("height") int pheight,  @Optional("device") String pdevice,  @Optional("orientation") String porientation) throws MalformedURLException {		
		browser = pbrowser;
		version = pversion;
		os = pos;
		capabilities = new DesiredCapabilities();
		
		if (pdevice.equalsIgnoreCase("desktop")) {
			width = pwidth;
			height = pheight;
			device = pdevice;
			
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			capabilities.setPlatform(setPlatform(pos));
			
			driver = new RemoteWebDriver(new URL(purl), capabilities);
			driver.manage().window().setSize(new Dimension(pwidth, pheight));
		}
		else if (pdevice.equalsIgnoreCase("iPad 2") || pdevice.equalsIgnoreCase("iPhone 6") || pdevice.equalsIgnoreCase("iPad Air")) {
			device = pdevice;
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);
			
			/*if (porientation.equalsIgnoreCase("landscape")) {
				capabilities.setCapability("orientation", "LANDSCAPE");
			}
			else if (porientation.equalsIgnoreCase("portrait")) {
				capabilities.setCapability("orientation", "PORTRAIT");
			}*/
			
			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}
		else if (pdevice.equalsIgnoreCase("Samsung Galaxy S5 - 4.4.4 - API 19 - 1080x1920")) {
			
		}
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=0) 
	public void eyesSetup() {
		driver = eyes.open(driver, "AAA saved items visual tests", "AAA saved items test in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=1)
	public void goToHomePage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=3)
	public void heartInHeaderCheck() {
		Assert.assertTrue(header.savedItemsHeartDisplayed(), "Saved items heart is not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=3)
	public void noSavedItemsCheck() {
		Assert.assertEquals(header.getSavedItemsCount(), "");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=6)
	public void savedItemsHeaderScreenshot1() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=9)
	public void featuredItemsCheck() {
		Assert.assertTrue(hp.featuredItemsBlockDisplayed(), "Featured Items block is not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=12)
	public void countFeaturedItems() {
		featuredItemsCount = hp.countFeaturedItems();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=15)
	public void emptyHeartCheck() {
		
		for (int i = 1; i <= featuredItemsCount; i++) {
			Assert.assertTrue(hp.emptyHeartDisplayed(), "Empty heart is not displayed");
		}
		
	}
	
	/*@Test(alwaysRun = true, groups = { "saved_items" }, priority=18)
	public void scrollToFeaturedProducts() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scroll(0, 6622)", "");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=18)
	public void emptyHeartScreenshot() {	
		WebElement emptyHeart = driver.findElement(hp.getFirstSavedItemsHeartEmpty());
		this.checkRegWithShift(eyes, emptyHeart, 0, "Empty heart screenshot");
		eyes.checkRegion(emptyHeart, "Empty heart screenshot");
	}*/
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=21)
	public void clickFirstEmptyHeart() {
		hp.clickFirstEmptyHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=24)
	public void assertHeartFilled() {
		Assert.assertTrue(hp.firstHeartFilled(), "Heart not filled after clicking");
	}
	
	/*Test(alwaysRun = true, groups = { "saved_items" }, priority=27)
	public void filledHeartScreenshot() {
		eyes.checkRegion(hp.getFirstSavedItemsHeartFull(), "Empty heart screenshot");
	}*/
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=30)
	public void assertCountDisplayed() {
		Assert.assertTrue(header.savedItemsCountDisplayed(), "Count is not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=33)
	public void assertCountAccurate() {
		Assert.assertEquals(header.getSavedItemsCount(), "1");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=36)
	public void savedItemsHeaderScreenshot2() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=39)
	public void removeSavedItem() {
		hp.clickFirstFilledHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=42)
	public void emptyHeartDisplayed() {
		Assert.assertTrue(hp.firstHeartEmpty(), "Heart is not empty");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=45)
	public void noSavedItemsCheck2() {
		Assert.assertEquals(header.getSavedItemsCount(), "");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=48)
	public void savedItemsHeaderScreenshot3() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=51)
	public void goToPLP() {
		cat = new CategoryPage(driver);
		cat.goToTestCategoryWHtml();
	}

	@Test(groups = { "sort_by_name" }, priority=54)
	public void gridCheck() {
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "sort_by_name" }, priority=57)
	public void savedItemsHeaderCheck() {
		Assert.assertTrue(header.savedItemsBlockDisplayed(), "Saved items block in header not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=60)
	public void savedItemsHeaderScreenshot4() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}

	@Test(alwaysRun = true, groups = { "saved_items" }, priority=63)
	public void assertEmptyHeartFirstProduct() {
		Assert.assertTrue(cat.firstProductEmptyHeart(), "The first product's heart is full");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=64)
	public void emptyHeartScreenshot() {
		//eyes.checkRegion(cat.getFirstProductEmptyHeart(), "Empty heart not in PLP");
		WebElement emptyHeart = driver.findElement(cat.getFirstProductEmptyHeart());
		this.checkRegWithShift(eyes,emptyHeart, 0, "Empty heart not in PLP");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=66)
	public void addToSavedItems() {
		cat.clickFirstProductEmptyHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=69)
	public void assertHeartFull() {
		Assert.assertTrue(cat.firstProductFullHeart(), "Heart is not full");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=72)
	public void assertCountDisplayed2() {
		Assert.assertTrue(header.savedItemsCountDisplayed(), "Count is not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=75)
	public void assertCountAccurate2() {
		Assert.assertEquals(header.getSavedItemsCount(), "1");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=76)
	public void filledHeartScreenshot() {
		//eyes.checkRegion(cat.getFirstProductFullHeart(), "Full heart not in PLP");
		WebElement filledHeart = driver.findElement(cat.getFirstProductFullHeart());
		this.checkRegWithShift(eyes, filledHeart, 0, "Filled heart not in PLP");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=78)
	public void savedItemsHeaderScreenshot5() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=81)
	public void removeFromSavedItems() {
		cat.clickFirstProductFullHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=84)
	public void assertHeartEmpty() {
		Assert.assertTrue(cat.firstProductEmptyHeart(), "Heart is full");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=87)
	public void headerCountAccurate() {
		Assert.assertEquals(header.getSavedItemsCount(), "");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=90)
	public void savedItemsHeaderScreenshot6() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=93)
	public void goToProductPage() {
		prod = new ProductPage(driver);
		prod.goToSimpleTestProduct();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=96)
	public void addToCartCheck() {
		Assert.assertTrue(prod.simpleProductAddToCartButtonPresent(), "Add to cart button not present");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=99)
	public void savedItemsBlockInHeaderCheck() {
		Assert.assertTrue(header.savedItemsBlockDisplayed(), "Saved items block in header not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=102)
	public void savedItemsBlockInHeaderScreenshot() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=105)
	public void emptyHeartCheck2() {
		Assert.assertTrue(prod.emptyHeartDisplayed(), "Empty heart not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=108)
	public void emptyHeartScreenshot2() {
		WebElement e = driver.findElement(prod.getEmptyHeart());
		this.checkRegWithShift(eyes, e, 0, "Empty heart product page");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=111)
	public void addToSavedItems2() {
		prod.clickEmptyHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=114)
	public void assertItemAdded() {
		Assert.assertTrue(prod.filledHeartDisplayed(), "Filled heart not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=117)
	public void savedItemsHeaderAccurate2() {
		Assert.assertEquals(header.getSavedItemsCount(), "1");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=120)
	public void savedItemsBlockInHeaderScreenshot2() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=123)
	public void filledHeartScreenshot2() {
		WebElement e = driver.findElement(prod.getFilledHeart());
		this.checkRegWithShift(eyes, e, 0, "Filled heart product page");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=126)
	public void removeFromSavedItems2() {
		prod.clickFilledHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=129)
	public void assertItemRemoved() {
		Assert.assertTrue(prod.emptyHeartDisplayed(), "Empty heart not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=132)
	public void savedItemsHeaderAccurate3() {
		Assert.assertEquals(header.getSavedItemsCount(), "");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=135)
	public void savedItemsBlockInHeaderScreenshot3() {
		WebElement headerSI = driver.findElement(header.getSavedItemsBlock());
		this.checkRegWithShift(eyes, headerSI, 0, "No saved items in header");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=138)
	public void emptyHeartScreenshot3() {
		WebElement e = driver.findElement(prod.getEmptyHeart());
		this.checkRegWithShift(eyes, e, 0, "Empty heart product page");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=141)
	public void addToSavedItems3() {
		prod.clickEmptyHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=142)
	public void assertItemSaved() {
		Assert.assertTrue(prod.filledHeartDisplayed(), "Filled heart not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=144)
	public void goToSavedItemsPage() {
		si = header.clickSavedItemsLink();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=147)
	public void savedItemGridCheck() {
		Assert.assertTrue(si.savedItemGridDisplayed(), "Saved item grid not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=150)
	public void urlCheck() {
		Assert.assertEquals(driver.getCurrentUrl(), si.getBaseUrl() + si.getUrl());
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=153)
	public void fullHeartCheck() {
		Assert.assertTrue(si.firstItemFullHeartDisplayed(), "First item in grid does not have full heart");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=156)
	public void savedItemsGridScreenshot() {
		WebElement e = driver.findElement(si.getSavedItemGrid());
		this.checkRegWithShift(eyes, e, 0, "Saved item grid");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=159)
	public void removeItemFromSavedItems() {
		si.clickFirstItemFilledHeart();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=160)
	public void emptyHeartDisplayed2() {
		Assert.assertTrue(si.firstItemEmptyHeartDisplayed(), "Empty heart not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=162)
	public void headerCountCheck() {
		Assert.assertEquals(header.getSavedItemsCount(), "");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=165)
	public void clickToDisplayNoSavedItemsMessage() {
		header.clickSavedItemsLink();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=168)
	public void assertMessageDisplayed() {
		Assert.assertTrue(header.noSavedItemsMessageIsDisplayed(), "No saved items message not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=172)
	public void noSavedItemsMessageScreenshot() {
		WebElement e = driver.findElement(header.getNoSavedItemsMessage());
		this.checkRegWithShift(eyes, e, 0, "Saved item grid");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=175)
	public void clickToHideNoSavedItemsMessage() {
		header.clickSavedItemsLink();
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=178)
	public void assertMessageClosed() {
		Assert.assertFalse(header.noSavedItemsMessageIsDisplayed(), "No saved items message not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "saved_items" }, priority=181)
	public void compareScreenshots() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}	
	
}
