import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main class to drive program
 */
public class Main {

    static final String GLOBAL = "GLOBAL"; // const
    static final int TOPNWORDS = 3; // const

    /**
     * Class Word is a container for a
     * word and its count in global and url specific contexts.
     *
     * The map is string -> Word, so when we encounter a string we can access its
     * corresponding word object 
     */
    static class Word {

        private final String word; // string representation
        private final HashMap<String, Integer> wordCounts; //map of counts for each URL

        Word(String word, Set<String> urls) {
            this.word = word;
            this.wordCounts = new HashMap<>();
            wordCounts.put(GLOBAL, 0);
            for (String s : urls) {
                wordCounts.put(s,0);
            }
        }

        /**
         * Two Word objects are equal if their String value is equal
         * @param o word to compare to this one
         * @return boolean
         */
        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Word)) {
                return false;
            }
            Word w = (Word) o;
            return w.word.equals(this.word);
        }

        /**
         * Increment the count for this word globally and for the calling url
         * This makes life easy in the WordCounter object
         * @param url url that word appeared in
         */
        public void increment(String url) {
            this.wordCounts.put(GLOBAL, this.wordCounts.get(GLOBAL) + 1);
            this.wordCounts.put(url, this.wordCounts.get(url) + 1);
        }
    }

    /**
     * Format output for printing to console.
     * @param lst a list of word objects containing individual counts for each URL
     * @param urls list of URLs we counted for
     * @return formatted string for output
     */
    private static String formatOutput(List<Word> lst, Set<String> urls) {
        StringBuilder output = new StringBuilder(); // to build output String

        output.append("Output #1" + "\n" + "=======\n\n");
        for (String url : urls) { // for each URL
            output.append(url).append("\n");

            // sort based on custom comparator.
            lst.sort((w2, w1) -> w1.wordCounts.get(url) > w2.wordCounts.get(url) ? 1 : w1.wordCounts.get(url) > w2.wordCounts.get(url) ? 0 : -1);

            int n = 0;
            while ( n < Math.min(TOPNWORDS, lst.size() )) { // to avoid index out of bounds
                output.append(lst.get(n).word);
                output.append(" - ").append(lst.get(n).wordCounts.get(url)).append("\n");
                n ++;
            }
        }
        output.append("========================\n");

        // another custom comparator
        lst.sort((w2, w1) -> w1.wordCounts.get(GLOBAL) > w2.wordCounts.get(GLOBAL) ? 1 : w1.wordCounts.get(GLOBAL) > w2.wordCounts.get(GLOBAL) ? 0 : -1);
        output.append("Output #2\n").append("=======\n\n");
        for (Word w : lst) {
            output.append(w.word).append(" - ").append(w.wordCounts.get(GLOBAL)).append("\n");
        }

        return output.toString();
    }

    /**
     * Read text file to get Set of contents on each line.
     *
     * I chose newline delimiter arbitrarily
     * @param filepath filepath to file. received as user input
     * @return Set of Strings, one String for each line in the input file
     */
    private static Set<String> getSetFromTxtFile(String filepath) {
        Set<String> set = new HashSet<>();

        try {
            File f = new File(filepath);
            Scanner reader = new Scanner(f); // scanner obj to inspect the file
            while (reader.hasNextLine()) {
                String line = reader.nextLine(); // get next line from file
                set.add(line);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found"); // could also prompt user to enter new url
            e.printStackTrace();
        }

        return set;
    }

    public static void main(String[] args) {
        // get urls and words from input
        Set<String> urlSet = getSetFromTxtFile(args[0]);
        Set<String> wordSet = getSetFromTxtFile(args[1]);

        // will hold mappings of a string to word obj containing counts
        HashMap<String, Word> wordStringToWordObjMap = new HashMap<>();

        // create a mapping for each word in input file
        wordSet.forEach(w -> wordStringToWordObjMap.put(w, new Word(w, urlSet)));

        // for each url, validate it and count words in it
        for (String url : urlSet) {
            WordCounter counter = new WordCounter(new URLValidator(url), wordStringToWordObjMap);
            counter.countWords();
        }

        // use java stream library to map the values in our wordStringToWordObjMap to a List of word objects
        List<Word> wordsWithCounts = wordStringToWordObjMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());

        // print output
        System.out.println(formatOutput(wordsWithCounts, urlSet));
    }
}