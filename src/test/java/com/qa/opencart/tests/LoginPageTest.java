package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitleTest()
	{
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void loginPageURLTest()
	{
		String actualURL= loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void forgotPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		
	}
	
	@Test
	public void loginTest()
	{
	   accPage =  loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	   Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
