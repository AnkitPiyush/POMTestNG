package pom.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pom.qa.hubspot.base.BasePage;
import pom.qa.hubspot.util.ElementUtil;

public class ContactsPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	
	By contactEmail = By.id("recipient-email");
	By contactName = By.id("recipient-name");
	By contactMessage = By.id("message-text");
	By contactSubmitMessage = By.xpath("//button[contains(text(),'Send message')]");
			
	public ContactsPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public HomePage createNewContact(String email, String name, String message) 
	{
		System.out.println("1st");
		elementUtil.waitForElementVisible(contactEmail);

		System.out.println("2nd");
		elementUtil.doSendKeys(contactEmail, email);
		elementUtil.doSendKeys(contactName, name);
		elementUtil.doSendKeys(contactMessage, message);
		elementUtil.doClick(contactSubmitMessage);
		elementUtil.doIsAlertExist();
		return new HomePage(driver);
	}
	
}
