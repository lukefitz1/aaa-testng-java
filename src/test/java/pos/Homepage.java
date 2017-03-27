package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage extends Base {

	By heroWrap = By.cssSelector("body > div.wrapper > div > div.hero-wrap");
	By featuredItemsBlock = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.home-products");
	By featuredItemsUnorderedList = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.home-products > div > ul");
	By featuredItem = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.home-products > div > ul > li");
	By featuredItem1 = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.home-products > div > ul > li:nth-child(1) > div.product-info > div.product-info-top > a > span.saved-item-icon:not(.remove)");
	By featuredItem1Filled = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.home-products > div > ul > li:nth-child(1) > div.product-info > div.product-info-top > a > span.saved-item-icon-remove");
	By emptyHeart = By.cssSelector("#saved-item-1 > span.saved-item-icon:not(.remove)");
	By filledHeart = By.cssSelector("#saved-item-1 > span.saved-item-icon-remove");
	By video1Row = By.cssSelector("#vid-1-row");
	By motivationNationRow = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div.std > div.grid-container.motivation-nation");
	By promo1x2Row = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div.std > div.grid-container.promo-1x2");
	By video2Row = By.cssSelector("#vid-2-row");
	By freeShippingRow = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div.std > div.grid-container.free-shipping-block");
	By threeUpRow = By.cssSelector("#three-up-row");
	By promo2x2Row = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div.std > div.grid-container.promo-2x2");
	By chainStationRow = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div.std > div.grid-container.home-chain-station");
	By products4UpRow = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div.std > div.home-products");
	String featuredItemString = "body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.home-products > div > ul > li";
	
	public Homepage(WebDriver driver) {
		super(driver);
	}
	
	public void goToHomepage() {
		visit("");
	}
	
	public Boolean heroDisplayed() {
		return waitForIsDisplayed(heroWrap, 10);
	}
	
	public By getHero() {
		return heroWrap;
	}
	
	public Boolean video1RowDisplayed() {
		return waitForIsDisplayed(video1Row, 10);
	}
	
	public By getVideo1Row() {
		return video1Row;
	}
	
	public Boolean motivationNationRowDisplayed() {
		return waitForIsDisplayed(motivationNationRow, 10);
	}
	
	public By getMotivationNationRow() {
		return motivationNationRow;
	}
	
	public Boolean promo1x2RowDisplayed() {
		return waitForIsDisplayed(promo1x2Row, 10);
	}
	
	public By getPromo1x2Row() {
		return promo1x2Row;
	}
	
	public Boolean video2RowDisplayed() {
		return waitForIsDisplayed(video2Row, 10);
	}
	
	public By getVideo2Row() {
		return video2Row;
	}
	
	public Boolean freeShippingRowDisplayed() {
		return waitForIsDisplayed(freeShippingRow, 10);
	}
	
	public By getFreeShippingRow() {
		return freeShippingRow;
	}
	
	public Boolean threeUpRowDisplayed() {
		return waitForIsDisplayed(threeUpRow, 10);
	}
	
	public By getThreeUpRow() {
		return threeUpRow;
	}
	
	public Boolean promo2x2RowDisplayed() {
		return waitForIsDisplayed(promo2x2Row, 10);
	}
	
	public By getPromo2x2Row() {
		return promo2x2Row;
	}
	
	public Boolean chainStationRowDisplayed() {
		return waitForIsDisplayed(chainStationRow, 10);
	}
	
	public By getChainStationRow() {
		return chainStationRow;
	}
	
	public Boolean products4UpRowDisplayed() {
		return waitForIsDisplayed(products4UpRow, 10);
	}
	
	public By getProducts4UpRow() {
		return products4UpRow;
	}
	
	public Boolean featuredItemsBlockDisplayed() {
		return waitForIsDisplayed(featuredItemsBlock, 10);
	}
	
	public int countFeaturedItems() {
		return count(featuredItem);
	}
	
	public Boolean emptyHeartDisplayed() {
		return waitForIsDisplayed(emptyHeart, 10);
	}
	
	public void clickFirstEmptyHeart() {
		click(featuredItem1);
	}
	
	public void clickFirstFilledHeart() {
		click(featuredItem1Filled);
	}
	
	public Boolean firstHeartFilled() {
		return waitForIsDisplayed(featuredItem1Filled, 10);
	}
	
	public Boolean firstHeartEmpty() {
		return waitForIsDisplayed(featuredItem1, 10);
	}
	
	public By getFirstSavedItemsHeartEmpty() {
		return featuredItem1;
	}
	
	public By getFirstSavedItemsHeartFull() {
		return featuredItem1Filled;
	}
	
	public String getFeaturedItemName(int prodNum) {
		String prod = featuredItemString + ":nth-child(" + prodNum + ") > div.product-info > h2 > a";
		String x =  getText(By.cssSelector(prod));
		return x;
	}
	
	public void closeLocationAlert() {
		waitForLocationAlert();
		closeAlert();
	}
	
	public void waitForLocationAlert() {
		waitForAlert(10);
	}

}
