package testCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TC007_BrandAndProductLinkChecker {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Brand counters
        int totalBrands=0;
        int validBrands=0;
        int failedBrands=0;

        // Product counters
        int totalProducts=0;
        int validProducts=0;
        int failedProducts=0;

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
            System.out.println("🔗 Total brand links present: "+totalBrands);

            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (String brandUrl:brandLinks) {
                System.out.println("\n==============================");
                System.out.println("Checking brand: "+brandUrl);

                if (isLinkValid(brandUrl)) {
                    System.out.println("✅ Brand link is valid");
                    validBrands++;

                    try {
                        driver.get(brandUrl);
                        Thread.sleep(2000);

                        // Infinite scroll to load all products
                        int lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).intValue();
                        int retries = 0;
                        while (true) {
                            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                            Thread.sleep(2000);
                            int newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).intValue();
                            if (newHeight == lastHeight) {
                                retries++;
                                if (retries > 2) break;
                            } else {
                                retries = 0;
                                lastHeight = newHeight;
                            }
                        }

                        // Collect unique product links
                        List<WebElement> productElements = driver.findElements(By.xpath("//div[@class='productui__bg']//a"));
                        Set<String> productLinks = new LinkedHashSet<>();
                        for (WebElement p : productElements) {
                            String url = p.getAttribute("href");
                            if (url != null && !url.isEmpty()) {
                                productLinks.add(url);
                            }
                        }

                        System.out.println("🛒 Total products found: "+productLinks.size());
                        totalProducts += productLinks.size();

                        // Check each product link
                        for (String productUrl : productLinks) {
                            if (isLinkValid(productUrl)) {
                                System.out.println("   ✅ Product OK: "+productUrl);
                                validProducts++;
                            } else {
                                System.out.println("   ❌ Broken Product: "+productUrl);
                                failedProducts++;
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("❌ Error loading brand page: "+e.getMessage());
                        failedBrands++;
                    }

                } else {
                    System.out.println("❌ Brand link is broken (HTTP error)");
                    failedBrands++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Final summary
            System.out.println("\n================ FINAL SUMMARY ================");
            System.out.println("Total brand links present   : "+totalBrands);
            System.out.println("Valid brand pages           : "+validBrands);
            System.out.println("Failed brand pages          : "+failedBrands);
            System.out.println("------------------------------------------------");
            System.out.println("Total products checked      : "+totalProducts);
            System.out.println("Valid product pages         : "+validProducts);
            System.out.println("Broken product pages        : "+failedProducts);
            System.out.println("================================================");
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
            System.out.println("❌ Error checking "+linkUrl+" : "+e.getMessage());
            return false;
        }
    }
}
