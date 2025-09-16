package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

public static WebDriver driver;
public Properties p;
public Logger logger;

	@BeforeClass
	public void setUp() throws IOException
	{
		logger=LogManager.getLogger(this.getClass());
		
		FileReader file=new FileReader("C:\\Prakash_SeleniumWebDriver\\Dev.TrulyFree\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2); // block notifications
        chromeOptions.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(chromeOptions);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appUrl"));
		driver.manage().window().maximize();
		
		
		// Handle unwanted alerts/popup
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        alert.dismiss();
	    	} 
	    catch (Exception e) 
	    	{
	        System.out.println("No initial alert present.");
	    	}
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;	
	}
	
	public String randomNumbers()
	{
		String generatedNumbers=RandomStringUtils.randomNumeric(10);
		return generatedNumbers;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumbers=RandomStringUtils.randomNumeric(6);
		return (generatedString+"@"+generatedNumbers);
	}
	
	 public String generateUSPhoneNumber() {
	        Random rand = new Random();
	        int areaCode = rand.nextInt(800) + 200; // ensures 200–999
	        int centralOffice = rand.nextInt(800) + 200;
	        int lineNumber = rand.nextInt(10000); 
	        
	        return String.format("%03d-%03d-%04d", areaCode, centralOffice, lineNumber);
	    }
	
	public String captureScreen(String tname)
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
		Date dd=new Date();
		String timeStamp=df.format(dd);
		
		TakesScreenshot takescreenshots=(TakesScreenshot) driver;
		File Source=takescreenshots.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("User.dir")+"\\screenshots\\" + tname + "_" + timeStamp+ ".png";
		File targetFile=new File(targetFilePath);
		Source.renameTo(targetFile);
		
		return targetFilePath;
		
	}
	

}
