package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pos.Base;
import pos.SavedItems;

public class Header extends Base {

	By header =  By.cssSelector("#header > div.header-outer-wrapper > div");
	By header2 = By.cssSelector("#header");
	By signInLink = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account");
	By signInLinkText = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account > span.label");
	By signInDropdownClosed = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account:not(.skip-active)");
	By signInDropdownOpen = By.cssSelector("#header > div.header-outer-wrapper > div.page-header-container > div.skip-content.ajaxlogin.skip-active");
	By signInFrom = By.id("ajax-login");
	By registerFrom = By.id("ajax-register");
	By signInLabel = By.cssSelector("#ajax-login > div > div > div.window-title > h3");
	By mobileSignInLabel = By.cssSelector("#ajax-login > div > div > div.window-title-mobile > h3");
	By mobileCreateAccountLabel = By.cssSelector("#ajax-register > div > div > div.window-title-mobile > h3");
	By signInButton = By.cssSelector("#ajax-login > div > div > div.window-box.last > div > button");
	By signInEmailField = By.id("email-login");
	By signInPwField = By.id("password-login");
	By registerFNameField = By.id("firstname");
	By registerLNameField = By.id("lastname");
	By registerEmailField = By.id("email");
	By registerPwField = By.id("password");
	By registerConfirmPwField = By.id("passwordsecond");
	By registerButton = By.cssSelector("#ajax-register > div > div > div.window-box.last > div > button");
	By loggedInUserLabel = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account > span.logged-in-label");
	By loggedInUserSignOutLabel = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account > span.logged-in-sign-out");
	By loggedInUserDropdownClosed = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account:not(.skip-active)");
	By loggedInUserDropdownOpen = By.cssSelector("#header > div.header-outer-wrapper > div.page-header-container > div.skip-content.skip-active");
	By logoutButton = By.cssSelector("#header-account > div.links > ul > li.last > a");
	By miniCartBlock = By.cssSelector("#header-cart");
	
	By miniCartBlockOpen = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > div.header-minicart > div.block.block-cart.skip-content.skip-active");
	
	By miniCartBlockClosed = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > div.header-minicart > div.block.block-cart.skip-content:not(.skip-active)");
	By miniCartCheckoutButton = By.cssSelector("#header-cart > div.minicart-wrapper > div.minicart-actions > ul > li > a");	 
	By miniCartSuccessMessage = By.id("minicart-success-message");
	By miniCartSpinner = By.cssSelector("#header-cart > div.minicart-wrapper.loading");
	By miniCartHeaderIcon = By.cssSelector("#minicart-anchor");
	By savedItemsIcon = By.cssSelector("#saved-items-menu > span.icon");
	By savedItemsCount = By.cssSelector("#saved-items-menu > span.count");
	By savedItemsBlock = By.cssSelector("#saved-items-menu");
	By savedItemsLink = By.cssSelector("#saved-items-menu");
	By noSavedItemsMessage = By.cssSelector("#header > div.page-header-container > div#header-loves");
	By mobileSignInForm = By.cssSelector("#header-account > form.login-window.open-menu");
	By mobileCreateAccountForm = By.cssSelector("#header-account > form.register-window.open-menu");
	By mobileSignInButton = By.cssSelector("#header-account > form.login-window:not(.open-menu) > h3");
	By mobileCreateAccountButton = By.cssSelector("#header-account > form.register-window:not(.open-menu) > h3");
	By mobileLoggedInDropdownOpen = By.cssSelector("#header > div.header-outer-wrapper > div.page-header-container > div.skip-content.skip-active");
	By mobileLoggedInDropdownClosed = By.cssSelector("#header > div.header-outer-wrapper > div.page-header-container > div.skip-content:not(.skip-active)");
	By loggedInAccountDropdownButton = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account");
	By loggedInAccountDropdownButtonActive = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account.skip-active");
	By loggedInDropdownLabel = By.cssSelector("#header-account > div.your-account-mobile-title > span.label");
	By loggedInLabelName = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account > span.logged-in-label");
	By loggedInLabelSignOut = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account > span.logged-in-sign-out");
	By navBar = By.cssSelector("#nav");
	By navElement = By.cssSelector("#nav > ol > li");
	By logo = By.cssSelector("#header > div.header-outer-wrapper > div > a > img");
	String navEle = "#nav > ol > li";
	
	public Header(WebDriver driver) {
		super(driver);
	}
	
	public Boolean headerDisplayed() {
		return waitForIsDisplayed(header, 10);
	}
	
	public By getHeader() {
		return header;
	}
	
	public void deleteHeader() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("$j('#header').remove();");
	}
	
	public By getSignInLink() {
		return signInLink;
	}
	
	public void clickSignInLink() {
		click(signInLink);
	}
	
	public Boolean signInLinkExists() {
		return isPresent(signInLink);
	}
	
	public Boolean signInLinkDisplayed() {
		return waitForIsDisplayed(signInLink, 10);
	}
	
	public void clickSignInLinkText() {
		click(signInLinkText);
	}
	
	public Boolean signInDropdownNotDisplayed() {
		return isPresent(signInDropdownClosed);
	}
	
	public Boolean signInDropdownDisplayed() {
		return waitForIsDisplayed(signInDropdownOpen, 10);
	}
	
	public By getOpenSignInDropdown() {
		return signInDropdownOpen;
	}
	
	public By getSignInForm() {
		return signInFrom;
	}
	
	public By getRegisterForm() {
		return registerFrom;
	}
	
	public void clearSignInDropdownFields() {
		clearField(signInEmailField);
		clearField(signInPwField);
		clearField(registerFNameField);
		clearField(registerLNameField);
		clearField(registerEmailField);
		clearField(registerPwField);
		clearField(registerConfirmPwField);
		
		//clicking on a non-clickable element (label) removes focus from the last input field, which was added by clearing that input field
		clickSignInLabel();
	}
	
	public void clickSignInLabel() {
		click(signInLabel);
	}
	
	public void clearSignInMobileFields() {
		clearField(signInEmailField);
		clearField(signInPwField);
	}
	
	public void clearRegisterMobileFields() {
		clearField(registerFNameField);
		clearField(registerLNameField);
		clearField(registerEmailField);
		clearField(registerPwField);
		clearField(registerConfirmPwField);
	}
	
	public void clickSignInButton() {
		click(signInButton);
	}
	
	public void clickRegisterButton() {
		click(registerButton);
	}
	
	public void fillSignInForm(String em, String pw) {
		type(em, signInEmailField);
		type(pw, signInPwField);
	}
	
	public void fillRegisterForm(String fn, String ln, String em, String pw, String cpw) {
		type(fn, registerFNameField);
		type(ln, registerLNameField);
		type(em, registerEmailField);
		type(pw, registerPwField);
		type(cpw, registerConfirmPwField);
	}
	
	public Boolean loggedInLabelDisplayed() {
		return waitForIsDisplayed(loggedInUserLabel, 10);
	}
	
	public Boolean loggedInUserDropdownNotDisplayed() {
		return isPresent(loggedInUserDropdownClosed);
	}
	
	public Boolean loggedInUserDropdownDisplayed() {
		return waitForIsDisplayed(loggedInUserDropdownOpen);
	}
	
	public void clickLoggedInUserLabel() {
		click(loggedInUserLabel);
	}
	
	public By getOpenLoggedInUserDropdown() {
		return loggedInUserDropdownOpen;
	}
	
	public Boolean logoutButtonIsDisplayed() {
		return waitForIsDisplayed(logoutButton);
	}
	
	public void clickLogoutButton() {
		click(logoutButton);
	}
	
	public Boolean miniCartOpen() {
		return waitForIsDisplayed(miniCartBlock, 10);
	}
	
	public Boolean miniCartDisplayed() {
		return waitForIsDisplayed(miniCartBlockOpen, 10);
	}
	
	public Boolean miniCartClosed() {
		return waitForIsNotDisplayed(miniCartBlockOpen, 10);
	}
	
	public Boolean miniCartCheckoutButtonDisplayed() {
		return waitForIsDisplayed(miniCartCheckoutButton, 10);
	}
	
	public Boolean miniCartSuccessMessageDisplayed() {
		return waitForIsDisplayed(miniCartSuccessMessage, 10);
	}
	
	public Boolean miniCartSpinnerDisplayed() {
		return waitForIsDisplayed(miniCartSpinner, 10);
	}
	
	public Boolean miniCartSpinnerNotDisplayed() {
		return waitForIsNotDisplayed(miniCartSpinner, 10);
	}
	
	public void clickToOpenMiniCart() {
		click(miniCartHeaderIcon);
	}
	
	public By getOpenMiniCartBlock() {
		return miniCartBlockOpen;
	}
	
	public By getMiniCartHeaderIcon() {
		return miniCartHeaderIcon;
	}
	
	public Checkout clickCheckoutButton() {
		click(miniCartCheckoutButton);
		return new Checkout(driver);
	}
	
	public By getSavedItemsIcon() {
		return savedItemsIcon;
	}
	
	public String getSavedItemsCount() {
		String ct = getText(savedItemsCount);
		return ct;
	}
	
	public Boolean savedItemsHeartDisplayed() {
		return waitForIsDisplayed(savedItemsIcon, 10);
	}
	
	public Boolean savedItemsCountDisplayed() {
		return waitForIsDisplayed(savedItemsCount, 10);
	}
	
	public Boolean savedItemsBlockDisplayed() {
		return waitForIsDisplayed(savedItemsBlock);
	}
	
	public By getSavedItemsBlock() {
		return savedItemsBlock;
	}
	
	public SavedItems clickSavedItemsLink() {
		click(savedItemsLink);
		return new SavedItems(driver);
	}
	
	public Boolean noSavedItemsMessageIsDisplayed() {
		return waitForIsDisplayed(noSavedItemsMessage, 10);
	}
	
	public By getNoSavedItemsMessage() {
		return noSavedItemsMessage;
	}
	
	public By getMobileSignInForm() {
		return mobileSignInForm;
	}
	
	public By getMobileCreateAccountForm() {
		return mobileCreateAccountForm;
	}
	
	public Boolean mobileSignInFormDisplayed() {
		return waitForIsDisplayed(mobileSignInForm, 10);
	}
	
	public Boolean mobileCreateAccountFormDisplayed() {
		return waitForIsDisplayed(mobileCreateAccountForm, 10);
	}
	
	public Boolean mobileSignInButtonDisplayed() {
		return waitForIsDisplayed(mobileSignInButton, 10);
	}
	
	public Boolean mobileCreateAccountButtonDisplayed() {
		return waitForIsDisplayed(mobileCreateAccountButton, 10);
	}
	
	public void clickMobileSignInButton() {
		click(mobileSignInButton);
	}
	
	public void clickMobileCreateAccountButton() {
		click(mobileCreateAccountButton);
	}
	
	public void clickMobileSignInLabel() {
		click(mobileSignInLabel);
	}
	
	public Boolean signInButtonDisplayed() {
		return waitForIsDisplayed(signInButton);
	}
	
	public void clickMobileCreateAccountLabel() {
		click(mobileCreateAccountLabel);
	}
	
	public Boolean mobileLoggedInDropdownOpen() {
		return waitForIsDisplayed(mobileLoggedInDropdownOpen, 10);
	}
	
	public Boolean mobileLoggedInDropdownClosed() {
		return waitForIsNotDisplayed(mobileLoggedInDropdownClosed, 10);
	}
	
	public void clickToOpenLoggedInDropdown() {
		click(loggedInAccountDropdownButton);
	}
	
	public void clickToCloseLoggedInDropdown() {
		click(loggedInAccountDropdownButtonActive);
	}
	
	public By getLoggedInDropdown() {
		return mobileLoggedInDropdownOpen;
	}
	
	public Boolean mobileLoggedInDropdownLabelDisplayed() {
		return waitForIsDisplayed(loggedInDropdownLabel, 10);
	}
	
	public Boolean loggedInLabelNameDisplayed() {
		return waitForIsDisplayed(loggedInLabelName);
	}
	
	public Boolean loggedInLabelSignOutDisplayed() {
		return waitForIsDisplayed(loggedInLabelSignOut);
	}
	
	public String getLoggedInName() {
		String s = getText(loggedInLabelName);
		return s;
	}
	
	public int countNavItems() {
		return count(navElement);
	}
	
	public int findNavElement(String navItem) {
		int x = countNavItems();
		String a, b;
		int found = 0;
		int num = 0;
		
		for (int i = 1; i <= x; i++) {
			a = navEle + ":nth-child(" + i + ") > a";
			b = getText(By.cssSelector(a));
			if (b.equalsIgnoreCase(navItem)) {
				found = 1;
				num = i;
			}
		}
		
		if (found == 1) {
			return num;
		}
		else {
			System.out.println("Nav element not found");
			return 0;
		}
		
	}
	
	public void clickToOpenMegaMenu(String navItem) {
		int i = findNavElement(navItem);
		
		if (i == 0) {
			System.out.println("Could not find nav element");
		}
		else {
			String a = navEle + ":nth-child(" + i + ")";
			click(By.cssSelector(a));
		}
		
	}
	
	public int hoverToOpenMegaMenu(String navItem) {
		int i = findNavElement(navItem);
		
		if (i == 0) {
			System.out.println("Could not find nav element");
			return 0;
		}
		else {
			String a = navEle + ":nth-child(" + i + ")";
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.cssSelector(a));
			action.moveToElement(we).build().perform();
			return i;
		}
		
	}
	
	public Boolean megaMenuDisplayed(int navItem) {
		String a = navEle + ":nth-child(" + navItem + ").menu-active > div";
		return waitForIsDisplayed(By.cssSelector(a));
	}
	
	public By getOpenMegaMenu(int navItem) {
		String a = navEle + ":nth-child(" + navItem + ").menu-active > div";
		return By.cssSelector(a);
	}
	
	public void hoverToLogo() {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(logo);
		action.moveToElement(we).build().perform();
	}
	
}
