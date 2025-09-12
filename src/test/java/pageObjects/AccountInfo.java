package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class AccountInfo extends BasePage {

		BaseClass bc=new BaseClass();
		public AccountInfo(WebDriver driver) 
		{
			super(driver);
		}

		//Account Information
		@FindBy(xpath="//input[@placeholder=' Enter first name']") WebElement txtFirstName;
		@FindBy(xpath="//input[@placeholder=' Enter last name']") WebElement txtLastName;
		@FindBy(xpath="//input[@id='signuplast']") WebElement txtPhoneNumber;
		@FindBy(xpath="//input[@placeholder='Enter best email']") WebElement txtEmail;
		@FindBy(xpath="//button[normalize-space()='DONE']") WebElement btnDone;
		
		public void setFirstName()
		{
			txtFirstName.sendKeys(bc.randomString());
		}
		
		public void setLastName()
		{
			txtLastName.sendKeys(bc.randomString());
		}
		
		public void setPhonenumber()
		{
			txtPhoneNumber.sendKeys(bc.generateUSPhoneNumber());
		}
		
		public void setEmail()
		{
			txtEmail.sendKeys(bc.randomString()+"@gmail.com");
		}
		
		public void clickDone()
		{
			btnDone.click();
		}
	
	
	
	
}
