// Day 2026-09-30 - Module 2: Control Flow - if-else (Grading System)
public class Main {
    public static void main(String[] args) {
        int marks = 78;
        String grade;
        if (marks >= 80) grade = "D1 - Distinction";
        else if (marks >= 70) grade = "C2 - Credit";
        else if (marks >= 60) grade = "C3";
        else if (marks >= 50) grade = "P7 - Pass";
        else grade = "F9 - Fail";

        System.out.println("Marks: " + marks + " => Grade: " + grade);

        // Another example - check voting eligibility
        int age = 21;
        if (age >= 18) System.out.println("Eligible to vote");
        else System.out.println("Not eligible");
    }
}
