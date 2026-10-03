// Day 2026-10-03 - Module 4: Access Modifiers - private, default, protected, public
class Demo {
    private String privateVar = "private";
    String defaultVar = "default/package";
    protected String protectedVar = "protected";
    public String publicVar = "public";

    private void privateMethod() { System.out.println("private method"); }
    public void show() {
        System.out.println(privateVar + " | " + defaultVar + " | " + protectedVar + " | " + publicVar);
        privateMethod();
    }
}
public class Main {
    public static void main(String[] args) {
        Demo d = new Demo();
        d.show();
        System.out.println("publicVar accessible: " + d.publicVar);
        System.out.println("defaultVar accessible: " + d.defaultVar);
        System.out.println("Encapsulation hides private, exposes via methods");
    }
}
