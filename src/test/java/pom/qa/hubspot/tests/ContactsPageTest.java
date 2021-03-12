package pom.qa.hubspot.tests;

import java.util.Properties;

import org.apache.bcel.classfile.Constant;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.qa.hubspot.base.BasePage;
import pom.qa.hubspot.page.ContactsPage;
import pom.qa.hubspot.page.HomePage;
import pom.qa.hubspot.page.LoginPage;
import pom.qa.hubspot.util.Credentials;
import pom.qa.hubspot.util.ExcelUtil;
import pom.qa.hubspot.util.appConstants;

public class ContactsPageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	Credentials creds;
	
	@BeforeTest
	public void setUP()
	{
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		
		String url = prop.getProperty("url");
		driver.get(url);
		loginPage = new LoginPage(driver);
	//	contactsPage.createNewContact("test", "test1", "test2");

	}	
	
	@DataProvider
	public Object[][] getContactsTestData()
	{
		Object data[][] = {
				{"test@test.com","Not Excel 1","Here is my first test"},
				{"test2@test.com","Not Excel 1","Here is my first test"},
				{"test3@test.com","Not Excel 1","Here is my first test"},
				{"test4@test.com","Not Excel 1","Here is my first test"},
			   };
				
		//ExcelUtil.getTestData(appConstants.TESTDATA_SHEET_NAME);
			
		for(int r=0; r<data.length;r++)
		{
			for(int c=0; c< data[0].length;c++)
			{
				System.out.print(data[r][c]);
			}
			System.out.println("");
		}
		
		return data;
	}
	
	@Test(priority = 1, dataProvider = "getContactsTestData")
	public void createContactTest(String contactEmail, String contactName, String contactMessage) 
	{
		System.out.println("In Contact");
		contactsPage = loginPage.clickOnContacts();
		contactsPage.createNewContact(contactEmail, contactName, contactMessage);
	}
	
	@AfterTest///
	public void tearDown()
	{
		driver.quit();
	}

}
