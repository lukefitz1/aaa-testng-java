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
import tests.Base;

public class AccountCreateTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private MyAccount acct;
	
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
	
	@Test(alwaysRun = true, groups = { "acct_create" }, priority=0)
	public void goToAccountLogin() {
		acct = new MyAccount(driver);
		acct.goToLogin();
	}
	
	@Test(groups = { "acct_create" }, priority=1)
	public void createAcctButtonCheck() {
		Assert.assertTrue(acct.createAcctButtonPresent(), "Create an account button is not present");
	}
	
	@Test(groups = { "acct_create" }, priority=3)
	public void clickCreateAcctButton() {
		acct.clickCreateAcctButton();
		Assert.assertTrue(acct.createAcctFormDisplayed(), "Create an account form is not displayed");
	}
	
	@Test(groups = { "acct_create" }, priority=6)
	public void checkCreateAcctUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://qa.aaa.dev/index.php/" + acct.getAccountCreateUrl());
	}
	
	@Test(groups = { "acct_create" }, priority=9)
	public void fillCreateAcctForm() {
		acct.fillCreateAcctForm("Luke", "Fitzgerald", "lukefitztest@gmail.com", "pass4luke");
		acct.clickRegisterButton();
		Assert.assertTrue(acct.acctDashboardDisplayed(), "Account dashboard is not displayed");
	}
	
	@Test(groups = { "acct_create" }, priority=12)
	public void checkAcctCreateSuccessMessage() {
		Assert.assertTrue(acct.createAcctSuccessMessageDisplayed(), "Account creation success message is not displayed");
	}
	
	@Test(groups = { "acct_create" }, priority=15)
	public void checkAcctCreateSuccessMessageTest() {
		Assert.assertEquals(acct.getAccountCreateSuccessMessageText(), "Thank you for registering with Main Website Store.");
	}
	
	@Test(groups = { "acct_create" }, priority=18) 
	public void testAccountCreateGreeting() {
		Assert.assertEquals(acct.getDashGreeting(), "Hello, Luke Fitzgerald!");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
