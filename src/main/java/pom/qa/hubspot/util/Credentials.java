package pom.qa.hubspot.util;

public class Credentials {
	
	String appUserName;
	String appPassword;
	
	public Credentials(String appUserName, String appPassword)
	{
		this.appUserName = appUserName;
		this.appPassword = appPassword;
	}
	
	public void setAppUserName(String appUserName)
	{
		this.appUserName = appUserName;
	}
	
	public String getAppUserName()
	{
		return appUserName;
	}
	
	public void setAppPassword(String appPassword)
	{
		this.appPassword = appPassword;
	}
	
	public String getAppPassword()
	{
		return appPassword;
	}
	
}
