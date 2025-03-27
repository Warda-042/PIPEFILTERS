package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Arrays;

public class PipeAndFilter {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
   
        List<Function<List<Integer>, List<Integer>>> filters = new ArrayList<>();
        filters.add(PipeAndFilter::filterEvenNumbers);        
        filters.add(PipeAndFilter::squareNumbers);           
        filters.add(PipeAndFilter::filterNumbersGreaterThanTen);  
        filters.add(PipeAndFilter::filterOddNumbers);        // New filter for cube numbers
        filters.add(PipeAndFilter::cubeNumbers);              // New filter for cube numbers
        filters.add(PipeAndFilter::filterPrimeNumbers);       // New filter for prime numbers


        List<Integer> result = processPipeline(input, filters);

    
        System.out.println(result);
    }


    private static List<Integer> processPipeline(List<Integer> input, List<Function<List<Integer>, List<Integer>>> filters) {
        List<Integer> output = input;
        for (Function<List<Integer>, List<Integer>> filter : filters) {
            output = filter.apply(output);
        }
        return output;
    }


    private static List<Integer> filterEvenNumbers(List<Integer> input) {
        return input.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }

    
    private static List<Integer> squareNumbers(List<Integer> input) {
        return input.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

 
    private static List<Integer> filterNumbersGreaterThanTen(List<Integer> input) {
        return input.stream()
                .filter(n -> n > 10)
                .collect(Collectors.toList());
    }


    private static List<Integer> filterOddNumbers(List<Integer> input) {
        return input.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
    }
    
    
    private static List<Integer> cubeNumbers(List<Integer> input) {
        return input.stream()
                .map(n -> n * n * n)
                .collect(Collectors.toList());
    }
    
  
    private static List<Integer> filterPrimeNumbers(List<Integer> input) {
        return input.stream()
                .filter(PipeAndFilter::isPrime)
                .collect(Collectors.toList());
    }
    
  
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}