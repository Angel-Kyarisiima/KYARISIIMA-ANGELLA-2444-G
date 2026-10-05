// Day 2026-10-05 - Module 4: Encapsulation - BankAccount
class BankAccount {
    private String accountNumber;
    private double balance;
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public void deposit(double amount) {
        if (amount > 0) { balance += amount; System.out.println("Deposited: " + amount + ", New Balance: " + balance); }
        else System.out.println("Deposit must be positive");
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) { balance -= amount; System.out.println("Withdrew: " + amount + ", New Balance: " + balance); }
        else System.out.println("Invalid withdrawal");
    }
}
public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("2444", 500000);
        System.out.println("Account: " + acc.getAccountNumber() + " Balance: " + acc.getBalance());
        acc.deposit(100000);
        acc.withdraw(200000);
        acc.withdraw(1000000);
        System.out.println("Final Balance: " + acc.getBalance());
    }
}
