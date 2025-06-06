package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil eutil;
	private By searchResults=By.xpath("//div[@class='caption']//a");
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eutil=new ElementUtil(driver);
	}

	
	public int getSearchResultsCount() {
	  List<WebElement> searchElements=eutil.waitForElementsVisible(searchResults,TimeUtil.DEFAULT_MEDIUM_TIME);
	  return searchElements.size();
	}
	
	public ProductInfoPage searchProduct(String productname) {
		By productlink= By.linkText(productname);
		eutil.doClick(productlink, TimeUtil.DEFAULT_TIME);
		return new ProductInfoPage(driver);
		
	}
}
