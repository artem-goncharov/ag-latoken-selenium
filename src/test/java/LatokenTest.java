import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;

import java.util.*;

public class LatokenTest {

    public static void main(String[] args) throws IOException {

        // Opening website in Firefox:
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        long startTimeMillis = System.currentTimeMillis();
        boolean readyStateComplete = false;

        System.out.println("Started loading the page: " + readyStateComplete + ". At: " + startTime);

        System.setProperty("webdriver.gecko.driver", "./browser_drivers/geckodriver.exe");
        WebDriver driverFirefox = new FirefoxDriver();
        driverFirefox.manage().window().maximize();
        driverFirefox.get("https://latoken.com/");

        // Checking when the page is fully loaded:
        while (!readyStateComplete) {
            JavascriptExecutor executor = (JavascriptExecutor) driverFirefox;
            //executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
            readyStateComplete = ((String) executor.executeScript("return document.readyState")).equals("complete");
        }

        if (readyStateComplete) {
            Timestamp endTime = new Timestamp(System.currentTimeMillis());
            System.out.println("The webpage is fully loaded: " + readyStateComplete + ". At: " + endTime);

            long endTimeMillis = System.currentTimeMillis();
            long totalTime = endTimeMillis - startTimeMillis;
            System.out.println("Total Page Load Time: " + totalTime + " milliseconds");
        }

        // Making screenshot of the page & waiting:
        Timestamp screenshotTimestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String targetString = "./browser_screenshots/" + sdf.format(screenshotTimestamp) + ".PNG";
        File targetFile = new File(targetString);

        File scrFile = ((TakesScreenshot)driverFirefox).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, targetFile);

        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        // Checking work of links in the main menu:
        // 01 exchange_link:
        WebElement exchangeLink = driverFirefox.findElement(By.id("exchange_link"));
        exchangeLink.click();

        // Waiting for some time before going back to main page:
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        driverFirefox.navigate().back();

        // 02 IEO Launchpad:
        try { Thread.sleep(2500); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        WebElement IEOLaunchpad = driverFirefox.findElement(By.id("IEO Launchpad"));
        IEOLaunchpad.click();

        // Waiting for some time before going back to main page:
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        driverFirefox.navigate().back();

        // 03 blog_link:
        try { Thread.sleep(2500); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        WebElement blogLink = driverFirefox.findElement(By.id("blog_link"));
        blogLink.click();

        // Waiting for some time before going back to main page:
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        driverFirefox.navigate().back();

        // 04 careers_link:
        try { Thread.sleep(2500); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        WebElement careersLink = driverFirefox.findElement(By.id("careers_link"));
        careersLink.click();

        // Waiting for some time before going back to main page:
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        driverFirefox.navigate().back();

        // 05 support_link:
        try { Thread.sleep(2500); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        WebElement supportLink = driverFirefox.findElement(By.id("support_link"));
        supportLink.click();

        // Waiting for some time before going back to main page:
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        driverFirefox.navigate().back();

        // 06 apply_ieo_listing_link:
        try { Thread.sleep(2500); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        WebElement applyIeoListingLink = driverFirefox.findElement(By.id("apply_ieo_listing_link"));
        applyIeoListingLink.click();

        // Waiting for some time before going back to main page:
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        driverFirefox.navigate().back();

        // Go to Featured stories:
        JavascriptExecutor jeStories = (JavascriptExecutor) driverFirefox;
        jeStories.executeScript("window.scrollBy(0, 560)");
        try { Thread.sleep(2500); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        List<WebElement> featuredStories = driverFirefox.findElements(By.xpath("//li[@class='Banners__event--kPuMS']/div/a"));
        System.out.println("Found following amount of featured stories: " + featuredStories.size());

        // Define an array where Featured story href will be added:
        ArrayList<String> featuredStoriesArray = new ArrayList<>();

        for (WebElement item : featuredStories) {
            String storyHref = item.getAttribute("href");
            featuredStoriesArray.add(storyHref);
        }

        System.out.println(featuredStoriesArray);

        for (int i = 0; i < featuredStoriesArray.size(); i++) {
            driverFirefox.get(featuredStoriesArray.get(i));
            try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
            driverFirefox.navigate().back();
            jeStories.executeScript("window.scrollBy(0, 560)");
            try { Thread.sleep(2500); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        }

        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        // Go to table with currencies:
        JavascriptExecutor jeCurrencies = (JavascriptExecutor) driverFirefox;
        WebElement tableCurrencies = driverFirefox.findElement(By.id("anchorTable"));
        jeCurrencies.executeScript("arguments[0].scrollIntoView(true);", tableCurrencies);
        jeCurrencies.executeScript("window.scrollBy(0, -170)");

        // Changing tabs with currencies:
        // 01 market_BTC:
        WebElement marketBTC = driverFirefox.findElement(By.id("market_BTC"));
        marketBTC.click();
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        // 02 market_ETH:
        WebElement marketETH = driverFirefox.findElement(By.id("market_ETH"));
        marketETH.click();
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        // 03 market_LA:
        WebElement marketLA = driverFirefox.findElement(By.id("market_LA"));
        marketLA.click();
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        // 04 market_USDT:
        WebElement marketUSDT = driverFirefox.findElement(By.id("market_USDT"));
        marketUSDT.click();
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        // 05 market_TRX:
        WebElement marketTRX = driverFirefox.findElement(By.id("market_TRX"));
        marketTRX.click();
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        // 06 market_TURBO:
        WebElement marketTURBO = driverFirefox.findElement(By.id("market_TURBO"));
        marketTURBO.click();
        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }

        try { Thread.sleep(5000); } catch (InterruptedException e) { System.out.println(e.getMessage()); }
        driverFirefox.quit();

        // End of test:
        System.out.println("----------------------------------------");
        System.out.println("|                                      |");
        System.out.println("|             END OF TEST              |");
        System.out.println("|                 ***                  |");
        System.out.println("|     prepared by: Artem Goncharov     |");
        System.out.println("|     performed: 30.11-01.12.2019      |");
        System.out.println("|     email: ag.developer@mail.ru      |");
        System.out.println("|     telephone: +7 977 724-51-25      |");
        System.out.println("|                                      |");
        System.out.println("----------------------------------------");

    }
}
