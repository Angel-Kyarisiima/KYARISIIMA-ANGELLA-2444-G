// Day 2026-10-01 - Module 2: Control Flow - Loops
public class Main {
    public static void main(String[] args) {
        System.out.println("For loop 1-5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("  i = " + i);
        }
        System.out.println("While loop sum 1-10:");
        int sum = 0, n = 1;
        while (n <= 10) { sum += n; n++; }
        System.out.println("Sum 1..10 = " + sum);

        System.out.println("Do-while:");
        int x = 3;
        do { System.out.println("  x=" + x); x--; } while (x > 0);
    }
}
