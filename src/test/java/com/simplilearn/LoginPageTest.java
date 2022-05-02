package com.simplilearn;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.simplilearn.swiggy.pageObjects.LoginPage;
import com.simplilearn.swiggy.pageObjects.SearchPage;

@Test
public class LoginPageTest {
	WebDriver driver = null;
	
	@BeforeTest
	public void setUp() throws Exception {
		BrowserDriver browserDriver = new BrowserDriver();
		driver = browserDriver.getDriver();
	}
	
	
	@Test
	public void loginAsAdmin() throws Exception {
		new VisitMedicare(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("vk@gmail.com", "admin");
		Thread.sleep(1000);
		assertEquals(loginPage.getUserName(), "Vikas Kashyap");
		loginPage.logout();
	}

	@Test
	public void loginAsUser() throws Exception {
		new VisitMedicare(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("kn@gmail.com", "12345");
		Thread.sleep(1000);
		assertEquals(loginPage.getUserName(), "Kavita Nigam");
		loginPage.logout();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
