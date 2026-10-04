// Day 2026-10-04 - Module 4: Getters/Setters with Validation
class Employee {
    private String name; private double salary;
    public Employee(String name, double salary) { this.name=name; setSalary(salary); }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public void setSalary(double s) {
        if (s >= 0) salary=s;
        else System.out.println("Salary cannot be negative");
    }
    public void setName(String n) { if(n != null && !n.isEmpty()) name=n; }
}
public class Main {
    public static void main(String[] args) {
        Employee e = new Employee("Angella", 2000000);
        System.out.println(e.getName() + " Salary: " + e.getSalary());
        e.setSalary(-1000); // blocked
        e.setSalary(2500000);
        System.out.println("Updated: " + e.getSalary());
    }
}
