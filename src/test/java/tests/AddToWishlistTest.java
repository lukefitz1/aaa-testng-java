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

import pos.MyAccount;
import pos.ProductPage;
import tests.Base;

public class AddToWishlistTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private ProductPage prod;
	private MyAccount acct;
	int numProds;
	String prodName; 
	
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
	
	@Test(alwaysRun = true, groups = { "add_to_wishlist" }, priority=1)
	public void goToProductPage() {
		prod = new ProductPage(driver);
		prod.goToSimpleTestProduct();
	}
	
	@Test(groups = { "add_to_wishlist" }, priority=2)
	public void checkWishlistLink() {
		prodName = prod.getProductName();
		Assert.assertTrue(prod.addToWishlistLinkPresent(), "Add to Wishlist link is not present");
	}
	
	@Test(groups = { "add_to_wishlist" }, priority=3)
	public void clickWishlistLink() {
		acct = prod.clickAddToWishlistLink();
		Assert.assertTrue(acct.loginFormPresent(), "Account login form is not present");
	}
	
	@Test(groups = { "add_to_wishlist" }, priority=6)
	public void checkLoginPageUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://qa.aaa.dev/index.php/" + acct.getAccountLoginUrl(), "Not taken to the login page");
	}
	
	@Test(groups = { "add_to_wishlist" }, priority=12)
	public void loginToCustomerAcct() {
		acct.fillLoginForm("luke.fitzgerald@blueacorn.com", "pass4luke");
		acct.clickLoginButton();
		Assert.assertTrue(acct.wishlistSuccessMessageDisplayed(), "Wishlist success message is displayed");
	}
	
	/*@Test(groups = { "add_to_wishlist" }, priority=15)
	public void checkSuccessMessageDisplayed() {
		Assert.assertEquals(acct.getWishlistSuccessMessageText(), "BA Test Product has been added to your wishlist. Click here to continue shopping.");
	}
	
	@Test(groups = { "add_to_wishlist" }, priority=18) 
	public void countProdsInWishlist() {
		numProds = acct.countProductsInWishlist();
	}
	
	@Test(groups = { "add_to_wishlist" }, priority=21) 
	public void checkWishlistTableForProduct() {
		Assert.assertTrue(acct.checkForProdInWishlist(prodName, numProds), "Product not found in wishlist");
	}*/
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
