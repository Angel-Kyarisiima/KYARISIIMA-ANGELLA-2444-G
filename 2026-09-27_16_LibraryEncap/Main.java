// Day 2026-09-27 - Module 4: Library Book Encapsulation
class Book {
    private String title, author, isbn;
    private int copies, borrowed;
    public Book(String title, String author, String isbn, int copies) {
        this.title=title; this.author=author; this.isbn=isbn; this.copies=copies; this.borrowed=0;
    }
    public String getTitle() { return title; }
    public int available() { return copies - borrowed; }
    public void borrow() {
        if (borrowed < copies) { borrowed++; System.out.println("Borrowed '" + title + "' (" + borrowed + "/" + copies + ")"); }
        else System.out.println("No copies available");
    }
    public void returnBook() {
        if (borrowed > 0) { borrowed--; System.out.println("Returned '" + title + "'"); }
    }
}
public class Main {
    public static void main(String[] args) {
        Book b = new Book("OOP in Java", "Jeff", "ISBN2444", 3);
        b.borrow(); b.borrow();
        System.out.println("Available: " + b.available());
        b.returnBook();
        System.out.println("Available: " + b.available());
    }
}
