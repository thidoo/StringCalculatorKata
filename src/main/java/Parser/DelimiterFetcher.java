package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterFetcher {

    // Each delimiter or delimiter group of repeated single character should be
    // surrounded by square brackets []
    private final static String DELIMITER_FORMAT_PATTERN = "\\[(.*?)\\]";

    public Optional<List<String>> parse(String delimiterString) {
        if (delimiterString.isEmpty()) {
            return Optional.empty();
        }

        if (!delimiterString.startsWith("[")) {
            return addSingleDelimiter(delimiterString);
        }

        return extractDelimitersFromSquareBrackets(delimiterString);

    }

    private Optional<List<String>> addSingleDelimiter(String delimiter) {
        List<String> delimiters = new ArrayList<>();
        delimiters.add(delimiter);
        return Optional.of(delimiters);
    }

    private Optional<List<String>> extractDelimitersFromSquareBrackets(String headerContent) {
        Pattern pattern = Pattern.compile(DELIMITER_FORMAT_PATTERN);
        Matcher matcher = pattern.matcher(headerContent);
        return findMatches(matcher);
    }

    private Optional<List<String>> findMatches(Matcher matcher){
        List<String> delimiters = new ArrayList<>();

        while (matcher.find()) {
            String delimiter = matcher.group(1);
            delimiters.add(delimiter);
        }
        return Optional.of(delimiters);
    }
}
