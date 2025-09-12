package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class VerificationCode extends BasePage{
	
	
	public VerificationCode(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[contains(@aria-label,'Please enter verification code. Digit 1')]") WebElement txtOTP;
	
	
	public void setOTP()
	{
		txtOTP.sendKeys("1111");
	}

}
