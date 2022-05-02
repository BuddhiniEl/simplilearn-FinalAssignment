package com.simplilearn;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserDriver {
	
	WebDriver driver = null;
	
	public WebDriver getDriver() throws MalformedURLException {
		if (driver == null) {
	         driver = getRemoteDriver();
		}
		return driver;
	}
	
	private WebDriver getRemoteDriver() throws MalformedURLException {
		 String hub = "http://localhost:4444";
         DesiredCapabilities cap = new DesiredCapabilities();
         cap.setBrowserName("firefox");
         
         return new RemoteWebDriver(new URL(hub), cap);
	}
	
	private WebDriver getLocalDriver() {
		System.setProperty("webdriver.gecko.driver","/home/rube/projects/selenium-drivers/geckodriver"); // Setting system properties of FirefoxDriver
		return new FirefoxDriver(); //Creating an object of FirefoxDriver
	}
}
