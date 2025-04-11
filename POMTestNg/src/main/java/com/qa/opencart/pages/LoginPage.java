package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	//ElementUtil ele;
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginbtn=By.xpath("//input[@value='Login']");
	private By forgetpasswordlink=By.xpath("//input[@value='Login']");
	
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
	}
			
	
	public String getLoginPageTitle() {
		String title = driver.getTitle();
		System.out.println("Login page title "+ title);
		return title;
	}
	
	public String getLoginPageURL() {
		String loginPageUrl = driver.getCurrentUrl();
		System.out.println("Login page URL "+ loginPageUrl);
		return loginPageUrl;
	}
	
	public boolean checkForgetPasswordLink() {
		return driver.findElement(forgetpasswordlink).isDisplayed();
	}
	
	public String doLogin(String username,String pwd) {
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginbtn).click();
		String title=driver.getTitle();
		System.out.println("Title after login is "+title);
		return title;
	}
}
