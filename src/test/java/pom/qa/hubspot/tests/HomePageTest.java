package pom.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.qa.hubspot.base.BasePage;
import pom.qa.hubspot.page.HomePage;
import pom.qa.hubspot.page.LoginPage;
import pom.qa.hubspot.util.Credentials;
import pom.qa.hubspot.util.appConstants;

public class HomePageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials creds;
	
	@BeforeTest
	public void setUP() throws InterruptedException
	{
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name is " + browserName);
		driver = basePage.init_driver(browserName);
		
		String url = prop.getProperty("url");
		System.out.println("URL is " + url);
		driver.get(url);
		loginPage = new LoginPage(driver);
		creds = new Credentials(prop.getProperty("username"),prop.getProperty("password"));
		homePage = loginPage.doLogin(creds);
									
		Thread.sleep(10000);
	}

	@Test
	public void getUserName()
	{
		String loggedInUser = homePage.getWelcomeText();
		System.out.println(loggedInUser);
		Assert.assertTrue(loggedInUser.contains(appConstants.HOME_PAGE_WELCOME));
	}
	

	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
