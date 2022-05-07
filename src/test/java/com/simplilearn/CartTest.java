package com.simplilearn;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.simplilearn.medicare.pageObjects.CartPage;
import com.simplilearn.medicare.pageObjects.CheckoutPage;
import com.simplilearn.medicare.pageObjects.InvoicePage;
import com.simplilearn.medicare.pageObjects.LoginPage;
import com.simplilearn.medicare.pageObjects.ProductViewPage;
import com.simplilearn.model.Item;

public class CartTest {
	WebDriver driver = null;
	LoginPage loginPage = null;
	
	@BeforeTest
	public void setUp() throws Exception {
		BrowserDriver browserDriver = new BrowserDriver();
		driver = browserDriver.getDriver();
		new VisitMedicare(driver);
		loginPage = new LoginPage(driver);
		loginPage.login("kn@gmail.com", "12345");
	}
	
	@Test
	public void checkout() throws InterruptedException {
		Thread.sleep(500);
		ProductViewPage productViewPage = new ProductViewPage(driver);
		CartPage cartPage = new CartPage(driver);
		productViewPage.visit();
		Thread.sleep(500);
		productViewPage.addToCart("Diclofenac");
		
		
//		Thread.sleep(500);
		
		cartPage.continueShopping();
		Thread.sleep(500);
		productViewPage.addToCart("Amoxicillin");
		Thread.sleep(200);
		
		cartPage.checkout();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.select();
		
		checkoutPage.setCardNumber("1234123412341234");
		checkoutPage.setExpiryMonth("12");
		checkoutPage.setExpiryYear("23");
		checkoutPage.setCvCode("345");
		checkoutPage.pay();
		
		InvoicePage invoicePage = new InvoicePage(driver);
		assertEquals(invoicePage.getTitle(), "Invoice");
		assertEquals(invoicePage.getItems().size(), 2);

		assertEquals(invoicePage.getItems().get(0).getName(), "Diclofenac");
		assertEquals(invoicePage.getItems().get(1).getName(), "Amoxicillin");
		invoicePage.contitnueShopping();
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		loginPage.logout();
		driver.quit();
	}
}
