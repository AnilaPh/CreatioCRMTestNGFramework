package com.creatio.crm.framework.web.commons;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.utilities.PropUtil;

public class WebCommons {
	
	//Common methods
	
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
	
}
