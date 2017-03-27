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
import pos.ProductPage;
import pos.Checkout;
import pos.Header;

public class CheckoutTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private ProductPage prod;
	private Header header;
	private Checkout checkout;
	float price;
	int qty;
	
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
	
	@Test(alwaysRun = true, groups = { "checkout" }, priority=1)
	public void goToTestProductPage() {
		prod = new ProductPage(driver);
		prod.goToSimpleTestProduct();
	}
	
	@Test(groups = { "checkout" }, priority=1)
	public void stateCheck() {
		Assert.assertTrue(prod.simpleProductAddToCartButtonPresent(), "Add to cart button is not present");
	}
	
	@Test(groups = { "checkout" }, priority=4)
	public void addToCart() {
		Assert.assertTrue(prod.simpleProductAddToCartButtonPresent(), "Add to cart button is not present");

		//when navigating to new page, the command that takes you to the new page should return a page object for that new page
		header = prod.clickSimpleProductAddToCart();
	}
	
	@Test(groups = { "checkout" }, priority=5 )
	public void waitForSpinnerToGoAway() {
		System.out.println("Hopefully we wait until the spinner goes away here! - 1");
		Assert.assertTrue(header.miniCartSpinnerNotDisplayed(), "Spinner did not go away");
	}
	
	@Test(groups = { "checkout" }, priority=6 )
	public void miniCartSuccessMessageDisplayed() {
		System.out.println("Mini cart success message is hopefully displayed! - 3");
		Assert.assertTrue(header.miniCartSuccessMessageDisplayed(), "Mini cart did not open");
	}
	
	@Test(groups = { "checkout" }, priority=7 )
	public void miniCartDisplayed() {
		System.out.println("Hopefully the mini cart opens! - 4");
		Assert.assertTrue(header.miniCartOpen(), "Mini cart did not open");
	}
	
	@Test(groups = { "checkout" }, priority=8 )
	public void miniCartCheckoutButtonDisplayed() {
		System.out.println("Hopefully the checkout button is displayed! - 2");
		Assert.assertTrue(header.miniCartOpen(), "Mini cart did not open");
	}
	
	@Test(groups = { "checkout" }, priority=10 )
	public void startCheckout() throws InterruptedException {	
		if (browser.equalsIgnoreCase("MicrosoftEdge")) {
			System.out.println("FUCK EDGE");
			checkout = header.clickCheckoutButton();
		}
		else {
			//when navigating to new page, the command that takes you to the new page should return a page object for that new page
			checkout = header.clickCheckoutButton();
		}
	}
	
	@Test(groups = { "checkout" }, priority=12)
	public void verifyOnCheckoutForm() {
		Assert.assertTrue(checkout.oneStepCheckoutFormPresent(), "Checkout method form isn't displayed");
	}
	
	@Test(groups = { "checkout" }, priority=13)
	public void testCheckoutUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), checkout.getBaseUrl() + checkout.getOneStepCheckoutUrl());
	}
	
	@Test(groups = { "checkout" }, priority=15)
	public void fillBillingInfoForm() {
		checkout.fillBillingInfoForm();
		checkout.waitForLoad();
	}
	
	@Test(groups = { "checkout" }, priority=18)
	public void checkLoadingFinished() {
		Assert.assertFalse(checkout.loadingSpinnerDisplayed(), "The loading spinner is still displayed");
		checkout.clickFlatRateShipping();
		checkout.waitForLoad();
	}
	
	@Test(groups = { "checkout" }, priority=21)
	public void choosePaymentOption() {
		Assert.assertFalse(checkout.loadingSpinnerDisplayed(), "The loading spinner is still displayed");
		checkout.clickCheckMoneyOrderRadioButton();
		checkout.waitForLoad();
	}
	
	@Test(groups = { "checkout" }, priority=24)
	public void checkTableSummary() throws Exception {
		Assert.assertTrue(checkout.waitForCheckoutSummary(), "Checkout summary table not displayed");
	}
	
	@Test(groups = { "checkout" }, priority=24)
	public void checkTableTotals() throws Exception {
		Assert.assertTrue(checkout.waitForCheckoutTotals(), "Checkout totals table not displayed");
	}
	
	@Test(groups = { "checkout" }, priority=27)
	public void placeOrder() throws Exception {
		checkout.clickPlaceOrderButton();
		Assert.assertTrue(checkout.continueShoppingButtonPresent(), "Continue shopping button not displayed!");
	}
	
	@Test(groups = { "checkout" }, priority=30)
	public void orderNum() throws InterruptedException {
		Assert.assertTrue(checkout.orderNumPresent(), "Order number isn't displayed");
	}
	
	@Test(groups = { "checkout" }, priority=33)
	public void orderSuccess() {
		Assert.assertEquals(driver.getCurrentUrl(), checkout.getBaseUrl() + checkout.getCheckoutSuccessUrl());
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
