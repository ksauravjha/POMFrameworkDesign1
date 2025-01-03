package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void accPageTitleTest()
	{
		String ActualTitle = accPage.getAccPageTitle();
		Assert.assertEquals(ActualTitle, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		
	}
	
	@Test
	public void accPageURLTest()
	{
		String ActualURL = accPage.getAccPageURL();
		Assert.assertTrue(ActualURL.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
		
	}
	
	@Test
	public void isLogoutLinkExistTest()
	{
		 Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	@Test
	public void accPageHeadersTest()
	{
		
		List<String> actualAccPageHeaderList =  accPage.getAccountsPageHeadersList();
	
		System.out.println("actualAccPageHeaderList "+ actualAccPageHeaderList );
	    Assert.assertEquals(actualAccPageHeaderList.size(),AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	
	@Test
	public void accPageHeadersValueTest()
	{
		
		List<String> actualAccPageHeaderList =  accPage.getAccountsPageHeadersList();
	
		System.out.println("actualAccPageHeaderList "+ actualAccPageHeaderList );
	    Assert.assertEquals(actualAccPageHeaderList,AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
	}
	
	
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][]
				{
			{"Macbook"},{"imac"},{"Apple"},{"Samsung"}
				};
	}
	
	@Test(dataProvider="getProductData")
	public void searchProductCountTest(String searchKey)
	{
		searchPage=accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getTotalSearchProductCount()>0);
	}
	
	
	@DataProvider
	public Object[][] getProductTestData()
	{
		return new Object[][]
				{
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941 BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"},
				};
	}
	
	@Test(dataProvider="getProductTestData")
	public void searchProductTest(String searchkey, String productName)
	{
		searchPage=accPage.performSearch(searchkey);
		if(searchPage.getTotalSearchProductCount()>0)
		{
			productInfoPage=searchPage.selectProduct(productName);
		    String actProductHeader= productInfoPage.getProductHeaderValue();
		    Assert.assertEquals(actProductHeader, productName);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
