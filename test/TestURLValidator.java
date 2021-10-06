import org.junit.*;


public class TestURLValidator {
    private URLValidator parser1;
    private String regex;

    @Before
    public void setUp() {
        this.regex = "\\p{Punct}";
        this.parser1 = new URLValidator("https://www.google.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidURL0() {
        this.parser1 = new URLValidator("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidURL1() {
        this.parser1 = new URLValidator("fsdfsadfsdfasd");
    }



    @Test
    public void testParse0() {
        String str1 = "This is a sentence.";
        assert parser1.parse(str1, regex).equals("this is a sentence");
    }

    @Test
    public void testParse1() {
        String str1 = "This! is-, a445: sentence.";
        assert parser1.parse(str1, regex).equals("this is a445 sentence");
    }

    @Test
    public void testParse2() {
        String str1 = "How, many! w^&^%*^%*^%*&^ays can I wri\\]te{}{} a sentence!?";
        assert parser1.parse(str1, regex).equals("how many ways can i write a sentence");
    }

    @Test
    public void testParseNoChange() {
        String str1 = "this sentence requires no edits";
        assert parser1.parse(str1, regex).equals("this sentence requires no edits");
    }

    @Test
    public void testParseHyphen() {
        String str1 = "this sent-ence requires no edits, except for these hy-phens";
        assert parser1.parse(str1, regex).equals("this sentence requires no edits except for these hyphens");
    }

}
