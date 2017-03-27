package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Checkout extends Base {

	String checkoutUrl = "checkout/onepage/";
	String checkoutSuccessUrl = "checkout/onepage/success/?___SID=U";
	String oneStepCheckoutUrl = "onestepcheckout/?___SID=U";
	By guestForm = By.cssSelector("#checkout-step-login > div > div.col-1 > ul");
	By checkoutMethodContinueButton = By.id("onepage-guest-register-button");
	By billingInfoForm = By.id("co-billing-form");
	By shippingInfoForm = By.id("co-billing-form");
	By billingInfoContinueButton = By.cssSelector("#billing-buttons-container > button");
	By shippingInfoContinueButton = By.cssSelector("#shipping-buttons-container > button");
	By shippingMethodForm = By.id("co-shipping-method-form");
	By shippingMethodContinueButton = By.cssSelector("#shipping-method-buttons-container > button");
	By paymentInfoForm = By.id("co-payment-form");
	By paymentInfoContinueButton = By.cssSelector("#payment-buttons-container > button");
	By orderReviewTable = By.id("checkout-review-load");
	By continueShoppingButton = By.cssSelector("body > div.wrapper > div.page > div.main-container.col1-layout > div > div > div.buttons-set > button");
	By orderNum = By.cssSelector("body > div.wrapper > div.page > div.main-container.col1-layout > div > div > p:nth-child(4)");
	By oneStepCheckoutForm = By.id("onestepcheckout-form");
	By billingFName = By.id("billing:firstname");
	By billingLName = By.id("billing:lastname");
	By billingEmail = By.id("billing:email");
	By billingAddress = By.id("billing:street0");
	By billingAddress2 = By.id("billing:street1");
	By billingCity = By.id("billing:city");
	By billingState = By.id("billing:region_id");
	By billingZip = By.id("billing:postcode");
	By billingPhone = By.id("billing:telephone");
	By shippingMethodBlock = By.cssSelector("#onestepcheckout-form > fieldset > div > div.onestepcheckout-column-middle > div > div.onestepcheckout-shipping-method > div");
	By loadingSpinner = By.id("loading-process");
	By flatRateShippingOption = By.id("s_method_flatrate_flatrate");
	By checkMoneyOrderRadioButton = By.id("p_method_checkmo");
	By placeOrderButton = By.id("onestepcheckout-place-order");
	By checkoutSummary = By.cssSelector("#onestepcheckout-form > fieldset > div > div.onestepcheckout-column-right > div > div.onestepcheckout-summary > table.onestepcheckout-summary");
	By checkoutTotals = By.cssSelector("#onestepcheckout-form > fieldset > div > div.onestepcheckout-column-right > div > div.onestepcheckout-summary > table.onestepcheckout-totals");
	
	By fuckinGigya = By.cssSelector("body > gig-share");
	By closeFuckinGigya = By.cssSelector("#gig_1461111650067_showShareUI_commentCanvas > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(2) > div > img");
	
	public Boolean isGigyaOpen() {
		return waitForIsDisplayed(fuckinGigya);
	}
	
	public void closeGigya() {
		click(closeFuckinGigya);
	}
	
	public Boolean waitForCheckoutSummary() {
		return waitForIsDisplayed(checkoutSummary, 10);
	}
	
	public Boolean waitForCheckoutTotals() {
		return waitForIsDisplayed(checkoutTotals, 10);
	}
	
	public void clickFlatRateShipping() {
		click(flatRateShippingOption);
	}
	
	public void waitForLoad() {
		waitForIsNotDisplayed(loadingSpinner, 10);
	}
	
	public Boolean loadingSpinnerDisplayed() {
		return waitForIsDisplayed(loadingSpinner, 10);
	}
	
	public Checkout(WebDriver driver) {
		super(driver);
	}
	
	public String getCheckoutUrl() {
		return checkoutUrl;
	}
	
	public String getOneStepCheckoutUrl() {
		return oneStepCheckoutUrl;
	}
	
	public String getCheckoutSuccessUrl() {
		return checkoutSuccessUrl;
	}
	
	public Boolean stepOneContinueButtonPresent() {
		return waitForIsDisplayed(checkoutMethodContinueButton, 10);
	}
	
	public Boolean guestFormPresent() {
		return waitForIsDisplayed(guestForm, 10);
	}
	
	public Boolean oneStepCheckoutFormPresent() {
		return waitForIsDisplayed(oneStepCheckoutForm, 10);
	}
	
	public Boolean billingInfoFormPresent() {
		return waitForIsDisplayed(billingInfoForm, 10);
	}
	
	public Boolean shippingInfoFormPresent() {
		return waitForIsDisplayed(shippingInfoForm, 10);
	}
	
	public Boolean shippingMethodFormPresent() {
		return waitForIsDisplayed(shippingMethodForm, 10);
	}
	
	public Boolean paymentInfoFormPresent() {
		return waitForIsDisplayed(paymentInfoForm, 10);
	}
	
	public Boolean orderReviewTablePresent() {
		return waitForIsDisplayed(orderReviewTable, 10);
	}
	
	public Boolean continueShoppingButtonPresent() {
		return waitForIsDisplayed(continueShoppingButton, 10);
	}
	
	public Boolean orderNumPresent() {
		return waitForIsDisplayed(orderNum, 10);
	}
	
	public void clickCheckoutMethodContinueButton() {
		click(checkoutMethodContinueButton);
	}
	
	public void clickBillingInfoContinueButton() {
		click(billingInfoContinueButton);
	}
	
	public void clickShippingInfoContinueButton() {
		click(shippingInfoContinueButton);
	}
	
	public void clickShippingMethodContinueButton() {
		click(shippingMethodContinueButton);
	}
	
	public void clickPaymentInfoContinueButton() {
		click(paymentInfoContinueButton);
	}
	
	public void clickPlaceOrderButton() {
		click(placeOrderButton);
	}
	
	public void clickContinueShoppingButton() {
		click(continueShoppingButton);
	}
	
	public void fillBillingInfoForm() {
		Select billingStateSelect = new Select(driver.findElement(billingState));
		
		type("Luke", billingFName);
		type("Fitzgerald", billingLName);
		type("luke.fitzgerald@blueacorn.com", billingEmail);
		type("145 Williman St", billingAddress);
		type("145 Williman St", billingAddress2);
		type("Charleston", billingCity);
		billingStateSelect.selectByIndex(54);
		type("29403", billingZip);
		type("877944283", billingPhone);	
	}
	
	public void clickCheckMoneyOrderRadioButton() {
		click(checkMoneyOrderRadioButton);
	}
}
