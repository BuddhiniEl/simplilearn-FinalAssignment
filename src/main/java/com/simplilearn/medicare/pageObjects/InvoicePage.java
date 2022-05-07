package com.simplilearn.medicare.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.simplilearn.ActionUtils;
import com.simplilearn.DataUtils;
import com.simplilearn.model.Item;

public class InvoicePage {
	WebDriver driver;

	
	By byTitle = By.tagName("h2");
	By byRows = By.cssSelector(".table-condensed tbody tr");
	By byContinueShopping = By.className("btn-warning");
	
	public InvoicePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		ActionUtils.wait(driver, byTitle);
		return driver.findElement(byTitle).getText();
	}
	
	public List<Item> getItems(){
		List<WebElement> rows = driver.findElements(byRows);
		
		List<Item> items = new ArrayList<>();
		for(WebElement row:rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			Item item = new Item(
					cols.get(0).getText(),
						DataUtils.getPrice(cols.get(1).getText()),
						Integer.parseInt(cols.get(2).getText()),
						DataUtils.getPrice(cols.get(3).getText())
					);
			
			items.add(item);
		}
		
		return items;
	}
	
	public void contitnueShopping() {
		WebElement ele = driver.findElement(byContinueShopping);
		ActionUtils.jsClick(driver, ele);
	}
}
