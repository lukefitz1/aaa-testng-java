package pos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pos.Base;

public class MyAccount extends Base {

	String acctLoginUrl = "customer/account/login/";
	String acctDashUrl = "customer/account/";
	String accountCreateUrl = "customer/account/create/";
	By loginForm = By.id("login-form");
	By loginEmail = By.id("email");
	By loginPw = By.id("pass");
	By loginButton = By.id("send2");
	By newAcctButton = By.cssSelector("#login-form > div > div.col-1.new-users > div.buttons-set > a");
	By acctDash = By.cssSelector("body > div.wrapper > div > div.main-container.col2-left-layout > div > div.col-main > div > div");
	By acctDashName = By.cssSelector("body > div.wrapper > div > div.main-container.col2-left-layout > div > div.col-main > div > div > div.welcome-msg > p.hello > strong");
	By createAcctForm = By.id("form-validate");
	By createAcctFn = By.id("firstname");
	By createAcctLn = By.id("lastname");
	By createAcctEmail = By.id("email_address");
	By createAcctPw = By.id("password");
	By createAcctConfirmPw = By.id("confirmation");
	By createAcctRegisterButton = By.cssSelector("#form-validate > div.buttons-set > button");
	By createAcctSuccessMessage = By.cssSelector("body > div.wrapper > div > div.main-container.col2-left-layout > div > div.col-main > div > div > ul > li");
	By createAcctSuccessMessageText = By.cssSelector("body > div.wrapper > div > div.main-container.col2-left-layout > div > div.col-main > div > div > ul > li > ul > li > span");
	By wishlistSuccessMessage = By.cssSelector("body > div.wrapper > div > div.main-container.col2-left-layout > div > div.col-main > div > div.my-wishlist > ul > li");
	By wishlistSuccessMessageText = By.cssSelector("body > div.wrapper > div > div.main-container.col2-left-layout > div > div.col-main > div > div.my-wishlist > ul > li > ul > li > span");
	By wishlistTable = By.cssSelector("#wishlist-table > tbody");
	By wishlistTableProducts = By.cssSelector("#wishlist-table > tbody > tr");
	
	public MyAccount(WebDriver driver) {
		super(driver);
	}
	
	public String getAccountLoginUrl() {
		return acctLoginUrl;
	}
	
	public String getAccountDashboardUrl() {
		return acctDashUrl;
	}
	
	public String getAccountCreateUrl() {
		return accountCreateUrl;
	}
	
	public void goToLogin() {
		visit(acctLoginUrl);
	}
	
	public Boolean loginFormPresent() {
		return waitForIsDisplayed(loginForm, 10);
	}
	
	public Boolean createAcctButtonPresent() {
		return waitForIsDisplayed(newAcctButton, 10);
	}
	
	public Boolean createAcctFormDisplayed() {
		return waitForIsDisplayed(createAcctForm, 10);
	}
	
	public void fillLoginForm(String un, String pw) {
		type(un, loginEmail);
		type(pw, loginPw);	
	}
	
	public void fillCreateAcctForm(String fn, String ln, String email, String pw) {
		type(fn, createAcctFn);
		type(ln, createAcctLn);
		type(email, createAcctEmail);
		type(pw, createAcctPw);	
		type(pw, createAcctConfirmPw);
	}
	
	public void clickLoginButton() {
		click(loginButton);
	}
	
	public void clickRegisterButton() {
		click(createAcctRegisterButton);
	}
	
	public void clickCreateAcctButton() {
		click(newAcctButton);
	}

	public Boolean acctDashboardDisplayed() {
		return waitForIsDisplayed(acctDash, 10);
	}
	
	public Boolean createAcctSuccessMessageDisplayed() {
		return waitForIsDisplayed(createAcctSuccessMessage, 10);
	}
	
	public String getDashGreeting() {
		return getText(acctDashName);
	}
	
	public String getAccountCreateSuccessMessageText() {
		return getText(createAcctSuccessMessageText);
	}
	
	public Boolean wishlistSuccessMessageDisplayed() {
		return waitForIsDisplayed(wishlistSuccessMessage, 10);
	}
	
	public String getWishlistSuccessMessageText() {
		return getText(wishlistSuccessMessageText);
	}
	
	public Boolean wishlistTableDispalyed() {
		return waitForIsDisplayed(wishlistTable, 10);
	}
	
	public int countProductsInWishlist() {
		int ct = driver.findElements(wishlistTableProducts).size();
		return ct;
	}
	
	public Boolean checkForProdInWishlist(String prodName, int numProds) {
		int found = 0;
		
		for(int i = 1; i<=numProds; i++) {
			
			By name = By.cssSelector("#wishlist-table > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > h3 > a");
			
			if(getText(name).equals(prodName)) {
				System.out.println("found!");
				found = 1;
				break;		
			}
		}
		
		if (found == 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
