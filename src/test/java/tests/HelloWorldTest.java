package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HelloWorldTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight,  @Optional("optional value") String pdevice) throws MalformedURLException {		
		browser = pbrowser;
		version = pversion;
		width = pwidth;
		height = pheight;
		device = pdevice;
		os = pos;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));

		//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		driver = new RemoteWebDriver(new URL("http://192.168.0.17:4444/wd/hub"), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}
	
	@Test(alwaysRun = true, groups = { "checkout" }, priority=1)
	public void goToGoogle() {
		driver.get("http://aaa.qa-2.blueacorn.net");
	}
	
	@Test(priority=2)
	public void waitForGoogle() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
