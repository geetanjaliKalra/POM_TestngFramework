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
import com.qa.opencart.exceptions.FrameworkException;

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
		String url = prop.getProperty("url");
		System.out.println("Passed browser is " + browser);

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
	 * This method is used to intialise the properties file. Thsi returns properties
	 *
	 * @return
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("Running test on env: " + "Default");
		if (envName == null) {
			try {
				ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
					break;
				default:
					throw new FrameworkException("Please pass the right environment " + envName);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
			try {
				prop.load(ip);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		

		return prop;
	}
}
