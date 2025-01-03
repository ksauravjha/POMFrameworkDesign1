package com.qa.opencart.base;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop; // We are making prop protected so that we can use them in child class
	// All the references we are going to store in Base Test Class
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;
	
	@BeforeTest
	public void setup() throws FileNotFoundException
	{
		df=new DriverFactory();
		prop=df.initProp();
		driver=df.initDriver(prop); // this will return driver
	    loginPage= new LoginPage(driver);
	    softAssert=new SoftAssert();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
