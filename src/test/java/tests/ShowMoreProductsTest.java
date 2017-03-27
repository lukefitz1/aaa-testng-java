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

import tests.Base;
import pos.CategoryPage;

public class ShowMoreProductsTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private CategoryPage cat;
	int productCount;
	String showAmount = "24";
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight) throws MalformedURLException {		
		browser = pbrowser;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));

		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}
	
	@Test(alwaysRun = true, groups = { "show_more" }, priority=0)
	public void goToCatPage() {
		cat = new CategoryPage(driver);
		cat.goToTestCategory();
	}
	
	@Test(groups = { "show_more" }, priority=1)
	public void gridCheck() {
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
		productCount = cat.countProducts();
		//System.out.println(productCount);
	}
	
	/*@Test(groups = { "show_more" }, priority=6)
	public void show() {
		cat.showMore(showAmount);
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "show_more" }, priority=12)
	public void validateShow() {
		
	}*/
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
	
	
}
