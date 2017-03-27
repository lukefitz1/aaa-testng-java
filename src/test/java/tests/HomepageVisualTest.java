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
import pos.Homepage;

import com.applitools.eyes.RectangleSize;

public class HomepageVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	int x = 0;
	
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
		System.out.println("Set window size for desktop!");
		driver = eyes.open(driver, "AAA homepage visual tests", "AAA homepage visual tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
		eyes.setForceFullPageScreenshot(true);
	}
	
	@Test(alwaysRun = true, groups = { "mega_menu_visual" }, priority=1)
	public void goToHomePage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "hp_visual" }, priority=3)
	public void heroCheck() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");	
	}
	
	@Test(groups = { "hp_visual" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "The header is not displayed");	
	}
	
	@Test(groups = { "hp_visual" }, priority=6)
	public void homeUrlCheck() {
		Assert.assertEquals(driver.getCurrentUrl(), hp.getBaseUrl(), "Urls do not match");
	}
	
	@Test(groups = { "hp_visual"}, priority=6 )
	public void deleteHeader() {
		header.deleteHeader();
	}
	
	@Test(groups = { "visual" }, priority=9)
	public void heroScreenshot() {

		if (hp.heroDisplayed()) {			
			WebElement e = driver.findElement(hp.getHero());
			this.checkRegWithShift(eyes, e, 0, "Hero image screenshot");
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=12)
	public void video1Check() {
		Assert.assertTrue(hp.video1RowDisplayed(), "Video 1 row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=15)
	public void video1Screenshot() {
		
		if (hp.video1RowDisplayed()) {
			eyes.checkRegion(hp.getVideo1Row(), "Video 1 block screenshot");
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=18)
	public void motivationNationRowCheck() {
		Assert.assertTrue(hp.motivationNationRowDisplayed(), "Motivation nation row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=21)
	public void motivationNationScreenshot() {
		
		if (hp.motivationNationRowDisplayed()) {
			eyes.checkRegion(hp.getMotivationNationRow(), "Motivation nation row screenshot");
					
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=24)
	public void promo1x2RowCheck() {
		Assert.assertTrue(hp.promo1x2RowDisplayed(), "Promo 1x2 row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=27)
	public void promo1x2RowScreenshot() {
		
		if (hp.promo1x2RowDisplayed()) {
			eyes.checkRegion(hp.getPromo1x2Row(), "Promo 1x2 row screenshot");		
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=30)
	public void video2RowCheck() {
		Assert.assertTrue(hp.video2RowDisplayed(), "Video 2 row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=33)
	public void video2RowScreenshot() {
		
		if (hp.video2RowDisplayed()) {
			eyes.checkRegion(hp.getVideo2Row(), "Video 2 row screenshot");		
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=36)
	public void freeShippingRowCheck() {
		Assert.assertTrue(hp.freeShippingRowDisplayed(), "Free shipping row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=39)
	public void freeShippingRowScreenshot() {
		
		if (hp.freeShippingRowDisplayed()) {
			eyes.checkRegion(hp.getFreeShippingRow(), "Free shipping row screenshot");		
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=42)
	public void threeUpRowCheck() {
		Assert.assertTrue(hp.threeUpRowDisplayed(), "Three up row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=45)
	public void threeUpRowScreenshot() {
		
		if (hp.threeUpRowDisplayed()) {
			eyes.checkRegion(hp.getThreeUpRow(), "Three up row screenshot");		
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=48)
	public void promo2x2RowCheck() {
		Assert.assertTrue(hp.promo2x2RowDisplayed(), "Promo 2x2 row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=51)
	public void promo2x2RowScreenshot() {
		
		if (hp.promo2x2RowDisplayed()) {
			eyes.checkRegion(hp.getPromo2x2Row(), "Promo 2x2 row screenshot");		
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=54)
	public void chainStationRowCheck() {
		Assert.assertTrue(hp.chainStationRowDisplayed(), "Chain station row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=57)
	public void chainStationRowScreenshot() {
		
		if (hp.chainStationRowDisplayed()) {
			eyes.checkRegion(hp.getChainStationRow(), "Chain station row screenshot");		
		}
	}
	
	@Test(groups = { "hp_visual" }, priority=60)
	public void products4UpRowCheck() {
		Assert.assertTrue(hp.products4UpRowDisplayed(), "Products 4 up row is not displayed");
	}
	
	@Test(groups = { "visual" }, priority=63)
	public void products4UpRowScreenshot() {
		
		if (hp.products4UpRowDisplayed()) {
			eyes.checkRegion(hp.getProducts4UpRow(), "Products 4 up row screenshot");		
		}
	}
	
	/*@Test(groups = { "hp_visual" }, priority=77)
	public void waitUp() throws InterruptedException {
		Thread.sleep(3000);
	}*/
	
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
