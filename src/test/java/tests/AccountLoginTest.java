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

public class AccountLoginTest extends Base {

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
	
	@Test(groups = { "login" }, priority=0)
	public void goToLoginPage() {
		acct = new MyAccount(driver);
		acct.goToLogin();
	}
	
	@Test(alwaysRun = true, groups = { "login" }, priority=0)
	public void loginFormCheck() {
		Assert.assertTrue(acct.loginFormPresent(), "Login / register form is not displayed");
	}
	
	@Test(groups = { "login" }, priority=6)
	public void login() throws InterruptedException {
		Thread.sleep(5000);
		acct.fillLoginForm("luke.fitzgerald@blueacorn.com", "pass4luke");
		acct.clickLoginButton();
		Assert.assertTrue(acct.acctDashboardDisplayed(), "Account dashboard is not displayed");
	}
	
	@Test(groups = { "login" }, priority=9)
	public void testLoginUrl() {
		Assert.assertEquals(driver.getCurrentUrl(), "http://aaa.qa-2.blueacorn.net/" + acct.getAccountDashboardUrl());
	}
	
	@Test(groups = { "login" }, priority=12) 
	public void testLoginGreeting() {
		Assert.assertEquals(acct.getDashGreeting(), "Hello, Luke Fitzgerald!");
	}	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
