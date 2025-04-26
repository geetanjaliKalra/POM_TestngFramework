package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Automation testing of Open cart app")
@Story("Login page test")
public class LoginPageTest extends BaseTest {
	
	@Description("===Login page title test===")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority=1)
	public void loginPageTitletest() {
		String actualloginPageTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actualloginPageTitle,AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Description("===Login page URL test===")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void loginPageURLtest() {
		String actualloginPageURL = loginpage.getLoginPageURL();
		Assert.assertTrue(actualloginPageURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}

	@Description("===Check for forget password link existence===")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgetpasswordLinkExistTest() {
		Assert.assertTrue(loginpage.checkForgetPasswordLink(), AppError.ELEMENT_NOT_FOUND);
	}

	@Description("===Login test===")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void loginTest() {

		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.AccountsPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	
	}
}
