package com.simplilearn;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtils {
	//To avoid "s not clickable at point ... because another element <div class="...."> obscures it"
	public static void moveAndClick(WebDriver driver, WebElement element) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		Thread.sleep(1000);
	}
	
	public static void jsClick(WebDriver driver, WebElement element) {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public static void jsClick(WebDriver driver, By by) {
		WebElement ele = driver.findElement(by);
		jsClick(driver, ele);
	}
	
	public static void jsWaitClick(WebDriver driver, By by) {
		wait(driver, by);
		WebElement ele = driver.findElement(by);
		jsClick(driver, ele);
	}
	
	public static void clearInput(WebDriver driver, WebElement element) {
		int len = element.getText().length();
		
		for(int i = 0; i < len +1; i++) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}
	
	public static void wait(WebDriver driver, By by) {
		new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public static void scroll(WebDriver driver, int x, int y) throws Exception {
	    ((JavascriptExecutor)driver).executeScript(String.format("window.scrollBy(%d,%d)", x, y));
	    Thread.sleep(500);
	}
}
