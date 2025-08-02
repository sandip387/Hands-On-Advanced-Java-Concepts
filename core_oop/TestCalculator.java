package core_oop;

import core_oop.mypackage.Calculator;

public class TestCalculator {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println("Demonstraing package creation: ");
        System.out.println("Sum: " + calc.add(10, 5));
        System.out.println("Difference: " + calc.subtract(10, 5));
    }
}
