package com.example.demo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long bookId;
    private String title;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = { @JoinColumn(name = "bookId") },
            inverseJoinColumns = { @JoinColumn(name = "authorId") })
    private Set<Author> authors = new HashSet<Author>(0);

    public Book() {
    }

    public Book(long bookId, String title, Set<Author> authors) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Set<Author> getAuthors() {
//        return authors;
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public boolean hasAuthors(Author author) {
        for (Author bookAuthor: getAuthors()) {
            if (bookAuthor.getAuthorId() == author.getAuthorId()) {
                return true;
            }
        }
        return false;
    }
}
