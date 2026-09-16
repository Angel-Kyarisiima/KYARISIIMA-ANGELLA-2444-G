// Day 2026-09-16 - Module 2: Switch Statement
public class Main {
    public static void main(String[] args) {
        String day = "Monday";
        switch (day) {
            case "Monday": System.out.println("OOP Lecture Day"); break;
            case "Tuesday": System.out.println("Lab Day"); break;
            case "Friday": System.out.println("Assignment Due"); break;
            default: System.out.println("Self Study");
        }
        // Grade switch
        char grade = 'B';
        switch (grade) {
            case 'A': System.out.println("Excellent"); break;
            case 'B': System.out.println("Good"); break;
            case 'C': System.out.println("Fair"); break;
            default: System.out.println("Needs improvement");
        }
    }
}
