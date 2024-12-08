import java.util.Scanner;

class Library {
    String[] availableBooks;
    String[] issuedBooks;
    int size;
    int issuedSize;
    Scanner sc = new Scanner(System.in);

    public Library(int limit) {
        availableBooks = new String[limit];
        issuedBooks = new String[limit];
        size = 0;
        issuedSize = 0;
    }

    public void getAvailableBooks() {
        if (size == 0) {
            System.out.println("No books available");
        } else {
            System.out.println("Available Books:");
            for (int i = 0; i < size; i++) {
                System.out.println(availableBooks[i]);
            }
        }
    }

    public void addBook(String book) {
        if (size < availableBooks.length) {
            availableBooks[size++] = book;
            System.out.println("Book is added.");
        } else {
            System.out.println("Library is full.");
        }
    }

    public boolean issueBook(String book) {
        for (int i = 0; i < size; i++) {
            if (availableBooks[i] != null && availableBooks[i].equals(book)) {
                System.out.println("Issuing the book: " + book);
                issuedBooks[issuedSize++] = book; // Add to issuedBooks
                for (int j = i; j < size - 1; j++) {
                    availableBooks[j] = availableBooks[j + 1]; // Shift elements
                }
                availableBooks[--size] = null; // Reduce size and clear last slot
                return true;
            }
        }
        System.out.println("The book is not available.");
        return false;
    }

    public boolean returnBook(String book) {
        for (int i = 0; i < issuedSize; i++) {
            if (issuedBooks[i] != null && issuedBooks[i].equals(book)) {
                System.out.println("Returning the book: " + book);
                for (int j = i; j < issuedSize - 1; j++) {
                    issuedBooks[j] = issuedBooks[j + 1]; // Shift elements
                }
                issuedBooks[--issuedSize] = null; // Reduce issued size and clear last slot
                availableBooks[size++] = book; // Add back to availableBooks
                return true;
            }
        }
        System.out.println("The book is not in the issued list.");
        return false;
    }
}

public class OnlineLibrary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String book;
        Library library = new Library(50);

        while (true) {
            System.out.println("\nChoose an option: add book, issue book, return book, view available books, or exit:");
            String purpose = sc.nextLine().toLowerCase();

            switch (purpose) {
                case "add book":
                    System.out.print("Enter the book name: ");
                    book = sc.nextLine();
                    library.addBook(book);
                    break;
                case "issue book":
                    System.out.print("Which book do you want to issue? ");
                    book = sc.nextLine();
                    library.issueBook(book);
                    break;
                case "return book":
                    System.out.print("Which book do you want to return? ");
                    book = sc.nextLine();
                    library.returnBook(book);
                    break;
                case "view available books":
                    library.getAvailableBooks();
                    break;
                case "exit":
                    System.out.println("Exiting the library system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
