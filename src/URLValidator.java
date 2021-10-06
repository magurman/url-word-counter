import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Class to validate a url. It has one method to return a parsed String[] that contains every word
 * from the url, lowercase and with punctuation removed
 */
public class URLValidator {
    private String url;
    private Document doc;

    public URLValidator(String url) {
        this.url = url;
        this.doc = validate(url); // will either be a document object or null
    }

    private Document validate(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Error! " + url + " is invalid!");
            return null;
        }
    }

    public String[] getHTMLBodyAsList() {
        // return null if invalid url -- or you can throw exception
        if (this.doc == null) {
            return null;
            //throw new IllegalStateException("Must pass valid URL to begin counting words...");
        }

        // strip punctuation and split body of html doc by ' ' delimiter
        String regex = "\\p{Punct}";
        return parse(doc.body().text(), regex).split(" ");
    }

    // decided to separate this method from countWords for testing purposes
    public String parse(String str, String regex) {
        return str.replaceAll(regex, " ").toLowerCase();
    }

    public String getURL() {
        return this.url;
    }
}
