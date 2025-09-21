package testCases;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class TC006VerifyStoreProductsBrokenLink {

	    public static void main(String[] args) throws InterruptedException {
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();

	        driver.get("https://trulyfree.com/store/Aion/680253dbe912a569ca4d0cc9");

	        // Use a Set to avoid duplicates
	        Set<String> productLinks = new LinkedHashSet<>();

	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        int lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).intValue();
	        int retries = 0;

	        while (true) {
	            // Find products visible so far
	            List<WebElement> products = driver.findElements(By.xpath("//div[@class='productui__bg']//a"));
	            for (WebElement p : products) {
	                String url = p.getAttribute("href");
	                if (url != null && !url.trim().isEmpty()) {
	                    productLinks.add(url);
	                }
	            }

	            // Scroll down
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	            Thread.sleep(2000); // wait for new products to load

	            int newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).intValue();

	            if (newHeight == lastHeight) {
	                retries++;
	                if (retries > 2) break; // reached end
	            } else {
	                retries = 0;
	                lastHeight = newHeight;
	            }
	        }

	        // Counters
	        int totalChecked = 0;
	        int totalLoaded = 0;
	        int totalErrors = 0;

	        System.out.println("Total unique product links found: " + productLinks.size());

	        for (String link : productLinks) {
	            totalChecked++;
	            System.out.println("Checking: " + link);

	            // 1. Validate using HTTP connection
	            if (!isLinkValid(link)) {
	                System.out.println("❌ Broken link (HTTP error): " + link);
	                totalErrors++;
	                continue;
	            }

	            // 2. Navigate to product page in browser
	            try {
	                driver.get(link);
	                Thread.sleep(2000);

	                // Example validation: Check product title is visible
	                WebElement title = driver.findElement(By.xpath("//p[@data-testid='product-name']"));
	                System.out.println("   ✅ Product page loaded: " + title.getText());
	                totalLoaded++;
	            } catch (Exception e) {
	                System.out.println("   ❌ Error loading product page: " + e.getMessage());
	                totalErrors++;
	            }
	        }

	        // Print final summary
	        System.out.println("=================================");
	        System.out.println("Total links checked       : " + totalChecked);
	        System.out.println("Total product pages loaded: " + totalLoaded);
	        System.out.println("Total errors              : " + totalErrors);
	        System.out.println("=================================");

	        driver.quit();
	    }

	    // Utility method to check if a link returns HTTP < 400
	    public static boolean isLinkValid(String linkUrl) {
	        try {
	            HttpURLConnection connection = (HttpURLConnection) new URL(linkUrl).openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            int code = connection.getResponseCode();
	            return code < 400;
	        } catch (Exception e) {
	            System.out.println("   ❌ Exception for link: " + linkUrl + " | " + e.getMessage());
	            return false;
	        }
	    }
	}
