package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public static WebDriver driver;
    public Properties p;
    public Logger logger;

    @BeforeClass(groups={"Regression","Smoke","Sanity"})
    @Parameters({"os","browser"})
    public void setUp(String os, String br) throws IOException {
        logger = LogManager.getLogger(this.getClass());

        // Load config.properties
        FileReader file = new FileReader(System.getProperty("user.dir") +
                "\\src\\test\\resources\\config.properties");
        p = new Properties();
        p.load(file);

        System.out.println("Launching " + br + " Browser...");

        try {
            switch (br.toLowerCase()) {

                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    driver = new ChromeDriver(chromeOptions);
                    break;
                    
                case "edge":
                     System.setProperty("webdriver.edge.driver","C:\\Drivers\\msedgedriver.exe");
                     EdgeOptions edgeOptions = new EdgeOptions();
                     edgeOptions.addArguments("--disable-notifications");
                     driver = new EdgeDriver(edgeOptions);
                     break;

                /*case "edge":
                    try {
                        // Attempt WebDriverManager first
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--disable-notifications");
                        driver = new EdgeDriver(edgeOptions);
                    } catch (Exception e) {
                        // If WDM fails, use manual driver
                        System.out.println("WebDriverManager failed. Using manual EdgeDriver.");
                        System.setProperty("webdriver.edge.driver","C:\\Drivers\\msedgedriver.exe");
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--disable-notifications");
                        driver = new EdgeDriver(edgeOptions);
                    }
                    break; */

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser name: " + br);
            }

            // Delete cookies and set implicit wait
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Open app URL
            String appUrl = p.getProperty("appUrl");
            if (appUrl == null || appUrl.isEmpty()) {
                throw new RuntimeException("appUrl is missing in config.properties");
            }
            System.out.println("Navigating to: " + appUrl);
            driver.get(appUrl);
            driver.manage().window().maximize();

            // Handle unwanted alerts/popup
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.dismiss();
            } catch (Exception e) {
                System.out.println("No initial alert present.");
            }

        } catch (Exception e) {
            logger.error("Browser setup failed!", e);
            throw new RuntimeException("Failed to initialize browser: " + br, e);
        }
    }

    @AfterClass(groups={"Regression","Smoke","Sanity"})
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    // ---------------- Utility Methods ----------------
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumbers() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(6);
    }

    public String generatePlainUSPhone() {
        Random rand = new Random();
        int areaCode = rand.nextInt(800) + 200;
        int centralOffice = rand.nextInt(800) + 200;
        int lineNumber = rand.nextInt(9000) + 1000;
        return String.format("%03d%03d%04d", areaCode, centralOffice, lineNumber);
    }

    public String captureScreen(String tname) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String timeStamp = df.format(new Date());

        TakesScreenshot takescreenshot = (TakesScreenshot) driver;
        File source = takescreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" +
                tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        Files.copy(source.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return targetFilePath;
    }
}
