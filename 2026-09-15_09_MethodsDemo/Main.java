class Calculator {
    int add(int a, int b) { return a+b; }
    int multiply(int a, int b) { return a*b; }
    double average(int a, int b, int c) { return (a+b+c)/3.0; }
}
public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Add 10+20 = " + calc.add(10,20));
        System.out.println("Multiply 6*7 = " + calc.multiply(6,7));
        System.out.printf("Average 70,80,90 = %.2f\n", calc.average(70,80,90));
    }
}
