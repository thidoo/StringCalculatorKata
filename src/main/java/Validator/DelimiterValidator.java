package Validator;

import CustomException.InvalidDelimiterFormatException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DelimiterValidator {

    public void validateDelimiterInHeader(List<String> delimiters) throws InvalidDelimiterFormatException {
        if (delimiters != null){
            for (String delimiter: delimiters){
                validateSingleDelimiter(delimiter);
            }
        }
    }

    private void validateSingleDelimiter(String delimiter) throws InvalidDelimiterFormatException {
        if (delimiter.length() == 0){
            throw new InvalidDelimiterFormatException();
        }

        if (delimiter.length() == 1 && delimiter.matches("[0-9]")){
            throw new InvalidDelimiterFormatException();
        }

        if (delimiter.length() > 1){
            if (!isDelimiterMadeUpOfSameCharacterRepeatedly(delimiter) && !isValidNumberDelimiter(delimiter)){
                throw new InvalidDelimiterFormatException();
            }
        }
    }

    private boolean isValidNumberDelimiter(String delimiter){
        return delimiter.matches("[^0-9][0-9][^0-9]");

    }

    private boolean isDelimiterMadeUpOfSameCharacterRepeatedly(String delimiter){
        String pattern = String.format("%s+", Pattern.quote(String.valueOf(delimiter.charAt(0))));
        return delimiter.matches(pattern);
    }

    // Body should not have two or more delimiters next to each other, and
    // should not start or end with a delimiter
    public void validateDelimiterInBody(String[] inputBody) throws InvalidDelimiterFormatException {
        if (Stream.of(inputBody).anyMatch(String::isEmpty)){
            throw new InvalidDelimiterFormatException();
        }
    }
}
