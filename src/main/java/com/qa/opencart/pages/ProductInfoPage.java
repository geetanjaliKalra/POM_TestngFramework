package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eutil;
	private By productHeaderLocator= By.cssSelector("div#content h1");
	private By addToCartLocator=By.xpath("//button[text()='Add to Cart']");
	private By productImagesLocator=By.cssSelector("ul.thumbnails img");
	private By quantityLocator=By.cssSelector("input#input-quantity");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eutil=new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		
		String productHeaderText = eutil.getElement(productHeaderLocator).getText();
	//	System.out.println("Product header is,from productinfopage "+productHeaderText);
		return productHeaderText;
	}
	
	public boolean checkAddToCartBtnExist() {
		return eutil.isElementDisplayed(addToCartLocator);
	}
	
	public int getProductImagesCount() {
		int imagesCount = eutil.waitForElementsVisible(productImagesLocator, TimeUtil.DEFAULT_TIME).size();
		return imagesCount;
	}
	
	public AddToCartPage addProductToCart(int qty) {
		eutil.doSendKeys(quantityLocator, String.valueOf(qty), TimeUtil.DEFAULT_TIME);
		eutil.doClick(addToCartLocator);
		return new AddToCartPage(driver);
	}
}
