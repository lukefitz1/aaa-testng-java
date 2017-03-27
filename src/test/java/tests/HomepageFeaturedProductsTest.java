package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
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

import pos.Database;
import pos.Homepage;
import pos.Admin;

public class HomepageFeaturedProductsTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Database db;
	private Homepage hp;
	private Admin admin;
	private int numFeaturedProducts;
	JSch jsch;
	Session session;
	List<String> count;
	List<String> resultSet;
	int[] featuredProducts = new int[4];
	
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
	
	@Test(alwaysRun = true, groups = { "hp_featured_products" }, priority=1)
	public void goToAdmin()  {
		db = new Database(driver);
		hp = new Homepage(driver);
		admin = new Admin(driver);
		admin.goToHomepageProducts();
	}
	
	@Test(alwaysRun = true, groups = { "hp_featured_products" }, priority=2)
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
	
	@Test(alwaysRun = true, groups = { "hp_featured_products" }, priority=3)
	public void connectToDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//db.connectToDb("jdbc:mysql://localhost:3306/mage_db", "root", "root");
		db.connectToDb("jdbc:mysql://172.16.0.6:3306/aaa", "magento", "magento");
	}
	
	@Test(alwaysRun = true, groups = { "hp_featured_products" }, priority=6)
	public void clearAllDataFromDB() throws SQLException {
		db.executeUpdate("delete from blueacorn_homepage_featureditem");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=9)
	public void loginToAdmin() {
		admin.login("luke.fitzgerald@blueacorn.com", "pass4luke");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=12)
	public void closePopup() {
		Assert.assertTrue(admin.popupDisplayed(), "Popup window didn't display");
		admin.closePopup();
	}
	
	@Test(groups = { "hp_featured_products" }, priority=15)
	public void confirmPopupClosed() {
		Assert.assertTrue(admin.popupNotDisplayed(), "Popup window didn't display");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=18)
	public void assertFeaturedProductsTabDisplayed() {
		Assert.assertTrue(admin.featuredProductsTabDisplayed(), "Featured products tab not displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=21)
	public void assertHomepageProductsTabDisplayed() {
		Assert.assertTrue(admin.homepageProductsTabsDisplayed(), "Homepage products tab not displayed");
	}

	@Test(groups = { "hp_featured_products" }, priority=27)
	public void clickFeaturedProductsTab() throws InterruptedException {
		Thread.sleep(4000);
		admin.clickFeaturedProductsTab();
	}
	
	@Test(groups = { "hp_featured_products" }, priority=30)
	public void assertFeaturedProductsGrid() throws InterruptedException {
		Thread.sleep(4000);
		Assert.assertTrue(admin.featuredProductGridDisplayed(), "Featured products grid is displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=33)
	public void selectFeaturedProducts() {
		for(int i = 1; i <=4; i++) {
			admin.clickFeaturedProductCheckbox(i);
			featuredProducts[i-1] = admin.getProdId(i);
		}
	}
	
	@Test(groups = { "hp_featured_products" }, priority=36)
	public void saveFeaturedProducts() throws InterruptedException {
		admin.clickSaveButton();
		Assert.assertTrue(admin.featuredItemsSuccessMessageDisplayed(), "Success message not displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=39)
	public void compareDbVsSelectedProductIds() throws SQLException {
		int x = featuredProducts.length;
		count = db.executeQuery("select count(*) from blueacorn_homepage_featureditem", 1);
		int y = Integer.parseInt(count.get(0));
		Assert.assertEquals(x, y);
		
		for (int i = 0; i < x; i++) {
			resultSet = db.executeQuery("select product_id from blueacorn_homepage_featureditem where product_id=" + (i+1), 1);
			int z = Integer.parseInt(resultSet.get(0));
			Assert.assertEquals(z, featuredProducts[i]);
		}
	}
	
	@Test(groups = { "hp_featured_products" }, priority=41)
	public void logout() {
		admin.logout();
	}
	
	@Test(groups = { "hp_featured_products" }, priority=42)
	public void goToHomepage() {
		hp.goToHomepage();
	}
	
	@Test(groups = { "hp_featured_products" }, priority=45)
	public void assertHero() {
		Assert.assertTrue(hp.heroDisplayed(), "Hero not displayed");
	}
	
	@Test(alwaysRun = true, groups = { "hp_featured_products" }, priority=48)
	public void assertFeaturedItems() {
		Assert.assertTrue(hp.featuredItemsBlockDisplayed(), "Hero not displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=51)
	public void countFeaturedProductsOnHp() {
		numFeaturedProducts = hp.countFeaturedItems();
		Assert.assertEquals(numFeaturedProducts, 4);
	}
	
	@Test(groups = { "hp_featured_products" }, priority=54)
	public void checkFeaturedProductsAreCorrect() throws SQLException {
		for (int i = 1; i <=numFeaturedProducts; i++) {
			String prodName = hp.getFeaturedItemName(i);
			
			resultSet = db.executeQuery("select entity_id from catalog_product_entity_varchar where value=\"" + prodName + "\"", 1);
			int x = Integer.parseInt(resultSet.get(0));
			resultSet = db.executeQuery("select count(*) from blueacorn_homepage_featureditem where product_id=" + x, 1);
			int y = Integer.parseInt(resultSet.get(0));
			Assert.assertEquals(y, 1, "Results not found");
			
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws SQLException {	
		eyes.abortIfNotClosed();
		db.closeDbConnection();
		driver.quit();
	}	
}
