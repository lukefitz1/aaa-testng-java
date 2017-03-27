package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
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

public class MegaMenuVisualTest extends Base {

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
		driver = eyes.open(driver, "AAA mega menu visual tests", "AAA mega menu visual tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
	}
	
	@Test(alwaysRun = true, groups = { "mega_menu_visual" }, priority=1)
	public void goToHomePage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=3)
	public void heroCheck() {
		Assert.assertTrue(hp.heroDisplayed(), "The hero block is not displayed");	
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "The header is not displayed");	
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=6)
	public void homeUrlCheck() {
		Assert.assertEquals(driver.getCurrentUrl(), hp.getBaseUrl(), "Urls do not match");
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=9)
	public void openMegaMenu() {
		x = header.hoverToOpenMegaMenu("Just In");
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=12)
	public void checkMegaMenuDisplayed() {
		Assert.assertTrue(header.megaMenuDisplayed(x));
	}
	
	@Test(groups = { "visual" }, priority=15)
	public void screenshot5Up() throws InterruptedException {	
		WebElement e = driver.findElement(header.getOpenMegaMenu(x));
		this.checkRegWithShift(eyes, e, 0, "5 up mega menu screenshot");
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=18)
	public void closeMegaMenu() {
		header.hoverToLogo();
		Assert.assertFalse(header.megaMenuDisplayed(x));
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=21)
	public void openMegaMenu2() {
		x = header.hoverToOpenMegaMenu("Jewelry");
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=24)
	public void checkMegaMenuDisplayed2() {
		Assert.assertTrue(header.megaMenuDisplayed(x));
	}
	
	@Test(groups = { "visual" }, priority=27)
	public void screenshot2Up2Img() {
		WebElement e = driver.findElement(header.getOpenMegaMenu(x));
		this.checkRegWithShift(eyes, e, 0, "2 column 2 image mega menu screenshot");
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=30)
	public void closeMegaMenu2() {
		header.hoverToLogo();
		Assert.assertFalse(header.megaMenuDisplayed(x));
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=33)
	public void openMegaMenu3() {
		x = header.hoverToOpenMegaMenu("Explore");
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=36)
	public void checkMegaMenuDisplayed3() {
		Assert.assertTrue(header.megaMenuDisplayed(x));
	}
	
	@Test(groups = { "visual" }, priority=39)
	public void screenshot1Up3Img() {
		WebElement e = driver.findElement(header.getOpenMegaMenu(x));
		this.checkRegWithShift(eyes, e, 0, "1 column 3 image mega menu screenshot");
	}
	
	@Test(groups = { "mega_menu_visual" }, priority=42)
	public void closeMegaMenu3() {
		header.hoverToLogo();
		Assert.assertFalse(header.megaMenuDisplayed(x));
	}
	
//	@Test(groups = { "mega_menu_visual" }, priority=77)
//	public void waitUp() throws InterruptedException {
//		Thread.sleep(3000);
//	}
	
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
