package Parser;

/**
 * Parse an input string into:
 * - a delimiterString part: which could be an empty string or is the substring between // and \n
 * - a numberString part: which can be an empty string or is the substring following the divider character \n
 */
public class CustomStringSplitter {

    private final static String CUSTOM_DELIMITER_START = "//";
    private final static String DIVIDER = "\n";

    private final static int SPLIT_COUNT = 2;
    private final static int CUSTOM_DELIMITER_START_INDEX = 2;

    private String delimiterString;
    private String numberString;

    public void parse(String inputString){
        if (inputString.startsWith(CUSTOM_DELIMITER_START)) {
            split(inputString);
        } else {
            delimiterString = "";
            numberString = inputString;
        }
    }

    private void split(String inputString){
        String[] splitContents = inputString.split(DIVIDER, SPLIT_COUNT);

        delimiterString = splitContents[0].substring(CUSTOM_DELIMITER_START_INDEX);
        numberString = splitContents[1];
    }

    public String getDelimiterString() {
        return delimiterString;
    }

    public String getNumberString() {
        return numberString;
    }
}
