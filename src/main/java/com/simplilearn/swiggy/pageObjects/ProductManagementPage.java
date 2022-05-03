package com.simplilearn.swiggy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.simplilearn.ActionUtils;

public class ProductManagementPage {
	WebDriver driver;
	
	By byManageProductNavBar = By.id("manageProduct");

	By byName = By.id("name");
	
	By byBrand = By.id("brand");
	
	By byDescription = By.id("description");
	
	By byUnitPrice = By.id("unitPrice");
	
	By byQuantity = By.id("quantity");
	
	By byFile = By.id("file");
	
	By byCategoryId = By.id("categoryId");
	
	By bySubmit = By.cssSelector("#product .btn-primary");
	
	By byProductTableRows = By.cssSelector("#productsTable tbody tr");
	
	By byProductTableInfo = By.id("productsTable_info");
	
	public ProductManagementPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void visit() throws InterruptedException {
		WebElement manageProductNavBar = driver.findElement(byManageProductNavBar);
		ActionUtils.moveAndClick(driver, manageProductNavBar);
	}
	public void setName(String name) {
		driver.findElement(byName).sendKeys(name);
	}
	
	public void setBrand(String brand) {
		driver.findElement(byBrand).sendKeys(brand);
	}
	
	public void setDescription(String description) {
		driver.findElement(byDescription).sendKeys(description);
	}
	

	public void setUntiPrice(String unitPrice) {
		WebElement ele = driver.findElement(byUnitPrice);
		ele.clear();
		ele.sendKeys(unitPrice);
	}

	public void setQuantity(String quantity) {
		WebElement ele = driver.findElement(byQuantity);
		ele.clear();
		ele.sendKeys(quantity);
	}

	public void setFile(String file) {
		driver.findElement(byFile).sendKeys(file);
	}
	
	public void setCategoryId(String categoryId) {
		driver.findElement(byCategoryId).sendKeys(categoryId);
	}

	public void editProductByName(String name) throws Exception {
		List<WebElement> rows = driver.findElements(byProductTableRows);
		
		for(WebElement rowEle:rows) {
			List<WebElement> cols = rowEle.findElements(By.tagName("td"));
			if(cols.get(2).getText().equals(name)) {
				WebElement a = cols.get(7).findElement(By.tagName("a"));
				ActionUtils.scroll(driver, 0, 1000);
				ActionUtils.moveAndClick(driver, a);
				ActionUtils.scroll(driver, 0, -1000);
				break;
			}
		}
	}
	
	public void activateProductByName(String name, boolean activate) throws Exception {
		List<WebElement> rows = driver.findElements(byProductTableRows);
		
		for(WebElement rowEle:rows) {
			List<WebElement> cols = rowEle.findElements(By.tagName("td"));
			if(cols.get(2).getText().equals(name)) {
				List<WebElement> checkedList = cols.get(6).findElements(By.cssSelector("input[checked=\"checked\"]"));
				if((checkedList.size() > 0 && !activate) || (checkedList.size() == 0 && activate)) {
					//Toggle to desired state
					ActionUtils.scroll(driver, 0, 200);
					ActionUtils.moveAndClick(driver, cols.get(6).findElement(By.className("slider")));
					
					Thread.sleep(500);

					ActionUtils.scroll(driver, 0, -200);
					WebElement confirm = driver.findElement(By.cssSelector(".bootbox .btn-primary"));
					confirm.click();
					Thread.sleep(200);
					confirm = driver.findElement(By.cssSelector(".bootbox .btn-primary"));
					confirm.click();
					Thread.sleep(200);
				}
				break;
			}
		}
	}
	
	public boolean hasProduct(String name) {
		List<WebElement> rows = driver.findElements(byProductTableRows);
		
		for(WebElement rowEle:rows) {
			List<WebElement> cols = rowEle.findElements(By.tagName("td"));
			if(cols.get(2).getText().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUnitPrice() {
		return driver.findElement(byUnitPrice).getAttribute("value");
	}
	
	public boolean isActive(String name) {
		List<WebElement> rows = driver.findElements(byProductTableRows);
		
		for(WebElement rowEle:rows) {
			List<WebElement> cols = rowEle.findElements(By.tagName("td"));
			if(cols.get(2).getText().equals(name)) {
				List<WebElement> checkedList = cols.get(6).findElements(By.cssSelector("input[checked=\"checked\"]"));
				return checkedList.size() > 0;
			}
		}
		return false;
	}
	
	public void submit() throws InterruptedException{
		ActionUtils.moveAndClick(driver, driver.findElement(bySubmit));
	}
	
	public int getProductCount() {
		String[] info = driver.findElement(byProductTableInfo).getText().split(" ");
		String count = info[info.length - 2];
		return Integer.parseInt(count);
	}
}
