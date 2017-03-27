package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Base {

	String simpleTestProduct = "batest-simple";
	String configTestProductColor = "batest-config4";
	String configTestProductFinish = "batest-config5";
	String configTestProductColorFinish = "batest-config6";
	
	String attributeInputBox = "#product-options-wrapper > dl > dd > div.input-box";
	String attributeInputDD = "#product-options-wrapper > dl > dd";
	String attributeSwatchOptions = "div.input-box > div > span.swatch-option";
	String attributeSelectOptions = "div.input-box > select > option";
	
	By simpleProductAddToCartButton = By.cssSelector("#product_addtocart_form > div.product-shop > div.add-to-box > div > div.add-to-cart-buttons > button");
	//By simpleProductAddToCartButton = By.cssSelector("#product_addtocart_form > div.add-to-cart-wrapper > div.add-to-box > div > div.add-to-cart-buttons > button");
	
	By configProductAddToCartButton = By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button");
	By productName = By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span");
	By price = By.cssSelector("#product_addtocart_form > div.product-shop > div.price-info > div > span > span");
	By qtyInput = By.id("#qty");
	By addToWishlistLink = By.cssSelector("#product_addtocart_form > div.add-to-cart-wrapper > div > ul.add-to-links > li:nth-child(1) > a");
	By addToSavedItemsButton = By.cssSelector("#product_addtocart_form > div.add-to-cart-wrapper > a");
	By emptyHeart = By.cssSelector("#saved-item-1 > span.saved-item-icon:not(.remove)");
	By filledHeart = By.cssSelector("#saved-item-1 > span.saved-item-icon-remove");
	By productOptionsBlock = By.id("product-options-wrapper");
	By productAttributes = By.cssSelector("#product-options-wrapper > dl > dd");
	By productAttributeFirstOption = By.cssSelector("#product-options-wrapper > dl > dd > div.input-box > div > span.swatch-option");
	By finishDropdown = By.cssSelector("#product-options-wrapper > dl > dd > div.input-box > select");
	By finishDropdown2 = By.cssSelector("#product-options-wrapper > dl > dd:nth-child(4) > div.input-box > select");
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToSimpleTestProduct() {
		visit(simpleTestProduct);
	}
	
	public void goToConfigTestProductColor() {
		visit(configTestProductColor);
	}
	
	public void goToConfigTestProductFinish() {
		visit(configTestProductFinish);
	}
	
	public void goToConfigTestProductColorFinish() {
		visit(configTestProductColorFinish);
	}

	public By getPrice() {
		return price;
	}
	
	public Boolean simpleProductAddToCartButtonPresent() {
		return waitForIsDisplayed(simpleProductAddToCartButton, 10);
	}
	
	public Boolean configProductAddToCartButtonPresent() {
		return waitForIsDisplayed(configProductAddToCartButton, 10);
	}
	
	public Boolean addToWishlistLinkPresent() {
		return waitForIsDisplayed(addToWishlistLink, 10);
	}
	
	public Boolean productPricePresent() {
		return waitForIsDisplayed(price, 10);
	}
	
	public Header clickSimpleProductAddToCart() {
		click(simpleProductAddToCartButton);
		return new Header(driver);
	}
	
	public Header clickConfigProductAddToCart() {
		click(configProductAddToCartButton);
		return new Header(driver);
	}
	
	public MyAccount clickAddToWishlistLink() {
		click(addToWishlistLink);
		return new MyAccount(driver);
	}
	
	public String getProductName() {
		return getText(productName);
	}
	
	public void clickAddToSavedItemsButton() {
		click(addToSavedItemsButton);
	}
	
	public Boolean emptyHeartDisplayed() {
		return waitForIsDisplayed(emptyHeart, 10);
	}
	
	public Boolean filledHeartDisplayed() {
		return waitForIsDisplayed(filledHeart, 10);
	}
	
	public void clickEmptyHeart() {
		click(emptyHeart);
	}
	
	public void clickFilledHeart() {
		click(filledHeart);
	}
	
	public By getEmptyHeart() {
		return emptyHeart;
	}
	
	public By getFilledHeart() {
		return filledHeart;
	}
	
	public int countProductAttributes() {
		return count(productAttributes);
	}
	
	public int countAttributeOptions(int attrOpt) {
		String s = attributeInputDD + ":nth-child(" + (attrOpt * 2) + ") > div.input-box > select > option";
		System.out.println(s);
		int ct = count(By.cssSelector(s));
		
		//subtract 1 from the number of options because the first option is always 'Select an option'
		return (ct - 1);
	}
	
	public void clickColorSwatchOption() {
		click(productAttributeFirstOption);
	}
	
	public void selectFinishOption() {
		Select finishDropdownSelect = new Select(driver.findElement(finishDropdown));
		finishDropdownSelect.selectByIndex(1);	
	}
	
	public void selectFinishOption2() {
		Select finishDropdownSelect = new Select(driver.findElement(finishDropdown2));
		finishDropdownSelect.selectByIndex(1);	
	}

}
