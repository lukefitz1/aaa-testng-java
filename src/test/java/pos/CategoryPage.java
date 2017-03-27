package pos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import pos.Base;

public class CategoryPage extends Base {

	String testCategory = "test-cat1/test-subcat1";
	String testCategoryWHtml = "test-cat1/test-subcat1.html";
	String productSelector = "body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li";
	String additionalPriceString = " > div > div.price-box > span > span";
	String additionalSpecialPriceString = " > div > div.price-box > p:nth-child(2) > span:nth-child(2)";
	String sortByOptionsString = "body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > div > div > select > option";
	String showOptionsString = "body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.pager > div > div > select > option";
	String additionalNameString = " > div > h2 > a";
	By productGrid = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul");
	By productList = By.cssSelector("#products-list");
	By product = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li");
	By topToolbar = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar");
	By bottomToolbar = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar-bottom");
	By sortBySelect = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > div > div > select");
	By sortByOptions = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > div > div > select > option");
	By showOptions = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.pager > div > div > select > option");
	By firstSortByOption = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > div > div > select > option:nth-child(1)");
	By showSelect = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.pager > div > div > select");
	By sortByDirectionSwitch = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > div > a");
	By sortArrowUp = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > div > a.sort-by-switcher--asc");
	By sortArrowDown = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > div > a.sort-by-switcher--desc");
	By viewModeOptions = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > p");
	By viewAsNotSelectedOption = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > p > a");
	By viewAsSelectedOption = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar > div.sorter > p > strong");
	By viewModeOptionsBottomToolbar = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar-bottom > div > div.sorter > p");
	By viewAsNotSelectedOptionBottomToolbar = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar-bottom > div > div.sorter > p > a");
	By viewAsSelectedOptionBottomToolbar = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > div.toolbar-bottom > div > div.sorter > p > strong");
	By firstProductEmptyHeart = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div.product-info > a > span.saved-item-icon:not(.remove)");
	By firstProductFilledHeart = By.cssSelector("body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div.product-info > a > span.saved-item-icon-remove");
	
	public CategoryPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToTestCategory() {
		visit(testCategory);
	}
	
	public void goToTestCategoryWHtml() {
		visit(testCategoryWHtml);
	}
	
	public String getTestCatUrl() {
		return testCategory;
	}
	
	public Boolean sortArrowUpDisplayed() {
		return waitForIsDisplayed(sortArrowUp, 10);
	}
	
	public Boolean sortArrowDownDisplayed() {
		return waitForIsDisplayed(sortArrowDown, 10);
	}
	
	public Boolean sortBySelectDisplayed() {
		return waitForIsDisplayed(sortBySelect, 10);
	}
	
	public Boolean productGridDisplayed() {
		return waitForIsDisplayed(productGrid, 10);
	}
	
	public Boolean productListDisplayed() {
		return waitForIsDisplayed(productList, 10);
	}
	
	public String checkDefaultSort() {
		String defaultSort = "";
		defaultSort = getText(firstSortByOption).trim();
		return defaultSort;
	}
	
	public String checkSelectedSortOption() {
		String selectedSort = "";
		Select select = new Select(driver.findElement(sortBySelect));
		selectedSort = select.getFirstSelectedOption().getText().trim();
		return selectedSort;
	}
	
	public String checkSelectedShowOption() {
		String selectedSort = "";
		Select select = new Select(driver.findElement(showSelect));
		selectedSort = select.getFirstSelectedOption().getText().trim();
		return selectedSort;
	}
	
	public void sortBy(String option) {
		String selectedSort = checkSelectedSortOption();
		String sortOption = option;
		int ct = countSortOptions();
		int index = 0;
		Select sortSelect = new Select(driver.findElement(sortBySelect));
		
		for (int i = 1; i <= ct; i++) {
			String selectorPath = sortByOptionsString + ":nth-child(" + i + ")";
			By individualOption = By.cssSelector(selectorPath);
			
			if (getText(individualOption).trim().equalsIgnoreCase(sortOption)) {
				index = i;
				break;
			}
		}
		
		if (!selectedSort.equalsIgnoreCase(option)) {
			sortSelect.selectByIndex(index - 1);
		} else {
			System.out.println("This sort option is already selected!");
		}
		
	}
	
	public void showMore(String option) {
		String selectedShow = checkSelectedShowOption();
		String showOption = option; 
		int ct = countShowOptions();
		int index = 0;
		Select showMoreSelect = new Select(driver.findElement(showSelect));
		
		for (int i = 1; i <= ct; i++) {
			String selectorPath = showOptionsString + ":nth-child(" + i + ")";
			By individualOption = By.cssSelector(selectorPath);
			
			if (getText(individualOption).trim().equalsIgnoreCase(showOption)) {
				index = i;
				break;
			}
		}
		
		if (!selectedShow.equalsIgnoreCase(option) && index != 0) {
			showMoreSelect.selectByIndex(index - 1);	
		} else if (selectedShow.equalsIgnoreCase(option)) {
			System.out.println("This show option is already selected!");
		} else {
			System.out.println("This show option was not available");
		}
	}
	
	public Boolean validateSortByPrice() {
		int productCount = countProducts();
		String priceType1;
		String priceType2;
		double dub1 = 0;
		double dub2 = 0;
		Boolean correct = false;
		
		for(int i = 1; i < productCount; i++) {
			String prod1 = productSelector + ":nth-child("  + i + ")";
			String prod2 = productSelector + ":nth-child("  + (i + 1) + ")";
			priceType1 = determinePriceType(prod1);
			priceType2 = determinePriceType(prod2);
			
			if (priceType1.equalsIgnoreCase("regular")) {
				dub1 = Double.parseDouble(getText(By.cssSelector(prod1 + additionalPriceString)).replace("$", ""));
			} 
			else if (priceType1.equalsIgnoreCase("special")) {
				dub1 = Double.parseDouble(getText(By.cssSelector(prod1 + additionalSpecialPriceString)).replace("$", ""));
			}
	
			if (priceType2.equalsIgnoreCase("regular")) {
				dub2 = Double.parseDouble(getText(By.cssSelector(prod2 + additionalPriceString)).replace("$", ""));
			} 
			else if (priceType2.equalsIgnoreCase("special")) {
				dub2 = Double.parseDouble(getText(By.cssSelector(prod2 + additionalSpecialPriceString)).replace("$", ""));
			}
			
			if (dub1 <= dub2) {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
		}
		
		return correct;
	}
	
	public Boolean validateSortByPriceReverse() {
		int productCount = countProducts();
		String priceType1;
		String priceType2;
		double dub1 = 0;
		double dub2 = 0;
		Boolean correct = false;
		
		for(int i = 1; i < productCount; i++) {
			String prod1 = productSelector + ":nth-child("  + i + ")";
			String prod2 = productSelector + ":nth-child("  + (i + 1) + ")";
			priceType1 = determinePriceType(prod1);
			priceType2 = determinePriceType(prod2);
			
			if (priceType1.equalsIgnoreCase("regular")) {
				dub1 = Double.parseDouble(getText(By.cssSelector(prod1 + additionalPriceString)).replace("$", ""));
			} 
			else if (priceType1.equalsIgnoreCase("special")) {
				dub1 = Double.parseDouble(getText(By.cssSelector(prod1 + additionalSpecialPriceString)).replace("$", ""));
			}
	
			if (priceType2.equalsIgnoreCase("regular")) {
				dub2 = Double.parseDouble(getText(By.cssSelector(prod2 + additionalPriceString)).replace("$", ""));
			} 
			else if (priceType2.equalsIgnoreCase("special")) {
				dub2 = Double.parseDouble(getText(By.cssSelector(prod2 + additionalSpecialPriceString)).replace("$", ""));
			}
			
			if (dub1 >= dub2) {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
		}
		
		return correct;
	}
	
	public Boolean validateSortByName() {
		int productCount = countProducts();
		String name1;
		String name2;
		Boolean correct = false;
		
		for(int i = 1; i < productCount; i++) {
			String prod1 = productSelector + ":nth-child("  + i + ")";
			String prod2 = productSelector + ":nth-child("  + (i + 1) + ")";
			name1 = getName(prod1);
			name2 = getName(prod2);
			
			int compare = name1.compareTo(name2);
			if (compare <= 0) {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
			
		}
		
		return correct;
	}
	
	public Boolean validateSortByNameReverse() {
		int productCount = countProducts();
		String name1;
		String name2;
		Boolean correct = false;
		
		for(int i = 1; i < productCount; i++) {
			String prod1 = productSelector + ":nth-child("  + i + ")";
			String prod2 = productSelector + ":nth-child("  + (i + 1) + ")";
			name1 = getName(prod1);
			name2 = getName(prod2);
			
			int compare = name1.compareTo(name2);
			if (compare >= 0) {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
			
		}
		
		return correct;
	}
	
	public Boolean validateSortByPosition(Database db, int catID) throws SQLException {
		int productCount = countProducts();
		List<String> resultSet1 = new ArrayList<String>();
		List<String> resultSet2 = new ArrayList<String>();
		int prodId1;
		int prodId2;
		int pos1;
		int pos2;
		String name1;
		String name2;
		Boolean correct = false;
		
		for (int i = 1; i < productCount; i++) {
			String prod1 = productSelector + ":nth-child("  + i + ")";
			String prod2 = productSelector + ":nth-child("  + (i + 1) + ")";
			name1 = getName(prod1);
			name2 = getName(prod2);
			
			resultSet1 = db.executeQuery("select entity_id from catalog_product_entity_varchar where value='" + name1 + "'", 1);
			resultSet2 = db.executeQuery("select entity_id from catalog_product_entity_varchar where value='" + name2 + "'", 1);
			
			prodId1 = Integer.parseInt(resultSet1.get(0));
			prodId2 = Integer.parseInt(resultSet2.get(0));
			
			resultSet1 = db.executeQuery("select position from catalog_category_product where product_id=" + prodId1, 1);
			resultSet2 = db.executeQuery("select position from catalog_category_product where product_id=" + prodId2, 1);
			
			pos1 = Integer.parseInt(resultSet1.get(0));
			pos2 = Integer.parseInt(resultSet2.get(0));
			
			if (pos1 <= pos2) {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
			
		}
		
		return correct;
	}
	
	public Boolean validateSortByPositionReverse(Database db, int catID) throws SQLException {
		int productCount = countProducts();
		List<String> resultSet1 = new ArrayList<String>();
		List<String> resultSet2 = new ArrayList<String>();
		int prodId1;
		int prodId2;
		int pos1;
		int pos2;
		String name1;
		String name2;
		Boolean correct = false;
		
		for (int i = 1; i < productCount; i++) {
			String prod1 = productSelector + ":nth-child("  + i + ")";
			String prod2 = productSelector + ":nth-child("  + (i + 1) + ")";
			name1 = getName(prod1);
			name2 = getName(prod2);
			
			resultSet1 = db.executeQuery("select entity_id from catalog_product_entity_varchar where value='" + name1 + "'", 1);
			resultSet2 = db.executeQuery("select entity_id from catalog_product_entity_varchar where value='" + name2 + "'", 1);
			
			prodId1 = Integer.parseInt(resultSet1.get(0));
			prodId2 = Integer.parseInt(resultSet2.get(0));
			
			resultSet1 = db.executeQuery("select position from catalog_category_product where product_id=" + prodId1, 1);
			resultSet2 = db.executeQuery("select position from catalog_category_product where product_id=" + prodId2, 1);
			
			pos1 = Integer.parseInt(resultSet1.get(0));
			pos2 = Integer.parseInt(resultSet2.get(0));
			
			if (pos1 >= pos2) {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
			
		}
		
		return correct;
	}
	
	public String getName(String prod) {
		By product = By.cssSelector(prod + additionalNameString);
		return getText(product);	
	}
	
	public int countSortOptions() {
		int ct = driver.findElements(sortByOptions).size();
		return ct;
	}
	
	public int countShowOptions() {
		int ct = driver.findElements(showOptions).size();
		return ct;
	}

	public int countProducts() {
		int ct = driver.findElements(product).size();
		return ct;
	}
	
	public String determinePriceType(String prod) {
		String priceType = " ";
		
		
		if (hasRegularPrice(prod) == true)  {
			priceType = "regular";
		}
		else if (hasSpecialPrice(prod) == true) {
			priceType = "special";
		}
		
		return priceType;
	}
	
	public Boolean hasRegularPrice(String prod) {
		By product = By.cssSelector(prod + additionalPriceString);
		
		if(isPresent(product)) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public Boolean hasSpecialPrice(String prod) {
		By product = By.cssSelector(prod + additionalSpecialPriceString);
		
		if(isPresent(product)) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public void clickSortDirectionButton() {
		click(sortByDirectionSwitch);
	}
	
	public Boolean viewOptionsDisplayed() {
		return waitForIsDisplayed(viewModeOptions, 10);
	}
	
	public void clickViewAsList() {
		click(viewAsNotSelectedOption);
	}
	
	public void clickViewAsGrid() {
		click(viewAsNotSelectedOption);
	}
	
	public String getSelectedViewOption() {
		return getText(viewAsSelectedOption);
	}
	
	public Boolean viewOptionsDisplayedBottomToolbar() {
		return waitForIsDisplayed(viewModeOptions, 10);
	}
	
	public void clickViewAsListBottomToolbar() {
		click(viewAsNotSelectedOption);
	}
	
	public void clickViewAsGridBottomToolbar() {
		click(viewAsNotSelectedOption);
	}
	
	public String getSelectedViewOptionBottomToolbar() {
		return getText(viewAsSelectedOption);
	}
	
	public Boolean firstProductEmptyHeart() {
		return waitForIsDisplayed(firstProductEmptyHeart, 10);
	}
	
	public Boolean firstProductFullHeart() {
		return waitForIsDisplayed(firstProductFilledHeart, 10);
	}
	
	public void clickFirstProductEmptyHeart() {
		click(firstProductEmptyHeart);
	}
	
	public void clickFirstProductFullHeart() {
		click(firstProductFilledHeart);
	}
	
	public By getFirstProductEmptyHeart() {
		return firstProductEmptyHeart;
	}
	
	public By getFirstProductFullHeart() {
		return firstProductFilledHeart;
	}
	
}
