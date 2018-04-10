package Parser;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DelimiterFetcherTest {

    private DelimiterFetcher delimiterFetcher;

    @Before
    public void setUp() {
        delimiterFetcher = new DelimiterFetcher();
    }

    @Test
    public void givenHeader_ShouldReturnDelimiters() {
        String header = "[##][&]";
        Optional<List<String>> expectedDelimiters = Optional.of(Arrays.asList(new String[]{"##", "&"}));

        Optional<List<String>> actualDelimiters = delimiterFetcher.parse(header);
        assertThat(actualDelimiters, equalTo(expectedDelimiters));
    }

    @Test
    public void singleDelimiterHeader_ShouldReturnSingleDelimiter() {
        String header = "*";
        Optional<List<String>> expectedDelimiters = Optional.of(Arrays.asList(new String[]{"*"}));

        Optional<List<String>> actualDelimiters = delimiterFetcher.parse(header);
        assertThat(actualDelimiters, equalTo(expectedDelimiters));
    }

    @Test
    public void singleDelimiterHeader_InSquareBrackets_ShouldReturnSingleDelimiter() {
        String header = "[*]";
        Optional<List<String>> expectedDelimiters = Optional.of(Arrays.asList(new String[]{"*"}));

        Optional<List<String>> actualDelimiters = delimiterFetcher.parse(header);
        assertThat(actualDelimiters, equalTo(expectedDelimiters));
    }

    @Test
    public void delimiterLengthLongerThan1_NotMadeUpOfRepeatedCharacter() {
        String header = "[*-]";
        Optional<List<String>> expectedDelimiters = Optional.of(Arrays.asList(new String[]{"*-"}));

        Optional<List<String>> actualDelimiters = delimiterFetcher.parse(header);
        assertThat(actualDelimiters, equalTo(expectedDelimiters));
    }

    @Test
    public void delimiterLengthLongerThan1_NotInSquareBrackets() {
        String header = "*-";
        Optional<List<String>> expectedDelimiters = Optional.of(Arrays.asList(new String[]{"*-"}));

        Optional<List<String>> actualDelimiters = delimiterFetcher.parse(header);
        assertThat(actualDelimiters, equalTo(expectedDelimiters));
    }

    @Test
    public void emptyContent_InsideSquareBrackets() {
        String header = "[][&]";
        Optional<List<String>> expectedDelimiters = Optional.of(Arrays.asList(new String[]{"", "&"}));

        Optional<List<String>> actualDelimiters = delimiterFetcher.parse(header);
        assertThat(actualDelimiters, equalTo(expectedDelimiters));
    }
}



