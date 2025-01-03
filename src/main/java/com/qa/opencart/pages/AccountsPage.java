package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink=By.linkText("Logout");
	private By accsHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By SearchIcon= By.cssSelector("#search button");
	
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle()
	{
		//String title= driver.getTitle();
		String title=eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Account page title is "+ title);
		return title;
	}
	
	public String getAccPageURL()
	{
		//String url= driver.getCurrentUrl();
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("Account page url is "+ url);
		return url;
	}
	
	public boolean isLogoutLinkExist()
	{
		// return driver.findElement(logoutLink).isDisplayed();
		return eleUtil.waitForElementVisible(logoutLink,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchExist()
	{
		//return driver.findElement(search).isDisplayed();
	    return eleUtil.waitForElementVisible(search,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountsPageHeadersList()
	{
		//List<WebElement> acctHeadersList= driver.findElements(accsHeaders);
	
		List<WebElement> acctHeadersList = eleUtil.waitForElementsVisible(accsHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		
		List<String> acctHeadersValList = new ArrayList<String>();
		
		for(WebElement e: acctHeadersList )
		{
			String text = e.getText();
			acctHeadersValList.add(text);
		}
		
		return acctHeadersValList;
	}
	
	public SearchPage performSearch(String searchKey)
	{
		if(isSearchExist())
		{
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(SearchIcon);
			return new SearchPage(driver);
		}
		else
		{
			System.out.println("Search field is not present");
		    return null;
		}
	}
	
	
	
	
	

}
