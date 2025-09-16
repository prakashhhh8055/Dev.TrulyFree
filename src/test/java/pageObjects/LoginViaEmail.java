package pageObjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class LoginViaEmail extends BasePage{

	BaseClass bc=new BaseClass();
	private Properties p;   // keep a reference
	public LoginViaEmail(WebDriver driver,Properties p) 
	{
		super(driver);
		this.p=p;
	}
	
	@FindBy(xpath="//input[@id='standard-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='standard-password']") WebElement txtPassword;
	@FindBy(xpath="//button[normalize-space()='CONTINUE']") WebElement btnContinue;
	
	public void setEmail()
	{
		txtEmail.sendKeys(p.getProperty("email"));
	}

	public void setPassword()
	{
		txtPassword.sendKeys(p.getProperty("password"));
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
}
