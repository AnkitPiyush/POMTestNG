package pom.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.qa.hubspot.base.BasePage;
import pom.qa.hubspot.page.HomePage;
import pom.qa.hubspot.page.LoginPage;
import pom.qa.hubspot.util.Credentials;
import pom.qa.hubspot.util.appConstants;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials creds;
	
	@BeforeTest
	public void setUP()
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
		creds = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void VerifyLoginPageTitle() 
	{
		String title = loginPage.getPageTitle();
		System.out.println("Login Page Title is " + title);
		Assert.assertEquals(title, appConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void VerifySignupLink()
	{
		Assert.assertTrue(loginPage.checkSignUpLink());		
	}
	
	
	@Test(priority = 3)
	public void loginTest() throws InterruptedException
	{
		homePage = loginPage.doLogin(creds);
		String loggedInUser = homePage.getWelcomeText();
		Assert.assertTrue(loggedInUser.contains(appConstants.HOME_PAGE_WELCOME));
	}
	
	
	@DataProvider
	public Object[][] getLoginInvalidData()
	{
		Object data[][] = {
							{"test123","test"},
							{"","test"},
							{"test",""}
						   };
		return data;
	}
	
	@Test(priority = 4, dataProvider = "getLoginInvalidData", enabled = false)
	public void invalidloginTest(String uname, String pwd) throws InterruptedException
	{
		creds.setAppUserName(uname);
		creds.setAppPassword(pwd);
		loginPage.doLogin(creds);
		
		boolean alertExist = loginPage.isAlert();
		Assert.assertEquals(alertExist,true);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
