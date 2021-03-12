package pom.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
	
	WebDriver driver;
	Properties prop;
	public OptionsManager optionsManager;
	
	public WebDriver init_driver(String browserName)
	{	
		optionsManager = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			
//			ChromeOptions co;
//			if(prop.getProperty("headless").equalsIgnoreCase("yes"))
//			{
//				co = new ChromeOptions();
//				co.addArguments("--headless");
//			}
//			
			driver = new ChromeDriver(optionsManager.getChromeOptions());
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.out.println("Set property for Firefox");
		}
		else
		{
			System.out.println("Browser name not found");
		}
		
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		System.out.println("Hello");
	//	driver.get(url);
		return driver;
	}

	public Properties init_properties()
	{
		prop = new Properties();
		String path = null;
		String env;
		
		try
		{
			env = System.getProperty("env");
			if(env.equalsIgnoreCase("QA"))
			{
				path = ".\\src\\main\\java\\pom\\qa\\hubspot\\config\\qa.config.properties";
			}
			else if(env.equalsIgnoreCase("STG"))
			{
				path = ".\\src\\main\\java\\pom\\qa\\hubspot\\config\\dev.config.properties";
			}	
			else
			{
				path = ".\\src\\main\\java\\pom\\qa\\hubspot\\config\\config.properties";
			}
		}
		catch(Exception e)
		{
			path = ".\\src\\main\\java\\pom\\qa\\hubspot\\config\\config.properties";
		}
		
		try
		{
			FileInputStream fip = new FileInputStream(path);
			prop.load(fip);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Config doesn't exist");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
}
