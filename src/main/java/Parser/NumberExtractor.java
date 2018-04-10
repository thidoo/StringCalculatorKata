package Parser;

import Calculator.StringCalculator;
import CustomException.InvalidDelimiterFormatException;
import Validator.DelimiterValidator;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberExtractor {

    private CustomStringSplitter customStringSplitter;

    private DelimiterFetcher delimiterFetcher;
    private DelimiterValidator delimiterValidator;

    private String customDelimiterString;
    private String numberString;


    public NumberExtractor(CustomStringSplitter customStringSplitter,
                           DelimiterFetcher delimiterFetcher,
                           DelimiterValidator delimiterValidator) {
        this.customStringSplitter = customStringSplitter;

        this.delimiterFetcher = delimiterFetcher;
        this.delimiterValidator = delimiterValidator;
    }

    public Optional<List<Integer>> parse(String inputString) throws InvalidDelimiterFormatException {
        if (inputString.isEmpty()) { return Optional.empty(); }
        return parseNonEmptyString(inputString);
    }

    private Optional<List<Integer>> parseNonEmptyString(String inputString) throws InvalidDelimiterFormatException {
        separateCustomDelimiterPart_FromNumberPart(inputString);

        Optional<List<String>> delimiters = delimiterFetcher.parse(customDelimiterString);
        delimiterValidator.validateDelimiterInHeader(delimiters);

        return extractNumbers(numberString, delimiters);
    }

    private void separateCustomDelimiterPart_FromNumberPart(String inputString) {
        customStringSplitter.parse(inputString);
        customDelimiterString = customStringSplitter.getDelimiterString();
        numberString = customStringSplitter.getNumberString();
    }

    private Optional<List<Integer>> extractNumbers(String numberString, Optional<List<String>> delimiters) throws InvalidDelimiterFormatException {
        String[] parsedStringArray = parseToStringArray(numberString, delimiters);
        delimiterValidator.validateDelimiterInBody(parsedStringArray);
        return Optional.ofNullable(convertToIntegerList(parsedStringArray));
    }

    private String[] parseToStringArray(String inputString, Optional<List<String>> delimiters) {
        String splitPattern = getSplitRegexPattern(delimiters);
        return inputString.split(splitPattern);
    }

    private List<Integer> convertToIntegerList(String[] stringArray) {
        if (stringArray.length == 0) {
            return null;
        } else {
            return Stream.of(stringArray)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        }
    }

    private String getSplitRegexPattern(Optional<List<String>> delimiters) {
        if (!delimiters.isPresent()) {
            return getDefaultPattern();
        }
        return getPatternForNonDefaultDelimiters(delimiters);
    }

    private String getDefaultPattern() {
        return String.format("%s|%s", StringCalculator.getAlternateDelimiter(),
                StringCalculator.getDefaultDelimiter());
    }

    private String getPatternForNonDefaultDelimiters(Optional<List<String>> delimiters) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringCalculator.getAlternateDelimiter());

        delimiters.get().forEach(delimiter -> {
            stringBuilder.append("|");
            stringBuilder.append(Pattern.quote(delimiter));
        });

        return stringBuilder.toString();
    }
}
