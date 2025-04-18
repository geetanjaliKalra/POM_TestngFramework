package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class AccountsPageTest extends BaseTest {

	
	@BeforeClass
	public void accSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountsPageTitletest() {
		String actualloginPageTitle = accpage.AccountsPageTitle();
		Assert.assertEquals(actualloginPageTitle, AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Test
	public void accPageURLtest() {
		String actualloginPageURL = accpage.getAccountsPageURL();
		Assert.assertTrue(actualloginPageURL.contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}

	@Test
	public void logoutLinkexistTest() {
		Assert.assertTrue(accpage.islogoutLinkDisplayed(), AppError.ELEMENT_NOT_FOUND);
	}

	@Test
	public void accPageHeadersTest() {
		List<String> accPageeadersList = accpage.getAccHeaders();
		Assert.assertEquals(accPageeadersList, AppConstants.list, AppError.HEADERS_LIST_NOT_MATCHED);
	}

	@DataProvider
	public Object[][] getSearchData() {

		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2},
			{"geetu",0}
		};
	}

	@Test(dataProvider="getSearchData")
	public void searchTest(String searchkey, int count) {
		searchresultpage = accpage.doSearch(searchkey);
		int actualSearchCount = searchresultpage.getSearchResultsCount();
		Assert.assertEquals(actualSearchCount, count, AppError.SEARCH_COUNT_NOT_MATCHED);
	}
}
