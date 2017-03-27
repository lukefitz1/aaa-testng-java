package tests;

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

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tests.Base;
import pos.CategoryPage;
import pos.Database;

public class SortByPositionTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private CategoryPage cat;
	private Database db;
	JSch jsch;
	Session session;
	List<String> resultSet;
	String catName = "Test Subcategory 1";
	String catId = "";
	int categoryId = 0;
	
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
	
	@Test(alwaysRun = true, groups = { "sort_by_position" }, priority=1)
	public void goToCatPage()  {
		//cat = new CategoryPage(driver);
		db = new Database(driver);
		//cat.goToTestCategory();
		//driver.get("http://mage.dev/test-cat1/test-subcat1/");
	}
	
	@Test(alwaysRun = true, groups = { "sort_by_position" }, priority=2)
	public void sshConnection() throws JSchException {
		java.util.Properties config = new java.util.Properties(); 
        config.put("StrictHostKeyChecking", "no");
		
        jsch = new JSch();
		
        session = jsch.getSession("devstaff", "172.16.0.6");
        session.setConfig(config);
		session.setPassword("staff4nut$");
		session.connect();
		session.setPortForwardingL(5656, "127.0.0.1", 3306);
	}
	
	@Test(alwaysRun = true, groups = { "sort_by_position" }, priority=3)
	public void connectToDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//db.connectToDb("jdbc:mysql://localhost:3306/mage_db", "root", "root");
		db.connectToDb("jdbc:mysql://172.16.0.6:3306/aaa", "magento", "magento");
	}
	
	@Test(groups = { "sort_by_position" }, priority=4)
	public void dbConnectTest() throws SQLException {		
		//resultSet = db.executeQuery("select entity_id from catalog_category_entity_varchar where value='" + catName + "'", 1);
		resultSet = db.executeQuery("select entity_id, value from catalog_category_entity_varchar where value='Test cat1'", 2);
		//categoryId = Integer.parseInt(resultSet.get(0));
	}
	
	@Test(groups = { "sort_by_position" }, priority=5)
	public void resultsTest() {		
		System.out.println(resultSet.get(0));
		System.out.println(resultSet.get(1));
	}
	
	/*@Test(groups = { "sort_by_position" }, priority=4)
	public void getCategoryId() throws SQLException {		
		resultSet = db.executeQuery("select entity_id from catalog_category_entity_varchar where value='" + catName + "'", 1);
		categoryId = Integer.parseInt(resultSet.get(0));
	}
	
	@Test(groups = { "sort_by_position" }, priority=5)
	public void gridCheck() {
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "sort_by_position" }, priority=6)
	public void sortDropdownCheck() {
		Assert.assertTrue(cat.sortBySelectDisplayed(), "The sort by dropdown is not displayed");
	}
	
	@Test(groups = { "sort_by_name" }, priority=6)
	public void checkSortArrowUp() {
		Assert.assertTrue(cat.sortArrowUpDisplayed(), "Arrow is not facing up");
	}
	
	@Test(groups = { "sort_by_position" }, priority=6)
	public void defaultSortCheck() {
		Assert.assertEquals(cat.checkDefaultSort(), "Position");
	}
	
	@Test(groups = { "sort_by_position" }, priority=9)
	public void sortByPosition() {
		cat.sortBy("Position");
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "sort_by_name" }, priority=12)
	public void checkSortArrowUp2() {
		Assert.assertTrue(cat.sortArrowUpDisplayed(), "Arrow is not facing up");
	}
	
	@Test(groups = { "sort_by_name" }, priority=15)
	public void checkSortUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://mage.dev/test-cat1/test-subcat1");
	}
	
	@Test(groups = { "sort_by_position" }, priority=12)
	public void validateSort() throws SQLException {		
		Assert.assertTrue(cat.validateSortByPosition(db, categoryId), "The grid is not sorted by position");
	}
	
	@Test(groups = { "sort_by_position" }, priority=18)
	public void changeSortDirection() {
		cat.clickSortDirectionButton();
		Assert.assertTrue(cat.productGridDisplayed(), "The product grid is not displayed");
	}
	
	@Test(groups = { "sort_by_position" }, priority=21)
	public void checkSortArrowDown() {
		Assert.assertTrue(cat.sortArrowDownDisplayed(), "Arrow is facing down");
	}
	
	@Test(groups = { "sort_by_position" }, priority=24)
	public void checkReverseSortUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://mage.dev/index.php/test-cat1/test-subcat1?dir=desc&order=position");
	}
	
	@Test(groups = { "sort_by_name" }, priority=27)
	public void validateReverseSort() {
		Assert.assertTrue(cat.validateSortByNameReverse(), "The grid is not sorted by position in descending order");
	}*/
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws SQLException {
		//db.closeDbConnection();
		session.disconnect();
		driver.quit();
	}
}
