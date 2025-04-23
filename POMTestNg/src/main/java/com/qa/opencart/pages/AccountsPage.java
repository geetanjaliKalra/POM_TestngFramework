package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	private By logutlink= By.linkText("Logout");
	private By headers = By.cssSelector("div #content h2");
	private By searchField= By.name("search");
	private By searchBtn=By.xpath("//button[contains(@class,'btn-default btn-lg')]");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eutil = new ElementUtil(driver);
	}

	public String AccountsPageTitle() {
		
		String title=eutil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		return title;
	}
	
	public String getAccountsPageURL() {
		String accoutsPageUrl = eutil.waitForURLContains(AppConstants.ACCOUNT_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("Login page URL " + accoutsPageUrl);
		return accoutsPageUrl;
	}

	public boolean islogoutLinkDisplayed() {
		return eutil.doIsDisplayed(logutlink);
	}
	
	public List<String> getAccHeaders() {
		
		List<String> headersValList=new ArrayList<String>();
		headersValList= eutil.getElementsText(headers);
		return headersValList;
	}
	
	public boolean searchExist() {
		return eutil.doIsDisplayed(searchField);
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		//System.out.println("Searched item is "+ searchKey);
		if(searchExist()) {
			eutil.doSendKeys(searchField, searchKey, TimeUtil.DEFAULT_TIME);
			eutil.doClick(searchBtn);
			return new SearchResultsPage(driver);
		}
		else {
			System.out.println("Search field is not present");
			return null;
		}
		
	}
}
