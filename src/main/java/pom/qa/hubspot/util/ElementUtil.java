package pom.qa.hubspot.util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	WebDriverWait wait;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}

	public Boolean waitForTitlePresent(String title)
	{
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}

	public Boolean waitForElementVisible(By locator)
	{
	//	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	public String doGetTitle()
	{
		return driver.getTitle();
	}
	
	/**
	 * 
	 * Create WebElement on the basis of By Locator
	 * @return WebElement element
	 */
	public WebElement getElement(By locator)
	{	
		WebElement element = null;
		try
		{
			if(waitForElementVisible(locator));
				element = driver.findElement(locator);
		}
		catch(Exception e)
		{
			System.out.println("Error occurred while creating WebElement");
		}
			
		return element;
	}
	
	public void doClick(By locator)
	{
		try
		{
			getElement(locator).click();
		}
		catch(Exception e)
		{
			System.out.println("Issue with clicking on a button");
		}
	}
	
	public void doSendKeys(By locator, String value)
	{
		try
		{
			WebElement textBox = getElement(locator);
			textBox.clear();
			textBox.sendKeys(value);
		}
		catch(Exception e)
		{
			System.out.println("Issue with Entering value in text box");
		}
	}

	public Boolean doIsDisplayed(By locator)
	{
		try
		{
			return getElement(locator).isDisplayed();
	
		}
		catch(Exception e)
		{
			System.out.println("Exception occurred in IsDisplayed");
		}
		return false;
	}

	public String doGetText(By locator)
	{
		try
		{
			return getElement(locator).getText();
	
		}
		catch(Exception e)
		{
			System.out.println("Exception occurred in getText");
		}
		return null;
	}
	
	public Boolean doIsAlertExist()
	{
		try
		{
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
}