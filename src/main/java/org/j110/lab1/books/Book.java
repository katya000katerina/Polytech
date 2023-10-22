package org.j110.lab1.books;

import java.util.Arrays;

public class Book {
    private String title;
    private Publisher publisher;
    private int yearOfPublication;
    private String[] authors;

    public Book(String title, Publisher publisher, int yearOfPublication) { // a constructor for a book with no authors
        setTitle(title);
        setPublisher(publisher);
        setYearOfPublication(yearOfPublication);
    }
    public Book(String title, Publisher publisher, int yearOfPublication, String[] authors) { // a constructor for a book with multiple authors
        this(title, publisher, yearOfPublication);
        setAuthors(authors);
    }
    public Book(String title, Publisher publisher, int yearOfPublication, String author) { // a constructor for a book with one author
        this(title, publisher, yearOfPublication, new String[]{author});
    }
    public String getTitle() {
        return title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public String[] getAuthors() { // the getter returns a copy of the original array so that it cannot be modified from the outside
        return Arrays.copyOf(authors, authors.length);
    }

    public int getNumberOfAuthors(){
        return authors != null ? authors.length : 0;
    }

    public String getAuthorByIndex(int index){
        if (authors == null){
            throw new IllegalArgumentException("The book has no authors");
        }
        if (index >= 0 && index < authors.length){
            return authors[index];
        }
        else throw new IllegalArgumentException("Invalid index");
    }

    public void setTitle(String title) {
        if (title != null){
            this.title = title;
        }
        else throw new IllegalArgumentException("Title cannot be null");
    }

    public void setPublisher(Publisher publisher) {
        if (publisher != null){
            this.publisher = publisher;
        }
        else throw new IllegalArgumentException("Publisher cannot be null");
    }

    public void setYearOfPublication(int yearOfPublication) {
        if (yearOfPublication > 0){
            this.yearOfPublication = yearOfPublication;
        }
        else throw new IllegalArgumentException("Year of publication must be greater than zero");
    }

    public void setAuthors(String[] authors) {
        for (String author : authors) {
            if (author == null) {
                throw new IllegalArgumentException("Author cannot be null");
                }
            }
        this.authors = authors;
    }
    public void print(){
        System.out.printf("Title: %s\nPublisher: %s, %s\nYear of publication: %d\nAuthors: %s",
                title, publisher.getPublisherName(), publisher.getCity(), yearOfPublication, authors != null ? String.join(", ", authors) : "N/A");
    }

    public static void printAll(Book[] books){
        for (Book book : books){
            book.print();
            System.out.println("\n-----------------");
        }
    }
}
