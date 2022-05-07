package com.simplilearn.medicare.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.simplilearn.ActionUtils;

public class LoginPage {
	WebDriver driver = null;
	
	By byLoadLogin = By.id("login");
	
	By byUserNameInput = By.id("username");
	
	By byPasswordInput = By.id("password");
	
	By byLoginButton = By.cssSelector("#loginForm .btn-primary");
	
	By byUserName = By.cssSelector("#userModel > a");
	
	By byLogoutButton = By.id("logout");
	
	By byErrorMessage = By.id("username-error");
	
	By byAlert = By.className("alert");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void loadLogin() {
		WebElement login = driver.findElement(byLoadLogin);
		login.click();
	}
	
	public void login(String userName, String password) throws InterruptedException {
		loadLogin();
		ActionUtils.wait(driver, byUserNameInput);
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
	
	public String getErrorMessage() {
		return driver.findElement(byErrorMessage).getText();
	}
	
	public String getAlert() {
		return driver.findElement(byAlert).getText();
	}
	
	public void logout() throws InterruptedException {
		driver.navigate().refresh();
		
		ActionUtils.wait(driver, byUserName);
		WebElement userName = driver.findElement(byUserName);
		Actions action = new Actions(driver);
		action.moveToElement(userName).click().build().perform();
		
		ActionUtils.jsWaitClick(driver, byLogoutButton);
		Thread.sleep(200);
	}
}
