/*
 * Course Code: BIT504
 * Name: YI HAN
 * Student Number: 5081696
 * Assessment Number: 2
 */

package com.library;

import java.util.Scanner;

public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);
    private static LibraryManager manager = LibraryManager.getInstance();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Main Menu ---\n1. Book Management\n2. Member Management\n3. Loan Management\n4. Search\n5. Exit");
            System.out.print("Select: ");
            String op = scanner.nextLine();
            if (op.equals("1")) showBookMenu();
            else if (op.equals("2")) showMemberMenu();
            else if (op.equals("3")) showLoanMenu();
            else if (op.equals("4")) showSearchMenu();
            else if (op.equals("5")) break;
        }
    }

    // --- Task 7: Loan Management ---
    private static void showLoanMenu() {
        while (true) {
            System.out.println("\n--- Loan Management ---\na. Check out\nb. Check in\nc. Return");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("c")) break;
            if (choice.equals("a")) checkOutUI();
            else if (choice.equals("b")) checkInUI();
        }
    }

    private static void checkOutUI() {
        System.out.print("Enter Book ID: "); String bId = scanner.nextLine();
        Book b = manager.findBookById(bId);
        System.out.print("Enter Member ID: "); String mId = scanner.nextLine();
        Member m = manager.findMemberById(mId);

        if (b == null || m == null) { System.out.println("Invalid IDs!"); return; }
        if (b.getBorrowerID() != null) { System.out.println("Book already borrowed!"); return; }
        if (m.getAge() < b.getAgeRating()) { System.out.println("Warning: Member too young!"); return; } // 年龄限制

        System.out.print("Confirm borrow '" + b.getTitle() + "'? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            b.setBorrowerID(m.getId());
            b.setBorrowerName(m.getFirstName() + " " + m.getLastName());
            System.out.println("Checkout successful!");
        }
    }

    private static void checkInUI() {
        System.out.print("Enter Book ID to return: ");
        Book b = manager.findBookById(scanner.nextLine());
        if (b != null && b.getBorrowerID() != null) {
            b.setBorrowerID(null);
            b.setBorrowerName(null);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Error: Book not borrowed or not found.");
        }
    }

    // --- Task 8: Search ---
    private static void showSearchMenu() {
        while (true) {
            System.out.println("\n--- Search ---\na. Find Member\nb. Find Book\nc. Return");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("c")) break;
            if (choice.equals("b")) {
                System.out.print("Enter Title part: ");
                String term = scanner.nextLine().toLowerCase();
                for (Book b : manager.getAllBooks()) {
                    if (b.getTitle().toLowerCase().contains(term)) { // 模糊搜索
                        System.out.println("ID: " + b.getId() + " | Title: " + b.getTitle() + 
                            " | Status: " + (b.getBorrowerID() == null ? "Available" : "Borrowed by " + b.getBorrowerName()));
                    }
                }
            }
        }
    }

    private static void showBookMenu() {
        while (true) {
            System.out.println("\n--- Book Management ---\na. Display all\ne. Return");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("e")) break;
            if (choice.equals("a")) manager.displayBooks(manager.getAllBooks());
        }
    }

    private static void showMemberMenu() {
        System.out.println("Member management menu placeholder. Press enter to return.");
        scanner.nextLine();
    }
}
