package Parser;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomStringSplitterTest {

    private CustomStringSplitter customStringSplitter;

    @Before
    public void setUp(){
        customStringSplitter = new CustomStringSplitter();
    }

    @Test
    public void givenInputString_ReturnsHeaderAndBody() {
        String inputString = "//%\n1%2%3";
        String header = "%";
        String body = "1%2%3";

        customStringSplitter.parse(inputString);
        assertThat(header, equalTo(customStringSplitter.getDelimiterString()));
        assertThat(body, equalTo(customStringSplitter.getNumberString()));
    }

    @Test
    public void emptyHeader_Case() {
        String contentString = "1,2,3";
        String header = "";
        String body = "1,2,3";

        customStringSplitter.parse(contentString);

        assertThat(header, equalTo(customStringSplitter.getDelimiterString()));
        assertThat(body, equalTo(customStringSplitter.getNumberString()));
    }

    @Test
    public void emptyHeaderContent_Case() {
        String contentString = "//\n1,2,3";
        String header = "";
        String body = "1,2,3";

        customStringSplitter.parse(contentString);

        assertThat(header, equalTo(customStringSplitter.getDelimiterString()));
        assertThat(body, equalTo(customStringSplitter.getNumberString()));
    }

    @Test
    public void emptyInputString_Case() {
        String contentString = "";
        String header = "";
        String body = "";

        customStringSplitter.parse(contentString);

        assertThat(header, equalTo(customStringSplitter.getDelimiterString()));
        assertThat(body, equalTo(customStringSplitter.getNumberString()));
    }
}
