import Calculator.StringCalculator;
import Calculator.SumCalculator;
import CustomException.InvalidDelimiterFormatException;
import CustomException.NegativesNotAllowedException;
import Parser.DelimiterFetcher;
import Parser.CustomStringSplitter;
import Parser.NumberExtractor;
import Validator.DelimiterValidator;
import Validator.NumberValidator;

public class Main {

    public static void main(String[] args) throws InvalidDelimiterFormatException, NegativesNotAllowedException {

        NumberExtractor numberExtractor = new NumberExtractor(new CustomStringSplitter(),
                new DelimiterFetcher(),
                new DelimiterValidator());
        StringCalculator stringCalculator = new StringCalculator(numberExtractor, new NumberValidator(), new SumCalculator());

        int result = stringCalculator.process("1,2,3");
        System.out.println(result);
    }
}
