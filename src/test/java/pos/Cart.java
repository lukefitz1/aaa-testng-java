package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart extends Base {

	String cartPageUrl = "checkout/cart/";
	By cartPageHeading = By.cssSelector("body > div.wrapper > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > div.page-title.title-buttons > h1");
	By cartTable = By.id("shopping-cart-table");
	By cartSuccessMessage = By.cssSelector("body > div.wrapper > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li");
	By cartTopCheckoutButton = By.cssSelector("body > div.wrapper > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > div.page-title.title-buttons > ul > li > button");
	By cartBottomCheckoutButton = By.cssSelector("body > div.wrapper > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > div.cart-totals-wrapper > div > ul > li.method-checkout-cart-methods-onepage-bottom > button");
	
	public Cart(WebDriver driver) {
		super(driver);
	}
	
	public String getCartPageUrl() {
		return cartPageUrl;
	}
	
	public Boolean cartTableDisplayed() {
		return waitForIsDisplayed(cartTable, 10);
	}
	
	public Boolean cartSuccessMessage() {
		return waitForIsDisplayed(cartSuccessMessage, 10);
	}
	
	public Boolean cartPageHeadingPresent() {
		return waitForIsDisplayed(cartPageHeading, 10);
	}
	
	public Boolean cartTopCheckoutButtonPresent() {
		return waitForIsDisplayed(cartTopCheckoutButton, 10);
	}
	
	public Boolean cartBottomCheckoutButtonPresent() {
		return waitForIsDisplayed(cartBottomCheckoutButton, 10);
	}
	
	public Checkout clickTopCheckoutButton() {
		click(cartTopCheckoutButton);
		return new Checkout(driver); 
	}
	
	public Checkout clickBottomCheckoutButton() {
		click(cartBottomCheckoutButton);
		return new Checkout(driver);
	}
}
