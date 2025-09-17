package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class AccountInfo extends BasePage {

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
		
		public void setFirstName(String firstName)
		{
			txtFirstName.sendKeys(firstName);
		}
		
		public void setLastName(String lastName)
		{
			txtLastName.sendKeys(lastName);
		}
		
		public void setPhonenumber(String usPhoneNum)
		{
			txtPhoneNumber.sendKeys(usPhoneNum);
		}
		
		public void setEmail(String email)
		{
			txtEmail.sendKeys(email);
		}
		
		public void clickDone()
		{
			btnDone.click();
		}
	
	
	
	
}
