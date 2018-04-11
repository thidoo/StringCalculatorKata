package Calculator;

import CustomException.NegativesNotAllowedException;

import java.util.List;
import java.util.Optional;

public class SumCalculator {

    public int sum(Optional<List<Integer>> integerListOptional) {
        int sumTotal = 0;

        if (integerListOptional.isPresent()) {
            List<Integer> integerList = integerListOptional.get();

            for (Integer number : integerList) {
                if (number < 1000){
                    sumTotal += number;
                }
            }
        }

        return sumTotal;
    }
}
