import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class SocialMediaChecker {

    private static final String TWITTER_URL = "https://x.com/";
    private static final String INSTAGRAM_URL = "https://www.instagram.com/";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SocialMediaChecker <companyName>");
            return;
        }

        String companyName = args[0];
        System.out.println("Checking social media for company: " + companyName);

        checkTwitter(companyName);
        checkInstagram(companyName);
    }

    private static void checkTwitter(String companyName) {
        String url = TWITTER_URL + companyName;

        try {
            if (checkPageExists(url)) {
                System.out.println("Twitter: Exists for " + companyName);
            } else {
                System.out.println("Twitter: Not found");
            }
        } catch (Exception e) {
            System.out.println("Twitter: Error checking - " + e.getMessage());
        }
    }

    private static void checkInstagram(String companyName) {
        String url = INSTAGRAM_URL + companyName;

        try {
            if (checkPageExists(url)) {
                System.out.println("Instagram: Exists for " + companyName);
            } else {
                System.out.println("Instagram: Not found");
            }
        } catch (Exception e) {
            System.out.println("Instagram: Error checking - " + e.getMessage());
        }
    }

    private static boolean checkPageExists(String urlString) throws Exception {
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        return responseCode == 200;
    }
}
