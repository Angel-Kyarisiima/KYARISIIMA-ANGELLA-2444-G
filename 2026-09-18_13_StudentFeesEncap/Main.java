// Day 2026-09-18 - Module 4: Encapsulation - Student Fees Tracker
class StudentFees {
    private String name, regNo;
    private double total, paid;
    public StudentFees(String name, String regNo, double total) {
        this.name = name; this.regNo = regNo; this.total = total; this.paid = 0;
    }
    public void pay(double amount) {
        if (amount > 0) paid += amount;
        System.out.println("Paid " + amount + " | Total paid " + paid + "/" + total + " | Bal " + getBalance());
    }
    public double getBalance() { return total - paid; }
    public boolean isCleared() { return paid >= total; }
    public void display() { System.out.println(regNo + " - " + name + ": " + paid + "/" + total); }
}
public class Main {
    public static void main(String[] args) {
        StudentFees s = new StudentFees("Kyarisiima Angella", "2444/G", 2000000);
        s.pay(800000); s.pay(700000); s.display();
        System.out.println("Cleared? " + s.isCleared());
        s.pay(500000); System.out.println("Cleared? " + s.isCleared());
    }
}
