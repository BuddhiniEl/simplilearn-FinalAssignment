package com.simplilearn;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.simplilearn.medicare.pageObjects.LoginPage;
import com.simplilearn.medicare.pageObjects.SearchPage;

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
		Thread.sleep(1000);

		assertNull(loginPage.getUserName());
	}

	@Test
	public void loginAsUser() throws Exception {
		new VisitMedicare(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("kn@gmail.com", "12345");
		Thread.sleep(1000);
		assertEquals(loginPage.getUserName(), "Kavita Nigam");
		loginPage.logout();
		Thread.sleep(1000);

		assertNull(loginPage.getUserName());
	}
	
	@Test
	public void invalidUsername() throws Exception {
		new VisitMedicare(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("kn1@gmail.com", "12345");
		Thread.sleep(1000);
		assertEquals(loginPage.getAlert(), "Username and Password is invalid!");
	}
	
	@Test
	public void invalidPassword() throws Exception {
		new VisitMedicare(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("kn@gmail.com", "123456");
		Thread.sleep(1000);
		assertEquals(loginPage.getAlert(), "Username and Password is invalid!");
	}
	
	@Test
	public void invalidEmailAddress() throws Exception {
		new VisitMedicare(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("kn", "123456");
		Thread.sleep(1000);
		assertEquals(loginPage.getErrorMessage(), "Please enter a valid email address!");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
