class Student {
    String name, regNo;
    Student(String name, String regNo) {
        this.name = name;
        this.regNo = regNo;
    }
    void display() { System.out.println(regNo + " - " + name); }
}
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Kyarisiima Angella", "2444/G");
        Student s2 = new Student("Musa Ali", "2444/G-03");
        s1.display();
        s2.display();
        System.out.println("Objects created via constructor with 'this' keyword");
    }
}
