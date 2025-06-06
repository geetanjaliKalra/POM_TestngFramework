package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AddToCartPage {
	private WebDriver driver;
	private ElementUtil eutil;
	private By successmsglocator= By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
		eutil=new ElementUtil(driver);
	}

	public String getSuccessMsg() {
		//System.out.println("succes msg is from addtocartpage class "+eutil.getElement(successmsglocator).getText());
		return eutil.waitForElementVisible(successmsglocator, TimeUtil.DEFAULT_LONG_TIME).getText();
		
		
	}
}
