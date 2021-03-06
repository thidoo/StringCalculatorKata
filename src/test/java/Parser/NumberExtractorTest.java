package Parser;

import CustomException.InvalidDelimiterFormatException;
import Validator.DelimiterValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberExtractorTest {

    private NumberExtractor numberExtractor;
    @Before
    public void setUp(){
        numberExtractor = new NumberExtractor(new CustomStringSplitter(),
                                        new DelimiterFetcher(),
                                        new DelimiterValidator());
    }

    @Test
    public void givenBodyAndDelimiters_ShouldReturn_IntegerList() throws InvalidDelimiterFormatException{
        List<Integer> actual = numberExtractor.extract("//;\n1;2;3\n4");
        List<Integer> expected = Arrays.asList(new Integer[]{1,2,3,4});

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void multipleDelimitersCase() throws InvalidDelimiterFormatException{
        List<Integer> actual = numberExtractor.extract("//[*][&]\n1*2&3\n4");
        List<Integer> expected = Arrays.asList(new Integer[]{1,2,3,4});

        assertThat(actual, equalTo(expected));
    }

    @Test(expected = InvalidDelimiterFormatException.class)
    public void contiguousDelimiters_ShouldThrowException() throws InvalidDelimiterFormatException{
        numberExtractor.extract("//[*][&]\n1*2&&3\n4");
    }

    @Test(expected = InvalidDelimiterFormatException.class)
    public void NoCharacterInsideSquareBrackets_ShouldThrowException() throws InvalidDelimiterFormatException {
        numberExtractor.extract("//[*][]\n1*2*3\n4");
    }
}
