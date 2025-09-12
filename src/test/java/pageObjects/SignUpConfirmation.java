package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class SignUpConfirmation extends BasePage {

	BaseClass bc=new BaseClass();
	public SignUpConfirmation(WebDriver driver) 
	{
		super(driver);
	}
	
	//ConfirmationInfo Locators
	@FindBy(xpath="//h3[normalize-space()='Welcome to Truly Free!']") WebElement ConfirmationTxt;
	@FindBy(xpath="//button[normalize-space()='Getting Started with Truly Free']") WebElement btnGettingStartedWithTF;
	@FindBy(xpath="") WebElement iconCross;
	
	public boolean isMyAccountCreated()
	{
		try {
			return (ConfirmationTxt.isDisplayed()&&btnGettingStartedWithTF.isDisplayed());
			}
		catch(Exception e)
			{
			return false;
			}
	}
	
	public void clickGettingStartedWithTF()
	{
		btnGettingStartedWithTF.click();
	}
	
	
	

}
