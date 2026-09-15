class Library {
    String name;
    int totalBooks;
    Library(String name, int totalBooks) {
        this.name = name;
        this.totalBooks = totalBooks;
    }
    void showLibrary() {
        System.out.println("Library: " + name + " | Collection: " + totalBooks + " books");
    }
}

class Book extends Library {
    String title;
    String author;
    String code;
    boolean borrowed;

    Book(String title, String author, String code, String libraryName, int totalBooks) {
        super(libraryName, totalBooks);
        this.title = title;
        this.author = author;
        this.code = code;
        this.borrowed = false;
    }

    void borrow() {
        if (!borrowed) {
            borrowed = true;
            totalBooks--;
            System.out.println("Borrowed '" + title + "' by " + author + " [Code: " + code + "] from " + name + " | Remaining: " + totalBooks);
        } else {
            System.out.println("'" + title + "' already borrowed - not available");
        }
    }

    void returnBook() {
        if (borrowed) {
            borrowed = false;
            totalBooks++;
            System.out.println("Returned '" + title + "' to " + name + " | Now: " + totalBooks);
        } else {
            System.out.println("'" + title + "' was not borrowed");
        }
    }

    void details() {
        showLibrary();
        System.out.println("  -> Book: " + title + " | Author: " + author + " | Code: " + code + " | Borrowed: " + borrowed);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Student: Kyarisiima Angella - 2444/G | Assignment: Book extends Library");
        Book b1 = new Book("Principles of OOP", "Angella Kyarisiima", "BK-2444-01", "UICT Central Library", 120);
        Book b2 = new Book("Java Basics", "Jane Doe", "BK-2444-02", "UICT Central Library", 120);
        b1.details();
        b1.borrow();
        b1.borrow();
        b1.returnBook();
        b2.borrow();
        b2.details();
    }
}
