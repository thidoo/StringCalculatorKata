package Validator;

import CustomException.InvalidDelimiterFormatException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DelimiterValidatorTest {

    private DelimiterValidator delimiterValidator;

    @Before
    public void setUp() {
        delimiterValidator = new DelimiterValidator();
    }

    @Test
    public void validHeaderTest_singleCharacterDelimiter() throws InvalidDelimiterFormatException {
        delimiterValidator.validateDelimiterInHeader(Arrays.asList(new String[]{"*"}));
    }

    @Test
    public void validHeaderTest_MultipleDelimiters() throws InvalidDelimiterFormatException {
        delimiterValidator.validateDelimiterInHeader(Arrays.asList(new String[]{"#","&"}));
    }

    @Test
    public void validHeaderTest_DelimiterOfAnyLength() throws InvalidDelimiterFormatException {
        delimiterValidator.validateDelimiterInHeader(Arrays.asList(new String[]{"***"}));
    }

    @Test
    public void validHeaderTest_NumberDelimiter() throws InvalidDelimiterFormatException {
        delimiterValidator.validateDelimiterInHeader(Arrays.asList(new String[]{"*1*"}));
    }

    @Test(expected = InvalidDelimiterFormatException.class)
    public void inValidHeaderTest_NoDelimiterInsideSquareBrackets() throws InvalidDelimiterFormatException {
        DelimiterValidator delimiterValidator = new DelimiterValidator();
        delimiterValidator.validateDelimiterInHeader(Arrays.asList(new String[]{"*",""}));
    }

    @Test
    public void validBodyTest() throws InvalidDelimiterFormatException {
        DelimiterValidator delimiterValidator = new DelimiterValidator();
        String[] validBody = new String[]{"1",",","2","\n","3"};
        delimiterValidator.validateDelimiterInBody(validBody);
    }

    @Test(expected = InvalidDelimiterFormatException.class)
    public void invalidBodyTest_moreThanOneDelimiter_inARow() throws InvalidDelimiterFormatException {
        DelimiterValidator delimiterValidator = new DelimiterValidator();
        String[] invalidBody = new String[]{"2","","1","2"};
        delimiterValidator.validateDelimiterInBody(invalidBody);
    }
}
