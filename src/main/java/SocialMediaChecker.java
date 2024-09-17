import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialMediaChecker {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SocialMediaChecker <company_name>");
            System.exit(1);
        }

        String companyName = args[0];
        WebDriver driver = setupDriver();

        try {
            String linkedinUrl = "https://www.linkedin.com/company/" + companyName + "/posts/";
            System.out.println("Checking LinkedIn URL: " + linkedinUrl);
            if (checkLinkedInActivity(driver, linkedinUrl)) {
                System.out.println("Company is active on LinkedIn.");
            } else {
                System.out.println("Company has a LinkedIn profile but is not active.");
            }

            // Use Google search for Twitter URL as before
            String twitterUrl = searchTwitter(driver, companyName);
            if (twitterUrl != null && validateUrl(twitterUrl, "twitter.com")) {
                System.out.println("Valid Twitter URL found: " + twitterUrl);
                if (checkTwitterActivity(driver, twitterUrl)) {
                    System.out.println("Company is active on Twitter.");
                } else {
                    System.out.println("Company has a Twitter profile but is not active.");
                }
            } else {
                System.out.println("No valid Twitter profile found for " + companyName);
            }

        } finally {
            driver.quit();
        }
    }

    private static WebDriver setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }

    private static String searchTwitter(WebDriver driver, String companyName) {
        String searchUrl = "https://www.google.com/search?q=twitter.com+" + companyName;
        driver.get(searchUrl);

        try {
            WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.g a")));
            return firstResult.getAttribute("href");
        } catch (Exception e) {
            System.out.println("No Twitter results found for " + companyName);
            return null;
        }
    }

    private static boolean validateUrl(String url, String platform) {
        return url.contains(platform);
    }

    private static boolean checkLinkedInActivity(WebDriver driver, String url) {
        driver.get(url);

        try {

            // Extract the post date from LinkedIn posts
            String postDate = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.update-components-actor__sub-description")))
                    .getText();

            System.out.println("LinkedIn post date: " + postDate);
            return isWithinSixMonths(postDate);
        } catch (Exception e) {
            System.out.println("Couldn't find recent post on LinkedIn.");
            return false;
        }
    }

    private static boolean checkTwitterActivity(WebDriver driver, String url) {
        driver.get(url);

        try {
            // Extract the datetime from the time tag in Twitter posts
            String dateTimeString = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.r-18u37iz.r-1q142lx time")))
                    .getAttribute("datetime");

            System.out.println("Twitter post datetime: " + dateTimeString);
            return isWithinSixMonthsTwitter(dateTimeString);
        } catch (Exception e) {
            System.out.println("Couldn't find recent post on Twitter.");
            return false;
        }
    }

    private static boolean isWithinSixMonths(String dateString) {
        LocalDate currentDate = LocalDate.now();
        LocalDate postDate;

        Pattern pattern = Pattern.compile("(\\d+)([dwmo]+)");
        Matcher matcher = pattern.matcher(dateString);

        if (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2);

            switch (unit) {
                case "d":
                    postDate = currentDate.minusDays(value);
                    break;
                case "w":
                    postDate = currentDate.minusWeeks(value);
                    break;
                case "mo":
                    postDate = currentDate.minusMonths(value);
                    break;
                default:
                    System.out.println("Couldn't parse date: " + dateString);
                    return false;
            }
        } else {
            System.out.println("Couldn't find valid time unit in: " + dateString);
            return false;
        }

        LocalDate sixMonthsAgo = currentDate.minus(6, ChronoUnit.MONTHS);
        return postDate.isAfter(sixMonthsAgo);
    }

    private static boolean isWithinSixMonthsTwitter(String dateTimeString) {
        LocalDate currentDate = LocalDate.now();

        try {
            // Parse the datetime string from Twitter's time tag
            OffsetDateTime postDateTime = OffsetDateTime.parse(dateTimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            LocalDate postDate = postDateTime.toLocalDate();

            LocalDate sixMonthsAgo = currentDate.minus(6, ChronoUnit.MONTHS);
            return postDate.isAfter(sixMonthsAgo);
        } catch (Exception e) {
            System.out.println("Couldn't parse Twitter datetime: " + dateTimeString);
            return false;
        }
    }
}