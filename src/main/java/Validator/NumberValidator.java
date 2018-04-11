package Validator;

import CustomException.NegativesNotAllowedException;

import java.util.List;
import java.util.Optional;

public class NumberValidator {
    public void validate(List<Integer> numbers) throws NegativesNotAllowedException {
        if (numbers != null){
            checkForNegatives(numbers);
        }
    }

    private void checkForNegatives(List<Integer> numbers) throws NegativesNotAllowedException {
        for (Integer number: numbers){
            if (number < 0){
                throw new NegativesNotAllowedException();
            }
        }
    }
}
