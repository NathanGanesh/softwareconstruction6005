package library;

import java.util.List;

/**
 * Book is an immutable type representing an edition of a book -- not the physical object,
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {

    private String title;
    private List<String> authors;
    private int year;





    /**
     * Make a Book.
     *
     * @param title   Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain
     *                at least one non-space character.
     * @param year    Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative.
     */
    public Book(String title, List<String> authors, int year) {
        this.title = checkIfNotBlankAndBiggerThenLengthOne(title);
        this.authors = checkAuthorsList(authors);
        this.year = checkIfNotNegative(year);
        checkRep();
    }

    public List<String> checkAuthorsList(List<String> authors) {
        for (String author : authors) {
           checkIfNotBlankAndBiggerThenLengthOne(author);
        }
        return authors;
    }

    public int checkIfNotNegative(int valueToCheck){
        if (valueToCheck<=0){
            return valueToCheck;
        }else{
            throw new RuntimeException("Value is negative");
        }
    }

    public String checkIfNotBlankAndBiggerThenLengthOne(String stringToCheck) {
        if (!stringToCheck.isBlank()) {
            if (stringToCheck.length() > 1) {
                return stringToCheck;
            }
        }
        throw new RuntimeException("String is not allowed");
    }



    // assert the rep invariant
    private void checkRep() {
        assert year>=0;
        assert checkIfNotBlankAndBiggerThenLengthOne(title) != null;
        assert !checkAuthorsList(authors).isEmpty();
        throw new RuntimeException("not implemented yet");
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    /**
     * @return human-readable representation of this book that includes its title,
     * authors, and publication year
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", year=" + year +
                '}';
    }
    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
     @Override
     public boolean equals(Object that) {
         if (! (that instanceof Book)) return false;
         Book other = (Book) that;
         return other.year == this.year
                 && other.title.equals(this.title)
                 && other.authors.equals(this.authors);
     }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
