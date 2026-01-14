/*
 * Course Code: BIT504
 * Name: YI HAN
 * Student Number: 5081696
 * Assessment Number: 2
 */


package com.library;

import java.util.*;
import java.io.*;

public class LibraryManager {
    private static LibraryManager instance;
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    private LibraryManager() {
        loadData("books.txt", true);
        loadData("members.txt", false);
    }

    public static LibraryManager getInstance() {
        if (instance == null) instance = new LibraryManager();
        return instance;
    }

    private void loadData(String fileName, boolean isBook) {
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                if (isBook && d.length == 7) {
                    books.add(new Book(d[0], d[1], d[2], d[3], d[4], d[5], Integer.parseInt(d[6].trim())));
                } else if (!isBook && d.length == 4) {
                    members.add(new Member(d[0], d[1], d[2], Integer.parseInt(d[3].trim())));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading " + fileName + ": " + e.getMessage());
        }
    }

    public Book findBookById(String id) {
        for (Book b : books) if (b.getId().equalsIgnoreCase(id)) return b;
        return null;
    }

    public Member findMemberById(String id) {
        for (Member m : members) if (m.getId().equalsIgnoreCase(id)) return m;
        return null;
    }

    // Task 5 & 6: Tabular display
    public void displayBooks(List<Book> list) {
        System.out.format("%-5s | %-20s | %-25s | %-15s | %-5s%n", "ID", "ISBN", "Title", "Author", "Age");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Book b : list) {
            System.out.format("%-5s | %-20s | %-25s | %-15s | %-5d%n", b.getId(), b.getIsbn(), b.getTitle(), b.getAuthor(), b.getAgeRating());
        }
    }

    public List<Book> getAllBooks() { return books; }
    public List<Member> getAllMembers() { return members; }
}