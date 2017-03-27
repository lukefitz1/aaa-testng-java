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

import pos.Admin;
import tests.Base;

public class DeleteCustomerTest extends Base {
	
	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Admin admin;
	String em = "lukefitztest8@gmail.com";
	
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
	
	@Test(alwaysRun = true, groups = { "admin_delete_customer" }, priority=0)
	public void goToLogin() {
		admin = new Admin(driver);
		admin.goToCustomerGrid();
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=1)
	public void loginToAdmin() {
		admin.login("luke.fitzgerald@blueacorn.com", "pass4luke");
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=2)
	public void closePopup() {
		Assert.assertTrue(admin.popupDisplayed(), "Popup window didn't display");
		admin.closePopup();
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=3)
	public void confirmPopupClosed() {
		Assert.assertTrue(admin.popupNotDisplayed(), "Popup window didn't display");
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=4)
	public void deleteCustomer() {
		admin.deleteCustomer(em);
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=5)
	public void alertHandle() {
		admin.alertWait();
		admin.clickOkOnAlert();
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=6)
	public void confirmDeletedCustomerMessage() {
		Assert.assertTrue(admin.successMessageDisplayed(), "Success message not displayed");
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=7)
	public void confirmDeletedCustomerText() {
		Assert.assertEquals(admin.getSuccessMessageText(), "Total of 1 record(s) were deleted.");
	}
	
	@Test(groups = { "admin_delete_customer" }, priority=9)
	public void confirmDeletedCustomer() {
		Assert.assertEquals(0, admin.findCustomer(em));
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
