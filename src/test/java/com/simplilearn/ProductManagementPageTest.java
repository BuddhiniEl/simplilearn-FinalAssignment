package com.simplilearn;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.simplilearn.medicare.pageObjects.LoginPage;
import com.simplilearn.medicare.pageObjects.ProductManagementPage;

@Test
public class ProductManagementPageTest {
	WebDriver driver = null;
	
	By byName = By.id("name");
	By byBrand = By.id("brand");
	By byDescription = By.id("description");
	By byUnitPrice = By.id("unitPrice");
	By byQuantity = By.id("quantity");
	By byFile = By.id("file");
	By byCategoryId = By.id("categoryId");
	By bySubmit = By.cssSelector("#product .btn-primary");
	LoginPage loginPage;
	
	@BeforeTest
	public void setUp() throws Exception {
		BrowserDriver browserDriver = new BrowserDriver();
		driver = browserDriver.getDriver();
		new VisitMedicare(driver);
		loginPage = new LoginPage(driver);
		loginPage.login("vk@gmail.com", "admin");
	}
	
	
	@Test
	public void addProduct() throws Exception {
		Thread.sleep(1000);
		ProductManagementPage productManagementPage = new ProductManagementPage(driver);
		productManagementPage.visit();
		
		int prodCount = productManagementPage.getProductCount();
		
		String prodName = "Fentanyl" + prodCount;
		productManagementPage.setName(prodName);
		productManagementPage.setBrand("Intas");
		productManagementPage.setDescription("a powerful synthetic opioid analgesic that is similar to morphine");
		productManagementPage.setUntiPrice("40.0");
		productManagementPage.setQuantity("" + (5 + prodCount));
		productManagementPage.setFile("FentanylSmall.jpg");
		productManagementPage.setCategoryId("Analgesics");
		productManagementPage.submit();
		
		Thread.sleep(100);
		assertTrue(productManagementPage.hasProduct(prodName), "Faield to add product " + prodName);
	}

	@Test
	public void editProduct() throws Exception{
		Thread.sleep(1000);
		ProductManagementPage productManagementPage = new ProductManagementPage(driver);
		productManagementPage.visit();
		
		productManagementPage.editProductByName("Aceclofenac");
		Thread.sleep(200);
		productManagementPage.setUntiPrice("45.0");
		productManagementPage.submit();
		Thread.sleep(100);

		productManagementPage.editProductByName("Aceclofenac");
		Thread.sleep(200);
		assertEquals(productManagementPage.getUnitPrice(), "45.0");
		
		//Revert back to previous value
		productManagementPage.setUntiPrice("40.0");
		productManagementPage.submit();
		Thread.sleep(100);
		productManagementPage.editProductByName("Aceclofenac");
		Thread.sleep(200);
		assertEquals(productManagementPage.getUnitPrice(), "40.0");
		
	}

	@Test
	public void activateProduct() throws Exception{
		Thread.sleep(1000);
		ProductManagementPage productManagementPage = new ProductManagementPage(driver);
		productManagementPage.visit();
		
		productManagementPage.activateProductByName("Paracetamol", false);
		driver.navigate().refresh();
		Thread.sleep(100);
		assertFalse(productManagementPage.isActive("Paracetamol"));
		
		//Revert to previous state
		Thread.sleep(500);
		productManagementPage.activateProductByName("Paracetamol", true);
		driver.navigate().refresh();
		Thread.sleep(100);
		assertTrue(productManagementPage.isActive("Paracetamol"));
	}
		
	@AfterTest
	public void tearDown() throws InterruptedException {
		loginPage.logout();
		driver.quit();
	}
}
