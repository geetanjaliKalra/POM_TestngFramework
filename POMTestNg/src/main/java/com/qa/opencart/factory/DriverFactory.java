package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;

	/**
	 * This method is used to initialise the browser based on the passed browser
	 * name
	 * 
	 * @param browsername
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		String browser = prop.getProperty("browser");
		String url=prop.getProperty("url");
		System.out.println("Passed browser is " + browser );

		switch (browser.toLowerCase().trim()) {

		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Please pass the right browser " + browser);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	/**
	 * This method is used to intialise the properties file.
	 * Thsi returns properties
	 * @return
	 */
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
