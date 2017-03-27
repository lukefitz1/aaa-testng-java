package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.Region;

public class Base {

	protected Eyes eyes = new Eyes();
	protected int num = 1;
	protected String os;
	protected String browser;
	protected String version;
	protected String device;
	protected int width;
	protected int height;
	protected Platform platform;
	protected SafariOptions saf_options;
	protected String gridUrl;
	
	@Parameters({"pbrowser", "pversion", "purl", "pos", "pdevice"})
	@BeforeTest(alwaysRun = true)
	public void setUp(/*Platform pplatform,*/ String pbrowser, String pversion, String purl, String pos, @Optional("optional value") String pdevice) throws MalformedURLException {
		//capabilities = new DesiredCapabilities();
		gridUrl = String.format(purl);
		
        // This is your api key, make sure you use it in all your tests.
        eyes.setApiKey("95dlJqlySCR2I46tb9J0HBHdlQjAq5JSN66Y4BcmQig110"); //<-- BA gmail account
		
		if (pbrowser.equalsIgnoreCase("chrome") && pos.equalsIgnoreCase("mac")) {
			System.out.println("Desktop Chrome on Mac Tests!");
//			capabilities.setPlatform(Platform.MAC);
//			capabilities.setBrowserName(pbrowser);
//			capabilities.setVersion(pversion);
			
			/*os = pos;
			browser = pbrowser;
			version = pversion;*/
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("safari") && pos.equalsIgnoreCase("mac")) {
			System.out.println("Desktop Safari Tests!");			
//			capabilities.setPlatform(Platform.MAC);
//			capabilities.setBrowserName(pbrowser);
//			capabilities.setVersion(pversion);
			
			/*os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;*/
		}
		
		else if (pbrowser.equalsIgnoreCase("firefox") && pos.equalsIgnoreCase("mac")) {
			System.out.println("Desktop FF on Mac Tests!");
//			capabilities.setPlatform(Platform.MAC);
//			capabilities.setBrowserName(pbrowser);
//			capabilities.setVersion(pversion);
			
			/*os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;*/
		}
		
		/*else if (pbrowser.equalsIgnoreCase("MicrosoftEdge") && pos.equalsIgnoreCase("win10")) {
			System.out.println("Desktop Edge on Win10 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("chrome") && pos.equalsIgnoreCase("win10")) {
			System.out.println("Desktop Chrome on Win10 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("chrome") && pos.equalsIgnoreCase("win7")) {
			System.out.println("Desktop Chrome on Win7 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("firefox") && pos.equalsIgnoreCase("win7")) {
			System.out.println("Desktop FF on Win7 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("internet explorer") && pversion.equalsIgnoreCase("11")) {
			System.out.println("Desktop IE11 on Win7 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("internet explorer") && pversion.equalsIgnoreCase("10")) {
			System.out.println("Desktop IE10 on Win7 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("internet explorer") && pversion.equalsIgnoreCase("9")) {
			System.out.println("Desktop IE9 on Win7 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pbrowser.equalsIgnoreCase("internet explorer") && pversion.equalsIgnoreCase("8")) {
			System.out.println("Desktop IE8 on Win7 Tests!");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pdevice.equalsIgnoreCase("Samsung Galaxy S5 - 4.4.4 - API 19 - 1080x1920")) {
			System.out.println("Android Tests!");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pdevice.equalsIgnoreCase("iPad 2")) {
			System.out.println("iPad 2 Tests!");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pdevice.equalsIgnoreCase("iPad Air")) {
			System.out.println("iPad Air Tests!");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		else if (pdevice.equalsIgnoreCase("iPhone 6")) {
			System.out.println("iPhone 6 Tests!");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}
		
		//Luke's real LG G2
		else if (pdevice.equalsIgnoreCase("")) {
			System.out.println("Android LG G2 (Real Device) Tests!");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pversion);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, pbrowser);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pdevice);
			
			os = pos;
			browser = pbrowser;
			version = pversion;
			device = pdevice;
		}*/
		
		/*if (pdevice.equalsIgnoreCase("desktop")) {
			System.out.println(pdevice + " <- this should equal desktop");
			driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			//driver.manage().window().setSize(new Dimension(1280, 750));
			if (browser.equalsIgnoreCase("MicrosoftEdge")) {
				System.out.println("This is Edge");
			}
			else {
				System.out.println("This is not edge");
				driver.manage().window().setSize(new Dimension(1280, 750));
			}
		}
		else if (pdevice.equalsIgnoreCase("iPad 2") || pdevice.equalsIgnoreCase("iPhone 6") || pdevice.equalsIgnoreCase("iPad Air")) {
			System.out.println(pdevice + " <- this should equal iPad 2/iPhone 6");
			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}
		else if (pdevice.equalsIgnoreCase("Samsung Galaxy S5 - 4.4.4 - API 19 - 1080x1920")) {
			System.out.println(pdevice + " <- this should equal Samsung Galaxy S5");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}*/
		
	}

	
//	@AfterMethod(alwaysRun = true)
//	public void screenshotOnFailure(ITestResult result) {
//		if(ITestResult.FAILURE==result.getStatus()) {
//			
//			try {
//				DateFormat df = new SimpleDateFormat("MMddyyyyHHmmsss");
//				Calendar cal = Calendar.getInstance();
//				
//				File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(scrfile, new File("/Users/Luke/Documents/workspace/aaa/failures/testFailure" + df.format(cal.getTime()) + ".jpg"));
//			} catch (IOException e) {
//				System.out.println("Error while taking screenshot");
//			}
//		}
//	}
	
//	@AfterTest(alwaysRun = true)
//	public void tearDown() {	
//		eyes.abortIfNotClosed();
//		driver.quit();
//	}
	
	protected Platform setPlatform(String os) {
		if (os.equalsIgnoreCase("mac")) {
			platform = Platform.MAC;
			return platform;
		}
		else if (os.equalsIgnoreCase("ios")) {
			System.out.println("This test is on an ios simulator");
			platform = Platform.MAC;
			return platform;
		}
		else if (os.equalsIgnoreCase("windows")) {
			platform = Platform.WINDOWS;
			return platform;
		}
		else if (os.equalsIgnoreCase("win7")) {
			platform = Platform.WINDOWS;
			return platform;
		}
		else if (os.equalsIgnoreCase("win10")) {
			System.out.println("Windows 10 tests");
			platform = Platform.WINDOWS;
			//platform = Platform.WIN10;
			return platform;
		}
		else if (os.equalsIgnoreCase("android")) {
			System.out.println("This test is on Android");
			platform = Platform.ANDROID;
			return platform;
		}
		else {
			System.out.println("This test did not have an applicable OS");
			platform = Platform.MAC;
			return platform;
		}
	}
	
	public void refresh(WebDriver drive) {
		drive.navigate().refresh();
	}
	
	public void checkRegWithShift(Eyes eyes, WebElement element, int heightShift, String testName) {
		int top = element.getLocation().getY();
		int left = element.getLocation().getX();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		Region region = new Region(left,top + heightShift,width,height);
		eyes.checkRegion(region);
	}
	
	public void checkRegWithLeftShift(Eyes eyes, WebElement element, int leftShift, String testName) {
		int top = element.getLocation().getY();
		int left = element.getLocation().getX();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		Region region = new Region(left + leftShift,top,width,height);
		eyes.checkRegion(region);
	}
	
	public void checkRegWithShiftAndWait(Eyes eyes, WebElement element, int heightShift, String testName, int wait) {
		int top = element.getLocation().getY();
		int left = element.getLocation().getX();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		Region region = new Region(left,top + heightShift,width,height);
		eyes.checkRegion(region);
	}
	
}
