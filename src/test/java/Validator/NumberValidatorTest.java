package Validator;

import CustomException.NegativesNotAllowedException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

public class NumberValidatorTest {

    @Test(expected = NegativesNotAllowedException.class)
    public void negativeNumberShouldThrowException() throws NegativesNotAllowedException{
        NumberValidator numberValidator = new NumberValidator();
        numberValidator.validate(Arrays.asList(new Integer[]{1, -2}));
    }
}
