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

public class SortByPriceTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private CategoryPage cat;
	
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
	
	@Test(alwaysRun = true, groups = { "sort_by_price" }, priority=0)
	public void goToCatPage() {
		cat = new CategoryPage(driver);
		cat.goToTestCategory();
	}
	
	@Test(groups = { "sort_by_price" }, priority=1)
	public void gridCheck() {
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "sort_by_price" }, priority=3)
	public void sortDropdownCheck() {
		Assert.assertTrue(cat.sortBySelectDisplayed(), "The sort by dropdown is not displayed");
	}
		
	@Test(groups = { "sort_by_price" }, priority=6)
	public void sortByPrice() throws InterruptedException {
		cat.sortBy("Price");
		
		if(browser.equalsIgnoreCase("safari")) {
			System.out.println("Safari Sucks");
			Thread.sleep(5000);
		}
		
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "sort_by_price" }, priority=9)
	public void checkSortArrowUp() {
		Assert.assertTrue(cat.sortArrowUpDisplayed(), "Arrow is facing up");
	}
	
	@Test(groups = { "sort_by_price" }, priority=12)
	public void checkSortUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://qa.aaa.dev/index.php/test-cat1/test-subcat1?dir=asc&order=price");
	}
	
	@Test(groups = { "sort_by_price" }, priority=15)
	public void validateSort() {
		Assert.assertTrue(cat.validateSortByPrice(), "The grid was not organized by price in ascending order");
	}
	
	@Test(groups = { "sort_by_price" }, priority=18)
	public void changeSortDirection() {
		cat.clickSortDirectionButton();
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "sort_by_price" }, priority=21)
	public void checkSortArrowDown() {
		Assert.assertTrue(cat.sortArrowDownDisplayed(), "Arrow is facing down");
	}
	
	@Test(groups = { "sort_by_price" }, priority=24)
	public void checkReverseSortUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://qa.aaa.dev/index.php/test-cat1/test-subcat1?dir=desc&order=price");
	}
	
	@Test(groups = { "sort_by_price" }, priority=27)
	public void validateReverseSort() {
		Assert.assertTrue(cat.validateSortByPriceReverse(), "The grid was not organized by price in descending order");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
	
}
