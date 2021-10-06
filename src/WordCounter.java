import java.util.*;

/**
 * WordCounter has a urlValidator and an array of words.
 * The constructor takes in a HashMap<String, Word> to use as an access point for the Word
 * objects containing the counts.
 */

public class WordCounter {
    private URLValidator urlValidator; // url validator object with url String contained inside
    private HashMap<String, Main.Word> wordStringToWordObjMap; // map of word Strings to word Objects

    public WordCounter(URLValidator urlValidator, HashMap<String, Main.Word> wordStringToWordObjMap) {
        this.urlValidator = urlValidator;
        this.wordStringToWordObjMap = wordStringToWordObjMap;

    }

    /**
     * Count the words in wordsToCount
     */
    public void countWords() {
        // for each word in htmlBody
        for (String word : urlValidator.getHTMLBodyAsList()) {
           // if we are counting this word
           if (wordStringToWordObjMap.containsKey(word)) {
               wordStringToWordObjMap.get(word).increment(urlValidator.getURL()); // retrieve the Word object and call its increment method
           }
        }
    }
}