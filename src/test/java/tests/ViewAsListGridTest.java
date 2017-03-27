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

public class ViewAsListGridTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private CategoryPage cat;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight"})
	@BeforeClass(alwaysRun = true)
	public void beforeClass(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight) throws MalformedURLException {		
		browser = pbrowser;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));

		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}

	@Test(alwaysRun = true, groups = { "view_as_list_grid" }, priority=0)
	public void goToCatPage() {
		cat = new CategoryPage(driver);
		cat.goToTestCategory();
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=2)
	public void gridCheck() {
		Assert.assertTrue(cat.productGridDisplayed(), "Product grid is not displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=3)
	public void viewOptionsCheck() {
		Assert.assertTrue(cat.viewOptionsDisplayed(), "View options are not displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=3)
	public void viewOptionsCheckBottomToolbar() {
		Assert.assertTrue(cat.viewOptionsDisplayedBottomToolbar(), "View options are not displayed in bottom toolbar");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=6)
	public void testGridDefaultView() {
		Assert.assertEquals(cat.getSelectedViewOption(), "Grid");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=6)
	public void testGridDefaultViewBottomToolbar() {
		Assert.assertEquals(cat.getSelectedViewOptionBottomToolbar(), "Grid");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=9)
	public void changeToListView() {
		cat.clickViewAsList();
		Assert.assertTrue(cat.productListDisplayed(), "Product list is not displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=12)
	public void checkGridNotDisplayed() {
		Assert.assertFalse(cat.productGridDisplayed(), "Product grid is displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=15)
	public void checkSelectedViewOption() {
		Assert.assertEquals(cat.getSelectedViewOption(), "List");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=15)
	public void checkSelectedViewOptionBottomToolbar() {
		Assert.assertEquals(cat.getSelectedViewOptionBottomToolbar(), "List");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=18)
	public void checkListUrl() {
		//Assert.assertEquals(driver1.getCurrentUrl(), "http://qa.aaa.dev/index.php/test-cat1/test-subcat1?mode=list");
		Assert.assertEquals(driver.getCurrentUrl(), "http://qa.aaa.dev/index.php/test-cat1/test-subcat1?mode=list");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=21)
	public void viewOptionsCheck2() {
		Assert.assertTrue(cat.viewOptionsDisplayed(), "View options are not displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=21)
	public void viewOptionsCheck2BottomToolbar() {
		Assert.assertTrue(cat.viewOptionsDisplayedBottomToolbar(), "View options are not displayed in bottom toolbar");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=24)
	public void changeToGridView() {
		cat.clickViewAsGrid();
		Assert.assertTrue(cat.productGridDisplayed(), "Product grid is not displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=27)
	public void checkListNotDisplayed() {
		Assert.assertFalse(cat.productListDisplayed(), "Product list is displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=30)
	public void checkSelectedViewOption2() {
		Assert.assertEquals(cat.getSelectedViewOption(), "Grid");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=30)
	public void checkSelectedViewOption2BottomToolbar() {
		Assert.assertEquals(cat.getSelectedViewOptionBottomToolbar(), "Grid");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=33)
	public void checkGridUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://qa.aaa.dev/index.php/test-cat1/test-subcat1?mode=grid");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=36)
	public void viewOptionsCheck3() {
		Assert.assertTrue(cat.viewOptionsDisplayed(), "View options are not displayed");
	}
	
	@Test(groups = { "view_as_list_grid" }, priority=36)
	public void viewOptionsCheck3BottomToolbar() {
		Assert.assertTrue(cat.viewOptionsDisplayedBottomToolbar(), "View options are not displayed in bottom toolbar");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
	
}
