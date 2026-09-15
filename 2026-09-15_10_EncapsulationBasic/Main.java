class StudentEncap {
    private String name;
    private int age;

    public StudentEncap(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { if (age > 0) this.age = age; }
}
public class Main {
    public static void main(String[] args) {
        StudentEncap s = new StudentEncap("Angella", 21);
        System.out.println("Name: " + s.getName() + ", Age: " + s.getAge());
        s.setAge(22);
        System.out.println("Updated Age: " + s.getAge());
        s.setAge(-5); // validation blocks
        System.out.println("After invalid age: " + s.getAge());
    }
}
