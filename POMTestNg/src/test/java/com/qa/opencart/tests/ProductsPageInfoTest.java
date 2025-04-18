package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.pages.SearchResultsPage;

public class ProductsPageInfoTest extends BaseTest {

	@BeforeClass
	public void productPageSetup() {

		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getHeaderData() {

		return new Object[][] {
			{ "Samsung", "Samsung Galaxy Tab 10.1" }, 
			{ "Macbook", "MacBook Air" },
			{ "iMac", "iMac" }

		};
	}

	@Test(dataProvider = "getHeaderData")
	public void productHeaderTest(String searchItem, String products) {
		searchresultpage = accpage.doSearch(searchItem);
		productinfopage = searchresultpage.searchProduct(products);
		String actualproductHeader = productinfopage.getProductHeader();
		Assert.assertEquals(actualproductHeader.trim(), products.trim(), AppError.HEADER_NOT_MATCH);

	}

	@Test(dataProvider = "getHeaderData")
	public void addToCartBtnExistTest(String searchItem, String products) {
		searchresultpage = accpage.doSearch(searchItem);
		productinfopage = searchresultpage.searchProduct(products);
		Assert.assertTrue(productinfopage.checkAddToCartBtnExist(), AppError.ELEMENT_NOT_FOUND);
	}

	@DataProvider
	public Object[][] getImagesData() {
		return new Object[][] { { "Samsung", "Samsung Galaxy Tab 10.1", 7 }, { "Macbook", "MacBook Air", 4 },
				{ "iMac", "iMac", 3 } };
	}

	@Test(dataProvider = "getImagesData")
	public void imagesCountTest(String searchItem, String products, int imgCount) {
		searchresultpage = accpage.doSearch(searchItem);
		productinfopage = searchresultpage.searchProduct(products);
		Assert.assertEquals(productinfopage.getProductImagesCount(), imgCount, AppError.IMAGE_COUNT_MISMATCHED);
	}
	
	@DataProvider
	public Object[][] getAddToCartData() {
		return new Object[][] { 
			{ "Samsung", "Samsung Galaxy Tab 10.1", 1 },
			{ "Macbook", "MacBook Air", 4 },
			{ "iMac", "iMac", 2 } };
	}
	
	@Test(dataProvider = "getAddToCartData")
	public void addToCartTest(String searchItem, String products, int qty) {
		searchresultpage = accpage.doSearch(searchItem);
		productinfopage=searchresultpage.searchProduct(products);
		addtocartpage=productinfopage.addProductToCart(qty);
		String actualSuccessMSg=addtocartpage.getSuccessMsg();
		Assert.assertTrue(actualSuccessMSg.contains(products),AppError.PRODUCT_ADD_UNSUCCESSFUL);
	}
}