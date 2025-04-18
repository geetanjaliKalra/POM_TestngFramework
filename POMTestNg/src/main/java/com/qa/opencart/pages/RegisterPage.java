package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eutil;
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By phone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//input[@name=\"newsletter\"][@value=1]");
	private By subscribeNo = By.xpath("//input[@name=\"newsletter\"][@value=0]");
	private By agree = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit'][@value='Continue']");
	private By successMsg = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public boolean doRegister(String firstname, String lastname, String email, String phone, 
			String password,
		 String subscribe) {
		eutil.doSendKeys(this.firstname, firstname, TimeUtil.DEFAULT_TIME);
		eutil.doSendKeys(this.lastname, lastname);
		eutil.doSendKeys(this.email, email);
		eutil.doSendKeys(this.phone, phone);
		eutil.doSendKeys(this.password, password);
		eutil.doSendKeys(this.confirmpassword, password);
		if (subscribe.equalsIgnoreCase("yes")) {
			eutil.doClick(subscribeYes);
		} else
			eutil.doClick(subscribeNo);
		eutil.doClick(agree);
		eutil.doClick(continueBtn);
		String successMsg=eutil.waitForElementVisible(this.successMsg,TimeUtil.DEFAULT_TIME).getText();
		
		if(successMsg.contains(AppConstants.SuccessMSG)) {
		
		eutil.doClick(logoutLink);
		eutil.doClick(registerLink);
		return true;
		}
		else
		return false;
	}
}