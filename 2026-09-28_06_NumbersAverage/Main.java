// Day 2026-09-28 - Module 2: Numbers & Arithmetic (from Learn_Java/Numbers.java)
public class Main {
    public static void main(String[] args) {
        int number1 = 10, number2 = 20;
        System.out.println("Sum: " + (number1 + number2));
        System.out.println("Difference: " + (number1 - number2));
        System.out.println("Product: " + (number1 * number2));
        if (number2 != 0) System.out.println("Quotient: " + (number1 / number2));
        System.out.println("Remainder: " + (number1 % number2));

        // Average of 5 numbers
        double n1=10, n2=20, n3=30, n4=40, n5=50;
        double avg = (n1+n2+n3+n4+n5)/5;
        System.out.printf("Average = %.2f\n", avg);
    }
}
