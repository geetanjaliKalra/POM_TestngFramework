package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;



public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
	}

	/*
	 * Method to find the element based on locator
	 */
	public WebElement getElement(By locator) {

		try {
			WebElement element = driver.findElement(locator);
			return element;
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page " + locator);
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * Method to find the element based on locator and do the action by sending the
	 * value
	 */
	public void doSendKeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).sendKeys(value);
	}

	/*
	 * method to check the null values
	 */
	public void nullCheck(String value) {
		if (value == null) {
			throw new ElementException("Value is null");
		}

	}

	/*
	 * method to perform click on a webelement
	 */
	public void doClick(By locator) {
		getElement(locator).click();

	}

	/*
	 * method to find the value of an attribute of the element like src,value
	 */
	public String doGetAttribute(By locator, String Attribute) {
		return getElement(locator).getAttribute(Attribute);
	}

	/**
	 * Method to find the attributes of multiple elements
	 * 
	 * @param locator
	 * @param Attribute
	 */
	public void doGetElementsAttribute(By locator, String Attribute) {

		List<WebElement> elements = getElements(locator);
		for (WebElement e : elements) {
			String attrValue = e.getAttribute(Attribute);
			if (attrValue != null && attrValue.length() != 0)
				System.out.println(attrValue);
		}
	}

	/**
	 * Method to find the Elements
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	/**
	 * Method to find the Elements count
	 * 
	 * @param locator
	 * @return
	 */
	public int getElementCount(By locator) {

		return getElements(locator).size();
	}

	/**
	 * Method to find the Elements text
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getElementsText(By locator) {

		List<WebElement> elements = getElements(locator);
		List<String> elementText = new ArrayList<String>();

		for (WebElement e : elements) {
			String text = e.getText();
			if (text.length() != 0) {
				elementText.add(text);
			}
		}
		return elementText;
	}

	// *****************Select class methods*************
	/**
	 * MEthod to select value in dropdown using visible values
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectByVisibleText(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	/**
	 * MEthod to select value in dropdown using values
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectByValue(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	/**
	 * MEthod to select value in dropdown by index
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectByIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * method to select all the options of dropdown
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getAllOptions(By locator) {

		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : options) {
			String optionValue = e.getText();
			optionsTextList.add(optionValue);
		}
		return optionsTextList;

	}

	/**
	 * Selecting a value from dropdown
	 * 
	 * @param locator
	 * @param Value
	 */
	public void selectValueFromDropdown(By locator, String Value) {
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		for (WebElement e : options) {
			String optionValue = e.getText();
			if (optionValue.equalsIgnoreCase(Value)) {
				e.click();
				break;
			}
		}

	}

	/**
	 * Selecting the value from dropdown without using select class
	 * 
	 * @param locator
	 * @param Value
	 */
	public void selectValueFromDropdownWithoutSelect(By locator, String Value) {

		List<WebElement> options = getElements(locator);
		for (WebElement e : options) {
			String optionValue = e.getText();
			if (optionValue.equalsIgnoreCase(Value)) {
				e.click();
				break;
			}
		}

	}

	public void doSearch(By locator, String value) {

		List<WebElement> elements = getElements(locator);
		System.out.println("List size is " + elements.size());
		for (WebElement options : elements) {
			String optionsValue = options.getText();
			System.out.println(optionsValue);
			if (optionsValue.contains(value)) {
				options.click();
				break;
			}

		}
	}

	public boolean doIsDisplayed(By locator) {
		try {
			boolean flag = getElement(locator).isDisplayed();
			System.out.println("Element is displayed ");
			return flag;
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed " + locator);
			return false;
		}
	}

	public boolean isElementDisplayed(By locator) {

		int elementsSize = driver.findElements(locator).size();

		if (elementsSize == 1) {
			System.out.println("Element is present on the page");
			return true;
		} else
			System.out.println("Multiple or 0 elements are present on the page");
		return false;
	}

	public boolean isElementDisplayed(By locator, int elementCount) {

		int elementsSize = driver.findElements(locator).size();

		if (elementsSize == elementCount) {
			System.out.println("Expected number " + elementCount + " of elements are present on the page");
			return true;
		} else
			System.out.println("Expected number of elements are not present on the page No of elements displayed are "
					+ elementsSize);
		return false;
	}

	public boolean isElementnabled(By locator) {

		if (getElement(locator).isEnabled()) {
			System.out.println(" elements is enabled");
			return true;
		} else
			System.out.println("elements is not enabled");
		return false;
	}

/////////********Alert***************

	public Alert getAlert() {

		return driver.switchTo().alert();
	}

	
	/****Wait for Alert
	 * 
	 * @param locator
	 * @return
	 */
	public Alert getAlert(int timeout) {

		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText() {

		Alert alert = getAlert();
		String text = alert.getText();
		alert.accept();
		System.out.println("Text on alert is " + text);
		return text;
	}
	
	public String getAlertText(int timeout) {
		
		Alert alert = getAlert(timeout);
		return alert.getText();
	}
	
	public void acceptAlert(int timeout) {

		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.alertIsPresent()).accept();
	}
	
	public void dismissAlert(int timeout) {

		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.alertIsPresent()).dismiss();
	}
	
	public void alertSendKeys(String value) {
		
		driver.switchTo().alert().sendKeys(value);
	}
	
public void alertSendKeys(String value,int timeout) {
		
		getAlert(timeout).sendKeys(value);
	}

//********Actions***************//////////

	public void doMoveToElement(By sourceElement, By targetElement) {

		Actions act = new Actions(driver);

		act.moveToElement(getElement(sourceElement)).perform();
		doClick(targetElement);
	}

//**************Waits*************/////////
	/**
	 * 
	 * @param locator
	 * @param timeout An expectation for checking that an element is present on the
	 *                DOM of a page. This does not necessarily mean that the element
	 *                is visible.
	 * @return
	 */
	public WebElement waitForElementPresent(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * 
	 * 
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible.Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	/**
	 * doSendKeys with a wait
	 * @param locator
	 * @param value
	 * @param timeout
	 */
	
	public void doSendKeys(By locator, String value,int timeout) {
		nullCheck(value);
		waitForElementVisible(locator,timeout).sendKeys(value);
	}
/**
 * click with a wait
 * @param locator
 * @param timeout
 */
	public void doClick(By locator,int timeout) {
		waitForElementVisible(locator,timeout).click();

	}
	
	
	public void clickWhenReady(By locator,int timeout) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public String waitForTitleConatins(String titlefraction,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.titleContains(titlefraction)))
				return driver.getTitle();
		}
		catch(TimeoutException e) {
			System.out.println("Title not found");
		}
		return driver.getTitle();
	}
	
	public String waitForTitleToBe(String title,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.titleIs(title)))
				return driver.getTitle();
		}
		catch(TimeoutException e) {
			System.out.println("Title not found");
		}
		return driver.getTitle();
	}
	
	public String waitForURLToBe(String URL,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.urlToBe(URL)))
				return driver.getTitle();
		}
		catch(TimeoutException e) {
			System.out.println("Title not found");
		}
		return driver.getTitle();
	}
	
	public String waitForURLContains(String URLFraction,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.urlContains(URLFraction)))
				return driver.getTitle();
		}
		catch(TimeoutException e) {
			System.out.println("Title not found");
		}
		return driver.getTitle();
	}
}
