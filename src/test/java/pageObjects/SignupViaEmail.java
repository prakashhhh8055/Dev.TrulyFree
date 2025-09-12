package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class SignupViaEmail extends BasePage {

	BaseClass bc=new BaseClass();
	public SignupViaEmail(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//input[@id='standard-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='standard-password']") WebElement txtPassword;
	@FindBy(xpath="//label[@class=\"style-module_checkBoxUi__9vrrE\"]") WebElement checkboxTCAndPrivacy;
	@FindBy(xpath="//button[normalize-space()='CONTINUE']") WebElement btnContinue;
	
	
	public void setEmail()
	{
		txtEmail.sendKeys(bc.randomString()+"@gmail.com");
	}
	
	public void setPassword()
	{
		txtPassword.sendKeys(bc.randomAlphaNumeric());
	}
	
	public void selectTCAndPrivacy()
	{
		checkboxTCAndPrivacy.click();
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
		
	
}
