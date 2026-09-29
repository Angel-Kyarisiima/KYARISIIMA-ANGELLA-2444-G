// Day 2026-09-29 - Module 3: Inheritance preview (extends)
class Vehicle {
    String brand; int year;
    Vehicle(String brand, int year) { this.brand=brand; this.year=year; }
    void start() { System.out.println(brand + " is starting..."); }
}
class Car extends Vehicle {
    int doors;
    Car(String brand, int year, int doors) { super(brand, year); this.doors=doors; }
    void start() { System.out.println(brand + " car vrooms! " + doors + " doors"); }
}
public class Main {
    public static void main(String[] args) {
        Vehicle v = new Vehicle("Generic", 2020); v.start();
        Car c = new Car("Toyota", 2022, 4); c.start();
        System.out.println("Inheritance: Car extends Vehicle");
    }
}
