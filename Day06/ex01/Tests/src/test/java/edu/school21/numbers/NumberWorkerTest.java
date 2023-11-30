package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    private NumberWorker numberWorker;

    @BeforeEach
    void setUp() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @CsvSource({"2,3,5,7"})
    void isPrimeForPrimes(int number) {
        boolean isPrime = numberWorker.isPrime(number);
        assertTrue(isPrime);
    }

    @ParameterizedTest
    @CsvSource({"4,6,8,9"})
    void isPrimeForNotPrimes(int number) {
        boolean isPrime = numberWorker.isPrime(number);
        assertFalse(isPrime);
    }

    @ParameterizedTest
    @CsvSource({"0, 1, -100, -999"})
    void isPrimeForIncorrectNumbers(int invalidNumber) {
        assertThrows(IllegalNumberException.class, ()->numberWorker.isPrime(invalidNumber));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void testDigitsSum(int number, int expectedNumber){
        assertEquals(numberWorker.digitsSum(number), expectedNumber);
    }
}
