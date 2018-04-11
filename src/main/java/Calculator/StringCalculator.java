package Calculator;

import CustomException.InvalidDelimiterFormatException;
import CustomException.NegativesNotAllowedException;
import Parser.NumberExtractor;
import Validator.NumberValidator;

import java.util.List;

public class StringCalculator {

    private final static String DEFAULT_DELIMITER = ",";
    private final static String ALTERNATE_DELIMITER = "\n";

    private NumberExtractor numberExtractor;
    private NumberValidator numberValidator;
    private SumCalculator sumCalculator;

    public StringCalculator(NumberExtractor numberExtractor, NumberValidator numberValidator, SumCalculator sumCalculator){

        this.numberExtractor = numberExtractor;
        this.numberValidator = numberValidator;
        this.sumCalculator = sumCalculator;
    }

    public int process(String inputString) throws InvalidDelimiterFormatException,
                                                    NegativesNotAllowedException {

        List<Integer> numbers = numberExtractor.extract(inputString);
        numberValidator.validate(numbers);

        return sumCalculator.sum(numbers);
    }

    public static String getDefaultDelimiter() {
        return DEFAULT_DELIMITER;
    }

    public static String getAlternateDelimiter() {
        return ALTERNATE_DELIMITER;
    }
}
