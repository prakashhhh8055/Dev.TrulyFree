package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class SignupViaPhoneNumber extends BasePage{

	public SignupViaPhoneNumber(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath="//span[normalize-space()='Email']") WebElement linkEmail;
	@FindBy(xpath="//input[@id='regPhoneInput']") WebElement txtPhoneNum;
	
	 public void setPhoneNum(String phone) throws InterruptedException 
	{
	    txtPhoneNum.clear();
	    for(char digit:phone.toCharArray()) 
	    {
	        txtPhoneNum.sendKeys(Character.toString(digit));
	        Thread.sleep(100); // small delay so mask catches it
	    }
	    System.out.println("Phone entered: "+phone+" | Length: "+phone.length());
	} 

	
	public void clickEmail()
	{
		linkEmail.click();
	}
	
}
