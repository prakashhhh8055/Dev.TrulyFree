package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//div[@data-testid=\"login-account\"]//span") WebElement linkLogin;
	@FindBy(xpath="//button[@id='onetrust-reject-all-handler']") WebElement btnReject;

	public void clickLogin()
	{
		linkLogin.click();
	}
	
	public void clickReject()
	{
		btnReject.click();
	}
	
	public void handlePopupIfPresent() 
	{
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        WebElement closeBtn = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".popup-close, .cookie-accept"))
	        );
	        closeBtn.click();
	    } catch (TimeoutException e) {
	        System.out.println("No popup appeared.");
	    }
	
	}
	
	

}
