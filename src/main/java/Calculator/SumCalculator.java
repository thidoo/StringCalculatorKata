package Calculator;

import java.util.List;

public class SumCalculator {

    public int sum(List<Integer> numbers) {
        int sumTotal = 0;

        if (numbers != null) {
            for (Integer number : numbers) {
                if (number < 1000){
                    sumTotal += number;
                }
            }
        }

        return sumTotal;
    }
}
