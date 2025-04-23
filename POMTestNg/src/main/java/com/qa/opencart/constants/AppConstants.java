package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AppConstants {
	public static final String CONFIG_FILE_PATH="./src/test/resources/config/config_DEFAULT.properties";
	public static final String CONFIG_QA_FILE_PATH="./src/test/resources/config/config_QA.properties";
	public static final String CONFIG_DEV_FILE_PATH="./src/test/resources/config/config_DEV.properties";
	public static final String CONFIG_STAGE_FILE_PATH="./src/test/resources/config/config_STAGE.properties";
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL="route=account/login";
	public static final String ACCOUNT_PAGE_FRACTION_URL="route=account/account";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final Collection<?> list = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String SuccessMSG = "Your Account Has Been Created!" ;;
	
	//*********Excel Sheet Details******8
	
	public static final String TEST_DATA_FILE_PATH="./src/test/resources/testdata/TestData.xlsx";
	public static final String REGISTER_TEST_DATA_SHEETNAME="register";
	


}
