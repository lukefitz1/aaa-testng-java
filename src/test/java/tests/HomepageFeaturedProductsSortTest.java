package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

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

import pos.Admin;
import pos.Database;
import pos.Homepage;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class HomepageFeaturedProductsSortTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Database db;
	private Homepage hp;
	private Admin admin;
	private int count;
	JSch jsch;
	Session session;
	List<String> ct;
	List<String> resultSet;
	int[] prodIdSort = new int[8];
	
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
	
	@Test(alwaysRun = true, groups = { "hp_featured_products" }, priority=4)
	public void clearAllDataFromDB() throws SQLException {
		ct = db.executeQuery("select count(*) from blueacorn_homepage_featureditem", 1);
		count = Integer.parseInt(ct.get(0));
	}
	
	@Test(groups = { "hp_featured_products" }, priority=6)
	public void loginToAdmin() {
		admin.login("luke.fitzgerald@blueacorn.com", "pass4luke");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=9)
	public void closePopup() {
		Assert.assertTrue(admin.popupDisplayed(), "Popup window didn't display");
		admin.closePopup();
	}
	
	@Test(groups = { "hp_featured_products" }, priority=12)
	public void confirmPopupClosed() {
		Assert.assertTrue(admin.popupNotDisplayed(), "Popup window didn't display");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=15)
	public void assertFeaturedProductsTabDisplayed() {
		Assert.assertTrue(admin.featuredProductsTabDisplayed(), "Featured products tab not displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=18)
	public void assertHomepageProductsTabDisplayed() {
		Assert.assertTrue(admin.homepageProductsTabsDisplayed(), "Homepage products tab not displayed");
	}

	@Test(groups = { "hp_featured_products" }, priority=21)
	public void clickFeaturedProductsTab() throws InterruptedException {
		Thread.sleep(3000);
		admin.clickFeaturedProductsTab();
	}
	
	@Test(groups = { "hp_featured_products" }, priority=24)
	public void assertFeaturedProductsGrid() throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertTrue(admin.featuredProductGridDisplayed(), "Featured products grid is displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=27)
	public void setPriority() throws InterruptedException {
		int flag = 0;
		
		for (int i = 0; i < 4; i++) {
			admin.clickFeaturedItemSortLink((i+1));
			
			Assert.assertTrue(admin.sortInputDisplayed(), "Sort input field was never displayed");
			Assert.assertTrue(admin.savedFeaturedItemButtonDisplayed(), "Featured item button was never displayed");
			admin.setPriority((i+1));
			Assert.assertTrue(admin.loadingMaskNotDisplayed(), "Loading mask still displayed");
			Assert.assertTrue(admin.featuredProductsTabDisplayed(), "Featured items tab not displayed");
			Thread.sleep(3000);
			admin.clickFeaturedProductsTab();
			Assert.assertTrue(admin.featuredProductGridDisplayed(), "Not returned to featured product grid");
			
		}
		
	}
	
	@Test(groups = { "hp_featured_products" }, priority=30)
	public void goToHomepage() {
		hp.goToHomepage();
	}
	
	@Test(groups = { "hp_featured_products" }, priority=33)
	public void assertHero() {
		Assert.assertTrue(hp.heroDisplayed(), "Hero not displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=36)
	public void assertFeaturedItems() {
		Assert.assertTrue(hp.featuredItemsBlockDisplayed(), "Hero not displayed");
	}
	
	@Test(groups = { "hp_featured_products" }, priority=39)
	public void countFeaturedProductsOnHp() {
		
	}
	
	/*@Test(groups = { "hp_featured_products" }, priority=6)
	public void test3() {
		
	}
	
	@Test(groups = { "hp_featured_products" }, priority=6)
	public void test4() {
		
	}
	
	@Test(groups = { "hp_featured_products" }, priority=6)
	public void test5() {
		
	}
	
	@Test(groups = { "hp_featured_products" }, priority=6)
	public void test6() {
		
	}
	
	@Test(groups = { "hp_featured_products" }, priority=6)
	public void test7() {
		
	}*/
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws SQLException {	
		eyes.abortIfNotClosed();
		db.closeDbConnection();
		driver.quit();
	}	
}
