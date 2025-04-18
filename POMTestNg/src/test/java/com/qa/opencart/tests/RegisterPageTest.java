package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.StringUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registerpage = loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserRegistrationData(){
	
		return new Object[][] {
			{"user","Auto1","123456789","user@123","No"},
			{"user","Auto2","123456789","user@123","Yes"},
			{"user","Auto3","123456789","user@123","No"},
			
		};
	}
	
	@Test(dataProvider="getUserRegistrationData")
	public void userRegistrationTest(String firstname,String lastname,String phone,String password,String subscribeOption) {
		Assert.assertTrue(
		registerpage.doRegister(firstname, lastname, 
				StringUtil.getRandomEmailId(), phone, password, subscribeOption),AppError.USER_REGISTRATION_NOT_SUCCESSFULL);
		
	}
}
