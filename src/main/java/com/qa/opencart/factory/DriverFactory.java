package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionManager optionManager;

	public WebDriver initDriver(Properties prop) {
		optionManager = new OptionManager(prop);
		String browserName = prop.getProperty("browser").trim().toLowerCase();
		System.out.println("Browser Name is " + browserName);

		if (browserName.trim().equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(optionManager.getChromeOptions());
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(optionManager.getFirefoxOptions());
		}

		else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionManager.getEdgeOptions());
		}

		else {
			System.out.println("please provide valid browser Name");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;

	}

	// when Multiple env config is require

	public Properties initProp() throws FileNotFoundException {
		// mvn clean install -Denv="stage"

		String envName = System.getProperty("env");
		FileInputStream ip = null;
		System.out.println("Running test case on env " + envName);

		if (envName == null) {
			System.out.println("no env mentioned.. Running on QA env");
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}

		else {

			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
				break;
			default:
				System.out.println("Wrong env Name Passed");
				break;
			}
		}

		prop = new Properties();

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/*
	 * // when Only one env config is require 
	 * public Properties initProp() throws FileNotFoundException
	 *  { prop=new Properties(); FileInputStream ip = new
	 * FileInputStream("./src/test/resources/config/config.properties");
	 * 
	 * try { prop.load(ip); } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * return prop; }
	 */
	public static String getScreenshot() {

		DriverFactory obj = new DriverFactory();
		File srcFile = ((TakesScreenshot) obj.driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot" + System.currentTimeMillis() + ".png";
		File Destination = new File(path);
		try {
			FileHandler.copy(srcFile, Destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
