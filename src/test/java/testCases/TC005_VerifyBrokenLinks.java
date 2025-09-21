package testCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TC005_VerifyBrokenLinks {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Counters
        int totalBrands = 0;
        int totalLoaded = 0;
        int totalErrors = 0;

        try {
            driver.get("https://trulyfree.com/");

            // Hover on Mega Menu
            WebElement megaMenu = driver.findElement(By.xpath(
                "//li[contains(@class,'megaMenuContainer') and contains(@class,'megamenu__title')]"
            ));
            Actions actions = new Actions(driver);
            actions.moveToElement(megaMenu).perform();
            Thread.sleep(2000);

            // Collect brand hrefs
            List<WebElement> brandElements = driver.findElements(By.xpath("//ul[contains(@class,'has-multi')]//a"));
            List<String> brandLinks = new ArrayList<>();
            for (WebElement el : brandElements) {
                String url = el.getAttribute("href");
                if (url != null && !url.isEmpty()) {
                    brandLinks.add(url);
                }
            }

            totalBrands = brandLinks.size();
            System.out.println("🔗 Total brand links present: " + totalBrands);

            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (String brandUrl : brandLinks) {
                System.out.println("\nChecking brand: " + brandUrl);

                // Validate brand link
                if (isLinkValid(brandUrl)) {
                    System.out.println("✅ Brand link is valid");

                    try {
                        driver.get(brandUrl);
                        Thread.sleep(2000);

                        // Infinite scroll to load all products
                        int lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).intValue();
                        int retries = 0;

                        while (true) {
                            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                            Thread.sleep(2000); // wait for new products to load

                            int newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).intValue();
                            if (newHeight == lastHeight) {
                                retries++;
                                if (retries > 2) break; // stop after 2 tries with no new content
                            } else {
                                retries = 0;
                                lastHeight = newHeight;
                            }
                        }

                        // Count all products after scroll
                        List<WebElement> products = driver.findElements(By.xpath("//div[@class='productui__bg']//a"));
                        System.out.println("   🛒 Total products found: " + products.size());

                        totalLoaded++;
                    } catch (Exception e) {
                        System.out.println("❌ Error loading brand page: " + e.getMessage());
                        totalErrors++;
                    }
                } else {
                    System.out.println("❌ Brand link is broken (HTTP error)");
                    totalErrors++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Final summary
            System.out.println("\n================ SUMMARY ================");
            System.out.println("Total brand links present : " + totalBrands);
            System.out.println("Total brand links loaded  : " + totalLoaded);
            System.out.println("Total brand links failed  : " + totalErrors);
            System.out.println("=========================================");
            driver.quit();
        }
    }

    // Method to check broken links
    public static boolean isLinkValid(String linkUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(linkUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            return code < 400;
        } catch (Exception e) {
            System.out.println("❌ Error checking " + linkUrl + " : " + e.getMessage());
            return false;
        }
    }
}
