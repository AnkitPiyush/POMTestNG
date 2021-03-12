package pom.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pom.qa.hubspot.base.BasePage;
import pom.qa.hubspot.util.Credentials;
import pom.qa.hubspot.util.ElementUtil;
import pom.qa.hubspot.util.appConstants;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	// Locators
	
	By contactButton = By.linkText("Contact");
	
	By login = By.xpath("//*[@id=\"login2\"]");
	By username = By.xpath("//*[@id=\"loginusername\"]");
	By password = By.xpath("//*[@id=\"loginpassword\"]");
	By loginBtn = By.xpath("//button[contains(text(),'Log in')]");
	By signUp = By.xpath("//*[@id=\"signin2\"]");
	ElementUtil elementUtil;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getPageTitle()
	{
		elementUtil.waitForTitlePresent(appConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetTitle();
				
	}
	
	public Boolean checkSignUpLink()
	{
		return elementUtil.doIsDisplayed(signUp);
	}
	
	public HomePage doLogin(Credentials cred) throws InterruptedException
	{
		
		elementUtil.doClick(login);
		Thread.sleep(2000);
		elementUtil.doSendKeys(username, cred.getAppUserName());
		elementUtil.doSendKeys(password, cred.getAppPassword());
		elementUtil.doClick(loginBtn);
		
		return new HomePage(driver);
		
	}
	
	public boolean isAlert()
	{
		return elementUtil.doIsAlertExist();		
	}
	
	public ContactsPage clickOnContacts()
	{
		elementUtil.waitForElementVisible(contactButton);
		elementUtil.doClick(contactButton);
		return new ContactsPage(driver);
	}
}
