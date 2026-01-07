package com.creatio.crm.framework.web.commons;

import java.security.KeyStore.Entry.Attribute;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.Flow.Publisher;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.utilities.PropUtil;

public class WebCommons {
	
	//Common methods using Selenium
	
	public WebDriver driver =BasePage.getDriver();
	public Properties prop = PropUtil.readData("Config.properties");

	//Launch the application and verify the title.
	public void launchApplication() {
		driver.get(prop.getProperty("APP_URL"));
		String expectedTitle = prop.getProperty("APP_TITLE");
		String actualTitle = driver.getTitle();
		if(!actualTitle.equals(expectedTitle)) {
			Assert.fail("Application title is not correct. Expected :" + expectedTitle + "Actual Title : " + actualTitle);
		}
	}
	
	//Common method to scroll the element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0],scrollInToView(true);", element);
	}
	
	//Common method to click element
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}
	
	//click a hidden element
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0],click();", element);
	}
	
	//method for double click
	public void doubleClick(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	//method for right click
	public void rightClick(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	//method for hover on an element
	public void mouseHover(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	//method to enter text in text field
	public void enterText(WebElement element, String text) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(text);
	}
	
	//select check box
	public void selectCheckbox(WebElement element, boolean status) {
		scrollToElement(element);
		if(element.isSelected()!=status) {
			element.click();
		}
		
	}
	
	//select from drop down
	public void selectDropdownOption(WebElement element,String option, String selectBy) {
		scrollToElement(element);
		Select select  = new Select(element);
		switch(selectBy.toLowerCase()) {
		case "value":
			select.selectByValue(option);
			break;
		case "visibletext":
			select.selectByVisibleText(option);
			break;
		case "index":
			select.deselectByIndex(Integer.parseInt(option));
			break;
		default:
			Assert.fail("Invalid selectBy option " + selectBy);
		}
		
	}
	
	// get text of an element
	public String getElementText(WebElement element) {
		scrollToElement(element);
		return element.getText();
	}
	
	//get attribute
	public String getElementAttribute(WebElement element, String attribute) {
		scrollToElement(element);
		return element.getAttribute(attribute);
	}
	
	//get current url
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	//refresh page
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	//verify the element display status
	public boolean isElementEnabled(WebElement element) {
		try {
			scrollToElement(element);
			return element.isEnabled();
		}catch(Exception e) {
			return false;
		}
	}
	
	//wait
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//wait for element
	public void waitForElement(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//
	
}
