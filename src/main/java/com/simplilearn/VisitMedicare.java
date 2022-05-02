package com.simplilearn;

import org.openqa.selenium.WebDriver;

public class VisitMedicare {
	
	VisitMedicare(WebDriver driver) throws InterruptedException{
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/medicare");
		Thread.sleep(2000);
	}

}
