import org.junit.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
//
//public class TestWordCounter {
//    private String url1;
//    private String url2;
//    private String url3;
//    private WordCounter wc1;
//    private WordCounter wc2;
//    private WordCounter wc3;
//
//
//    @Before
//    public void setUp() {
//        Set<String> s1 = new HashSet<>();
//        s1.add(url1);
//        s1.add(url2);
//        s1.add(url3);
//
//        Main.Word w1 = new Main.Word("hello", s1);
//        Main.Word w2 = new Main.Word("my", s1);
//        Main.Word w3 = new Main.Word("name", s1);
//        Main.Word w4 = new Main.Word("is", s1);
//        Main.Word w5 = new Main.Word("noknok", s1);
//
//        url1 = "";
//        url2 = " ";
//        url3 = "a";
//
//        HashMap<String, Main.Word> hm1 = new HashMap<>();
//        hm1.put("hello", w1);
//        hm1.put("my", w2);
//        hm1.put("name", w3);
//        hm1.put("is", w4);
//        hm1.put("noknok", w5);
//
//
//        wc1 = new WordCounter(url1, hm1, new String[] {"hello", "hello", "hello", "is", "noknok"});
//        wc2 = new WordCounter(url2, hm1, new String[]{"noknok"});
//        wc3 = new WordCounter(url3, hm1, new String[]{"Hello", "my", "name", "is", "noknok"});
//    }
//}
