package pom.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pom.qa.hubspot.base.BasePage;
import pom.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	By welcomeText = By.xpath("//*[contains(text(),'Welcome')]");
	By linkHome = By.linkText("Log out");
	ElementUtil elementUtil;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getWelcomeText()
	{
		return elementUtil.doGetText(welcomeText);
	}
	
	public Boolean getHomeLink()
	{
		return elementUtil.doIsDisplayed(linkHome);
	}
	
}


