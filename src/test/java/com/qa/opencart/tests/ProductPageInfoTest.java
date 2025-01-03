package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }, { "Samsung", "Samsung SyncMaster 941 BW", 1 } };
	}

	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImageCount, imagesCount);
	}
	
	
	@Test
	public void productInfoTest()
	{
		searchPage=accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
	    Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
	    System.out.println(actProductInfoMap);  
	    //Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
	    // Soft Assertion
	    softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
	    softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
	    softAssert.assertAll();
	}
	
	@Test
	public void addToCartTest()
	{
		searchPage= accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String actCartMessage = productInfoPage.addProductToCart();
		softAssert.assertTrue(actCartMessage.contains("Success"));
		softAssert.assertTrue(actCartMessage.contains("MacBook Pro"));
		softAssert.assertAll();
	}

}
