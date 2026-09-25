// Day 2026-09-25 - Module 3&4: 'this' keyword demo
class Person {
    private String name; private int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    void display() { System.out.println(this.name + " is " + this.age + " years"); }
    Person getThis() { return this; }
}
public class Main {
    public static void main(String[] args) {
        Person p = new Person("Angella", 21);
        p.display();
        System.out.println("this refers to current object: " + p.getThis());
    }
}
