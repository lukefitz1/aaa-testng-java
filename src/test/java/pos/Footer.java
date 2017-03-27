package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Footer extends Base {

	By footer = By.cssSelector("body > div.wrapper > div > div.footer-container");
	By newsletterSignupBlock = By.cssSelector("body > div.wrapper > div > div.footer-container > div > div.block.block-subscribe");
	By newsletterSignupButton = By.cssSelector("#newsletter-validate-detail > div > div.subscribe-form > div.actions > button");
	By newsletterInputField = By.id("newsletter");
	By newsletterSpinner = By.id("loading-block");
	By newsletterRequiredFieldMessage = By.id("advice-required-entry-newsletter");
	By newsletterEmailAlreadyInUseMessage = By.cssSelector("#newsletter-validate-detail > div > div.subscribe-form > div.input-box.validation-passed > ul > li");
	By newsletterSuccessMessage = By.cssSelector("#newsletter-validate-detail > div > div.subscribe-form > div.input-box.validation-passed > ul > li");
	By tc = By.cssSelector("body > div.wrapper > div > div.footer-container > div > address");
	
	public Footer(WebDriver driver) {
		super(driver);
	}
	
	public Boolean footerDisplayed() {
		return waitForIsDisplayed(footer);
	}
	
	public By getFooter() {
		return footer;
	}
	
	public void clickNewsletterSignupButton() {
		click(newsletterSignupButton);
	}
	
	public By getNewsletterSignupBlock() {
		return newsletterSignupBlock;
	}
	
	public void enterEmailForNewsletter(String em) {
		type(em, newsletterInputField);
	}
	
	public Boolean newsletterSubmitComplete() {
		return waitForIsNotDisplayed(newsletterSpinner, 10);
	}
	
	public void clearNewsletterField() {
		clearField(newsletterInputField);
	}
	
	public Boolean requiredFieldMessageDisplayed() {
		return waitForIsDisplayed(newsletterRequiredFieldMessage);
	}
	
	public Boolean emailAlreadyInUseMessageDisplayed() {
		return waitForIsDisplayed(newsletterEmailAlreadyInUseMessage);
	}
	
	public Boolean emailSuccessMessageDisplayed() {
		return waitForIsDisplayed(newsletterSuccessMessage);
	}
	
	public void clickTC() {
		click(tc);
	}
	
	public void clickFooterContainer() {
		click(footer);
	}
}
