class Product {
    String name; double price;
    Product(String n, double p) { name=n; price=p; }
}
class Cart {
    Product[] items = new Product[10]; int count=0;
    void add(Product p) { if(count<10) items[count++]=p; }
    double total() { double s=0; for(int i=0;i<count;i++) s+=items[i].price; return s; }
    void receipt() { for(int i=0;i<count;i++) System.out.println(items[i].name+" - "+items[i].price); System.out.println("TOTAL: "+total()); }
}
public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.add(new Product("Laptop", 2500000));
        cart.add(new Product("Mouse", 50000));
        cart.receipt();
    }
}
