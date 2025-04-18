package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eutil;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgetpasswordlink = By.xpath("//input[@value='Login']");
	private By registerlink= By.linkText("Register");

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		String title = eutil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Login page title " + title);
		return title;
	}

	public String getLoginPageURL() {
		String loginPageUrl = eutil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("Login page URL " + loginPageUrl);
		return loginPageUrl;
	}

	public boolean checkForgetPasswordLink() {
		return eutil.doIsDisplayed(forgetpasswordlink);
	}

	public AccountsPage doLogin(String username, String pwd) {
		eutil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_TIME);
		eutil.doSendKeys(password, pwd);
		eutil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		eutil.doClick(registerlink, TimeUtil.DEFAULT_TIME);
		return new RegisterPage(driver);
	}
	
}
