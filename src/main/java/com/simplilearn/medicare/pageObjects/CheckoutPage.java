package com.simplilearn.medicare.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.simplilearn.ActionUtils;

public class CheckoutPage {
	WebDriver driver;

	
	By bySelect = By.className("btn-primary");
	By byCard = By.id("cardNumber");
	By byExpiryMonth = By.id("expityMonth");
	By byExpiryYear = By.id("expityYear");
	By byCvCode = By.id("cvCode");
	By byPay = By.className("btn-success");
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	

	public void select() {
		ActionUtils.wait(driver, bySelect);
		WebElement select= driver.findElement(bySelect);
		ActionUtils.jsClick(driver, select);
	}
	
	public void setCardNumber(String cardNumber) {
		ActionUtils.wait(driver, byCard);
		WebElement num = driver.findElement(byCard);
		num.sendKeys(cardNumber);
	}
	
	public void setExpiryMonth(String month) {
		WebElement mon = driver.findElement(byExpiryMonth);
		mon.sendKeys(month);
	}
	
	public void setExpiryYear(String year) {
		WebElement yr = driver.findElement(byExpiryMonth);
		yr.sendKeys(year);
	}
	
	public void setCvCode(String code) {
		WebElement cv = driver.findElement(byCvCode);
		cv.sendKeys(code);
	}
	
	public void pay() {
		WebElement payEle = driver.findElement(byPay);
		ActionUtils.jsClick(driver, payEle);
	}
}
