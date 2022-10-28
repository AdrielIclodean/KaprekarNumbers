package com.kaprekars;


import java.util.*;

/**
 * Having a 4-digit number
 * constraints: must not be like 4444
 * <p>
 * 1. Sort the digits descending
 * 2. Sort the digits ascending
 * 3. Subtract first from the second
 * 4. Do this until we get 6174
 */
public class Main {

    public static void main(String[] args) {
        int number = 9998;

        int maxNumberofIterations = 0;
        for (int i = number; i >= 1000; i--) {
            int currentNumber = i;
            int numberOfLoops = 0;
            if (numbersIsAllowed(currentNumber)) {
                System.out.println(currentNumber);
                while (currentNumber != 6174) {
                    List<Integer> digits = getDigits(currentNumber);
                    int firstNumber = getDescendingNumber(digits);
                    int secondNumber = getAscendingNumber(digits);

                    currentNumber = firstNumber - secondNumber;
                    numberOfLoops++;

                    System.out.printf(" -> (%d - %d) = %d", firstNumber, secondNumber, currentNumber);
                }

                System.out.printf(" -> %d iterations", numberOfLoops);
                System.out.println();
                if (maxNumberofIterations < numberOfLoops) {
                    maxNumberofIterations = numberOfLoops;
                }
            }
        }

        System.out.printf("Max number of iterations is %d", maxNumberofIterations);
    }

    /**
     * Add leading zeroes until we have a 4-digit number
     */
    private static int add0ToEndIfNecessary(int firstNumber) {
        if (firstNumber < 1000) {
            List<Integer> digits = getDigits(firstNumber);
            while (digits.size() < 4) {
                digits.add(0);
            }
            return getFinalNumber(digits);
        }
        return firstNumber;
    }

    private static int getAscendingNumber(List<Integer> number) {
        return getNumberSorted(number, Comparator.naturalOrder());
    }

    private static int getDescendingNumber(List<Integer> number) {
        return add0ToEndIfNecessary(getNumberSorted(number, Comparator.reverseOrder()));
    }

    private static int getNumberSorted(List<Integer> digits, Comparator<Integer> comparator) {
        digits.sort(comparator);
        return getFinalNumber(digits);
    }

    private static int getFinalNumber(List<Integer> digits) {
        int finalNumber = 0;
        int power = digits.size() - 1;
        for (Integer digit : digits) {
            finalNumber = (int) Math.pow(10, power) * digit + finalNumber;
            power--;
        }
        return finalNumber;
    }

    private static List<Integer> getDigits(int number) {
        List<Integer> digits = new ArrayList<>();
        int tempNumber = number;
        while (tempNumber != 0) {
            digits.add(tempNumber % 10);
            tempNumber /= 10;
        }
        return digits;
    }

    private static boolean numbersIsAllowed(Integer number) {
        return ((Set<Integer>) new HashSet<>(getDigits(number))).size() >= 2;
    }
}
