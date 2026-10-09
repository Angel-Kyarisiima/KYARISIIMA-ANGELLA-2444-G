// Day 2026-10-09 - Module 2 & 3: Arrays with Objects
public class Main {
    public static void main(String[] args) {
        String[] names = {"Angella", "Musa", "Jane"};
        int[] marks = {78, 85, 72};
        System.out.println("Students:");
        for (int i = 0; i < names.length; i++) {
            System.out.println((i+1) + ". " + names[i] + " - " + marks[i]);
            if (marks[i] >= 80) System.out.println("   Grade: D1");
            else if (marks[i] >= 70) System.out.println("   Grade: C2");
            else System.out.println("   Grade: P");
        }
        int sum=0; for(int m: marks) sum+=m;
        System.out.println("Average: " + (sum/marks.length));
    }
}
