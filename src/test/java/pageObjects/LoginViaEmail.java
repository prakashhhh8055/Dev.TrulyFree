package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginViaEmail extends BasePage{

	public LoginViaEmail(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='standard-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='standard-password']") WebElement txtPassword;
	@FindBy(xpath="//button[normalize-space()='CONTINUE']") WebElement btnContinue;
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}

	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
}
