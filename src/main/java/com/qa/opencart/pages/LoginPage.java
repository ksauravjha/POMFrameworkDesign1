package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
		
	// Private By Locator
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By forgotPwdLink= By.linkText("Forgotten Password");
    private By registerLink=By.linkText("Register");
	
	// Page Constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	
	// page actions/methods
	public String getLoginPageTitle()
	{
		//String title = driver.getTitle();
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		return title;
	}
	
	public String getLoginPageURL()
	{
		String url =eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		return url;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForElementVisible(forgotPwdLink,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String un, String pwd) 
	{
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
}
