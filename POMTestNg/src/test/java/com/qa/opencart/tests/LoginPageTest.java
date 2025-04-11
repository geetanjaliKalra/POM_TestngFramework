package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	public void loginPageTitletest() {
		String actualloginPageTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actualloginPageTitle,AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Test(priority=2)
	public void loginPageURLtest() {
		String actualloginPageURL = loginpage.getLoginPageURL();
		Assert.assertTrue(actualloginPageURL.contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}

	@Test(priority=3)
	public void forgetpasswordLinkExistTest() {
		Assert.assertTrue(loginpage.checkForgetPasswordLink(), AppError.ELEMENT_NOT_FOUND);
	}

	@Test(priority=4)
	public void loginTest() {

		String actualaccountPageTitle = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(actualaccountPageTitle, AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
}
