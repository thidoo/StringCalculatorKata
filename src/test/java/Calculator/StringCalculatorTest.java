package Calculator;

import CustomException.InvalidDelimiterFormatException;
import CustomException.NegativesNotAllowedException;
import Parser.CustomStringSplitter;
import Parser.DelimiterFetcher;
import Parser.NumberExtractor;
import Validator.DelimiterValidator;
import Validator.NumberValidator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;
    private NumberExtractor numberExtractor;

    @Before
    public void setUp(){

        numberExtractor = new NumberExtractor(new CustomStringSplitter(),
                                        new DelimiterFetcher(),
                                        new DelimiterValidator());

        stringCalculator = new StringCalculator(numberExtractor, new NumberValidator(), new SumCalculator());
    }

    @Test
    public void emptyStringShouldReturn0() throws InvalidDelimiterFormatException,
                                                    NegativesNotAllowedException {
        int actual = stringCalculator.process("");
        int expected = 0;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void singleDigitShouldReturnItself() throws InvalidDelimiterFormatException,
                                                            NegativesNotAllowedException  {
        int actual = stringCalculator.process("1");
        int expected = 1;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void twoDigitsShouldReturnSum() throws InvalidDelimiterFormatException,
                                                        NegativesNotAllowedException  {
        int actual = stringCalculator.process("1,2");
        int expected = 3;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void moreDigitsShouldReturnSum() throws InvalidDelimiterFormatException,
                                                        NegativesNotAllowedException {
        int actual = stringCalculator.process("1,2,3,4,5");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void delimiter_newLineAndCommaInterchangeable() throws InvalidDelimiterFormatException,
                                                                    NegativesNotAllowedException {
        int actual = stringCalculator.process("1,2\n3,4,5");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void delimiter_otherDelimitersShouldWork() throws InvalidDelimiterFormatException,
                                                                NegativesNotAllowedException {
        int actual = stringCalculator.process("//;\n1;2\n3;4;5");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

    @Test(expected = NegativesNotAllowedException.class)
    public void negativeNumberShouldThrowExceptions() throws InvalidDelimiterFormatException,
                                                                NegativesNotAllowedException {
        stringCalculator.process("//;\n1;2\n-3;4;5");
    }

    @Test
    public void ignoreNumbersGreaterThan1000() throws InvalidDelimiterFormatException,
                                                        NegativesNotAllowedException {
        int actual = stringCalculator.process("//;\n1;2\n3;4;5;1000");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void allowsForDelimiterOfAnyLength() throws InvalidDelimiterFormatException,
                                                        NegativesNotAllowedException {
        int actual = stringCalculator.process("//[;;]\n1;;2;;3;;4;;5;;1000");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void allowsForMultipleDelimiters() throws InvalidDelimiterFormatException,
                                                        NegativesNotAllowedException {
        int actual = stringCalculator.process("//[;;][&]\n1;;2;;3&4;;5&1000");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

    @Test
    public void allowsForNumberDelimiters() throws InvalidDelimiterFormatException,
                                                    NegativesNotAllowedException {
        int actual = stringCalculator.process("//[*1*][&]\n1*1*2*1*3&4&5&1000");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

    @Test(expected = InvalidDelimiterFormatException.class)
    public void contigruousDelimitersInBody_ShouldThrowException() throws InvalidDelimiterFormatException,
                                                                            NegativesNotAllowedException {
        int actual = stringCalculator.process("//[*1*][&]\n1*1*2*1*3&&4&5&1000");
        int expected = 15;

        assertThat(expected, equalTo(actual));
    }

}
