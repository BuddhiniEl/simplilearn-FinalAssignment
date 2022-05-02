package com.simplilearn.swiggy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
	WebDriver driver = null;
	
	By byLoadLogin = By.id("login");
	
	By byUserNameInput = By.id("username");
	
	By byPasswordInput = By.id("password");
	
	By byLoginButton = By.cssSelector("#loginForm .btn-primary");
	
	By byUserName = By.cssSelector("#userModel > a");
	
	
	By byLogoutButton = By.id("logout");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void loadLogin() {
		WebElement login = driver.findElement(byLoadLogin);
		login.click();
	}
	
	public void login(String userName, String password) throws InterruptedException {
		loadLogin();
		Thread.sleep(1000);
		WebElement userNameElement = driver.findElement(byUserNameInput);
		userNameElement.sendKeys(userName);
		WebElement passElement = driver.findElement(byPasswordInput);
		passElement.sendKeys(password);
		
		WebElement login = driver.findElement(byLoginButton);
		login.click();
	}

	public String getUserName() {
		List<WebElement> eles = driver.findElements(byUserName);
		
		if(eles.size() == 1) {
			return eles.get(0).getText();
		}
		
		return null;
	}
	
	public void logout() throws InterruptedException {
		WebElement userName = driver.findElement(byUserName);
		System.out.println(userName.getText());
		Actions action = new Actions(driver);
		action.moveToElement(userName).click().build().perform();
		Thread.sleep(100);
		WebElement ele = driver.findElement(byLogoutButton);
		ele.click();
		Thread.sleep(100);
	}
}
