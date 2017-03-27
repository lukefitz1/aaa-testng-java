package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pos.Base;

public class SavedItems extends Base {

	String url = "saveditems/customer/view";
	By savedItemGrid = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > ul");
	By firstItemFilledHeart = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > ul > li > div.product-info > a > span.saved-item-icon-remove");
	By firstItemEmptyHeart = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > ul > li > div.product-info > a > span.saved-item-icon:not(.remove)");
	By firstItemInGrid = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > ul > li");
	
	public SavedItems(WebDriver driver) {
		super(driver);
	}
	
	public String getUrl() {
		return url;
	}
	
	public By getSavedItemGrid() {
		return savedItemGrid;
	}
	
	public Boolean savedItemGridDisplayed() {
		return waitForIsDisplayed(savedItemGrid, 10);
	}
	
	public Boolean firstItemFullHeartDisplayed() {
		return waitForIsDisplayed(firstItemFilledHeart, 10);
	}
	
	public Boolean firstItemEmptyHeartDisplayed() {
		return waitForIsDisplayed(firstItemEmptyHeart, 10);
	}
	
	public void clickFirstItemFilledHeart() {
		click(firstItemFilledHeart);
	}
	
	public By getFirstItemInGrid() {
		return firstItemInGrid;
	}
	
}
