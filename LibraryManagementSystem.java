import java.io.*;
import java.util.*;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        text();
        loading();
        menu();
    }

    public static void loading() {
        System.out.println("\n\n\n\n\n\t\t\t\t\t\tPlease Wait...");
        try {
            for (int i = 10; i <= 100; i += 10) {
                System.out.printf("\t\t\t\t\t\tLoading %d%%\r", i);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.err.println("Error during loading: " + e.getMessage());
        }
        System.out.flush();
    }

    public static void text() {
        System.out.println("\n\n\n\n\n\t\t\t\t||Welcome to the Library Management System!||\n");
        System.out.println("\t\t\t\t===========================================\n\n");
        System.out.println("\t\t\t ||We're here to help you manage your library efficiently.||\n");
        System.out.println("\t\t\t\t||Please login to get started.||\n\n\n");
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------");
            System.out.println(">>> Library Management System <<< ");
            System.out.println("----------------------------------\n");
            System.out.println("> 1. User Management Panel ");
            System.out.println("> 2. Book Management Panel ");
            System.out.println("> 3. Exit\n");
            System.out.print("> Enter the number & hit ENTER: ");

            int number = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (number) {
                case 1:
                    userPanel();
                    break;
                case 2:
                    bookPanel();
                    break;
                case 3:
                    System.out.println("Exiting Program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("\n>>> Invalid Input! Redirecting to Main Menu... <<<\n");
            }
        }
    }

    public static void userPanel() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println(">>> Library Management System - User Panel <<< ");
            System.out.println("-----------------------------------------------\n");
            System.out.println("> 1. Add User ");
            System.out.println("> 2. Search User ");
            System.out.println("> 3. Delete User ");
            System.out.println("> 4. List Users ");
            System.out.println("> 5. Open Main Menu ");
            System.out.println("> 6. Close the Program... \n");

            System.out.print("> Enter the number & hit ENTER: ");
            int number = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (number) {
                case 1:
                    addUser();
                    break;
                case 2:
                    searchUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    listUsers();
                    break;
                case 5:
                    return; // Return to main menu
                case 6:
                    System.out.println("Exiting Program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("\n>>> Invalid Input! Redirecting to User Panel... <<<\n");
            }
        }
    }

    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_Records.txt", true))) {
            writer.write(userName + "\n");
            System.out.println("User added successfully!");
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    public static void searchUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name to search: ");
        String userName = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("user_Records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase(userName)) {
                    System.out.println("User found: " + userName);
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user records: " + e.getMessage());
        }

        if (!found) {
            System.out.println("User not found.");
        }
    }

    public static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name to delete: ");
        String userName = scanner.nextLine();
        boolean found = false;

        File inputFile = new File("user_Records.txt");
        File tempFile = new File("user_Records_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase(userName)) {
                    found = true;
                } else {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing user records: " + e.getMessage());
        }

        if (found) {
            if (tempFile.renameTo(inputFile)) {
                System.out.println("User deleted successfully.");
            } else {
                System.err.println("Error renaming temp file.");
            }
        } else {
            System.out.println("User not found.");
            tempFile.delete(); // Clean up temp file if no changes were made
        }
    }

    public static void listUsers() {
        System.out.println("Listing all users:");
        try (BufferedReader reader = new BufferedReader(new FileReader("user_Records.txt"))) {
            String line;
            int count = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(count + ". " + line);
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No users found.");
        } catch (IOException e) {
            System.err.println("Error reading user records: " + e.getMessage());
        }
    }

    public static void bookPanel() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println(">>> Library Management System - Book Panel <<< ");
            System.out.println("-----------------------------------------------\n");
            System.out.println("> 1. Add Book ");
            System.out.println("> 2. Search Book by ID ");
            System.out.println("> 3. Delete Book by ID ");
            System.out.println("> 4. List All Books ");
            System.out.println("> 5. Open Main Menu ");
            System.out.println("> 6. Close the Program... \n");

            System.out.print("> Enter the number & hit ENTER: ");
            int number = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (number) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    return; // Return to main menu
                case 6:
                    System.out.println("Exiting Program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("\n>>> Invalid Input! Redirecting to Book Panel... <<<\n");
            }
        }
    }

    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Enter book author: ");
        String bookAuthor = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("book_Records.txt", true))) {
            writer.write(bookID + "," + bookTitle + "," + bookAuthor + "\n");
            System.out.println("Book added successfully!");
        } catch (IOException e) {
            System.err.println("Error saving book: " + e.getMessage());
        }
    }

    public static void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book ID to search: ");
        String bookID = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("book_Records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                if (bookDetails[0].equals(bookID)) {
                    System.out.println("Book found: ");
                    System.out.println("ID: " + bookDetails[0]);
                    System.out.println("Title: " + bookDetails[1]);
                    System.out.println("Author: " + bookDetails[2]);
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading book records: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    public static void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book ID to delete: ");
        String bookID = scanner.nextLine();
        boolean found = false;

        File inputFile = new File("book_Records.txt");
        File tempFile = new File("book_Records_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                if (bookDetails[0].equals(bookID)) {
                    found = true;
                } else {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing book records: " + e.getMessage());
        }

        if (found) {
            if (tempFile.renameTo(inputFile)) {
                System.out.println("Book deleted successfully.");
            } else {
                System.err.println("Error renaming temp file.");
            }
        } else {
            System.out.println("Book not found.");
            tempFile.delete(); // Clean up temp file if no changes were made
        }
    }

    public static void listBooks() {
        System.out.println("Listing all books:");
        try (BufferedReader reader = new BufferedReader(new FileReader("book_Records.txt"))) {
            String line;
            int count = 1;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                System.out.println(count + ". ID: " + bookDetails[0] + ", Title: " + bookDetails[1] + ", Author: " + bookDetails[2]);
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No books found.");
        } catch (IOException e) {
            System.err.println("Error reading book records: " + e.getMessage());
        }
    }
}
