package com.simplilearn.medicare.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplilearn.ActionUtils;

public class ProductViewPage {
	WebDriver driver;

	
	By byRows = By.cssSelector("#productListTable_wrapper tbody tr");
	
	public ProductViewPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visit() throws InterruptedException {
		driver.get("http://localhost:8080/medicare/show/all/products");
	}
	
	public void addToCart(String name) throws InterruptedException {

		List<WebElement> rows = driver.findElements(byRows);
		for(WebElement row:rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			
			if(name.equals(cols.get(1).getText())) {
				WebElement add = cols.get(5).findElement(By.cssSelector("a.btn-success"));
				
				new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.elementToBeClickable(add));
				ActionUtils.jsClick(driver, add);
				break;
			}
		}
		
	}
}
