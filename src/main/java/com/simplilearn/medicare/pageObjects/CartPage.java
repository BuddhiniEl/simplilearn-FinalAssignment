package com.simplilearn.medicare.pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplilearn.ActionUtils;
import com.simplilearn.DataUtils;
import com.simplilearn.model.CartItem;

public class CartPage {
	WebDriver driver;

	
	By byContinueShopping = By.className("btn-warning");
	By byCheckout = By.className("btn-success");
	
	By byRows = By.cssSelector("#cart tbody tr");
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visit() throws InterruptedException {
		driver.get("http://localhost:8080/medicare/cart/show?result=added");
	}
	

	public void continueShopping() {
		new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.presenceOfElementLocated(byContinueShopping));
		WebElement cont= driver.findElement(byContinueShopping);
		ActionUtils.jsClick(driver, cont);
	}
	
	public void checkout() {
		WebElement cont= driver.findElement(byCheckout);
		ActionUtils.jsClick(driver, cont);
	}
	
	public List<CartItem> getItems(){
		List<WebElement> rows = driver.findElements(byRows);
		
		List<CartItem> items = new ArrayList<>();
		for(WebElement row:rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			CartItem item = new CartItem(
					cols.get(0).findElement(By.tagName("h4")).getText(),
						DataUtils.getPrice(cols.get(1).getText()),
						Integer.parseInt(cols.get(2).findElement(By.tagName("input")).getAttribute("value")),
						DataUtils.getPrice(cols.get(3).getText())
					);
			
			items.add(item);
//			System.out.println(cols.get(0).findElement(By.tagName("h4")).getText());
//			System.out.println(cols.get(1).getText());
//			System.out.println(cols.get(2).findElement(By.tagName("input")).getAttribute("value"));
//			System.out.println(cols.get(3).getText());
		}
		
		return items;
	}
}
