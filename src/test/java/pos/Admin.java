package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Admin extends Base {

	String adminLoginUrl = "admin/";
	String adminCustomerGridUrl = "admin/customer/index/";
	String adminHomepageProducts = "admin/homepage_products/";
	String featuredProductGridRowString = "#featureditem_grid_table > tbody > tr";
	By customerGrid = By.cssSelector("#customerGrid_table > tbody");
	By customerGridCustomer = By.cssSelector("#customerGrid_table > tbody > tr");
	By customerGridActionsSelect = By.id("customerGrid_massaction-select");
	By submitButton = By.cssSelector("#customerGrid_massaction-form > fieldset > span:nth-child(4) > button");
	By successMessage = By.cssSelector("#messages > ul > li");
	By successMessageText = By.cssSelector("#messages > ul > li > ul > li > span");
	By loginEmail = By.id("username");
	By loginPw = By.id("login");
	By loginButton = By.cssSelector("#loginForm > div > div.form-buttons > input");
	By popup = By.id("message-popup-window");
	By popupClose = By.cssSelector("#message-popup-window > div.message-popup-head > a");
	By featuredProductsTab = By.cssSelector("#homepage_tabs_featureditems > span");
	By homepageProductsTabs = By.cssSelector("#homepage_tabs");
	By featuredProductsGrid = By.cssSelector("#featureditem_grid_table");
	By featuredProductGridRow = By.cssSelector("#featureditem_grid_table > tbody > tr");
	By loadingMask = By.id("#loading-mask");
	By saveButton = By.cssSelector("#content > div > div.content-header > p > button:nth-child(3)");
	By featuredItemsSuccessMessage = By.cssSelector("#messages > ul > li");
	By logoutLink = By.cssSelector("#html-body > div.wrapper > div.header > div.header-top > div > p > a");
	By priorityInputField = By.cssSelector("#priority");
	By savedFeaturedItemButton = By.cssSelector("#anchor-content > div > div.content-header > p > button:nth-child(3)");
	By editPriorityBackButton = By.cssSelector("#anchor-content > div > div.content-header > p > button:nth-child(1)");
	
	public Admin(WebDriver driver) {
		super(driver);
	}
	
	public void login(String un, String pw) {
		type(un, loginEmail);
		type(pw, loginPw);
		click(loginButton);
	}
	
	public Boolean popupDisplayed() {
		return waitForIsDisplayed(popup, 10);
	}
	
	public Boolean popupNotDisplayed() {
		return waitForIsNotDisplayed(popup, 10);
	}
	
	public void closePopup() {
		click(popupClose);
	}
	
	public void goToCustomerGrid() {
		visit(adminCustomerGridUrl);
	}
	
	public void goToHomepageProducts() {
		visit(adminHomepageProducts);
	}
	
	public void goToAdmin() {
		visit(adminLoginUrl);
	}
	
	public void logout() {
		click(logoutLink);
	}
	
	public int countCustomersDisplayed() {
		int ct = driver.findElements(customerGridCustomer).size();
		return ct;
	}
	
	public int findCustomer(String email) {
		int ct = countCustomersDisplayed();
		int row = 0;
		
		for(int i = 1; i<=ct;i++) {
			
			By em = By.cssSelector("#customerGrid_table > tbody > tr:nth-child(" + i + ") > td:nth-child(4)");
			
			if(getText(em).equals(email)) {
				//System.out.println("found!");
				row = i;
				//System.out.println(row);
				break;		
			}
			
		}
		
		return row;
	}
	
	public void deleteCustomer(String email) {
		int row = findCustomer(email);
		if (row == 0) {
			System.out.println("Customer Not found");
		} 
		else {
			driver.findElement(By.cssSelector("#customerGrid_table > tbody > tr:nth-child(" + row + ") > td:nth-child(1) > input")).click();
			selectDeleteFromSelect();
			clickSubmitButton();
		}
	}
	
	public void selectDeleteFromSelect() {
		Select actionDropdown = new Select(driver.findElement(customerGridActionsSelect));
		actionDropdown.selectByValue("delete");
	}
	
	public void clickSubmitButton() {
		click(submitButton);
	}
	
	public void alertWait() {
		waitForAlert(10);
	}
	
	public void clickOkOnAlert() {
		driver.switchTo().alert().accept();
	}
	
	public Boolean successMessageDisplayed() {
		return waitForIsDisplayed(successMessage);
	}
	
	public String getSuccessMessageText() {
		return getText(successMessageText);
	}
	
	public void clickFeaturedProductsTab() {
		click(featuredProductsTab);
	}
	
	public void clickFeaturedProductCheckbox(int prodNum) {
		String featuredProd = featuredProductGridRowString + ":nth-child(" + prodNum + ")";
		click(By.cssSelector(featuredProd));
	}
	
	public Boolean featuredProductsTabDisplayed() {
		return waitForIsDisplayed(featuredProductsTab, 10);
	}
	
	public Boolean homepageProductsTabsDisplayed() {
		return waitForIsDisplayed(homepageProductsTabs, 10);
	}
	
	public Boolean loadingMaskNotDisplayed() {
		return waitForIsNotDisplayed(loadingMask, 10);
	}
	
	public Boolean featuredProductGridDisplayed() {
		return waitForIsDisplayed(featuredProductsGrid, 10);
	}
	
	public void clickSaveButton() {
		click(saveButton);
	}
	
	public Boolean featuredItemsSuccessMessageDisplayed() {
		return waitForIsDisplayed(featuredItemsSuccessMessage, 10);
	}
	
	public int getProdId(int prodNum) {
		String featuredProd = featuredProductGridRowString + ":nth-child(" + prodNum + ") > td:nth-child(6)";
		String prodName = getText(By.cssSelector(featuredProd));
		String name = prodName.replaceAll("\\s", "");
		return Integer.parseInt(name);
	}
	
	public void clickFeaturedItemSortLink(int prodNum) {
		String featuredProd = featuredProductGridRowString + ":nth-child(" + prodNum + ") > td:nth-child(8) > a";
		click(By.cssSelector(featuredProd));
	}
	
	public Boolean sortInputDisplayed() {
		return waitForIsDisplayed(priorityInputField);
	}
	
	public Boolean savedFeaturedItemButtonDisplayed() {
		return waitForIsDisplayed(savedFeaturedItemButton, 10);
	}
	
	public void setPriority(int priority) {
		String p = Integer.toString(priority);
		type(p, priorityInputField);
		click(savedFeaturedItemButton);
	}
	
	public void clickEditPriorityBackButton() {
		click(editPriorityBackButton);
	}
}
