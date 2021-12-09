package library;

import java.util.Objects;

/**
 * BookCopy is a mutable type representing a particular copy of a book that is held in a library's
 * collection.
 */
public class BookCopy {

    private Book book;
    private Condition condition;

    
    public static enum Condition {
        GOOD, DAMAGED
    };

    /**
     * Make a new BookCopy, initially in good condition.
     * @param book the Book of which this is a copy
     */
    public BookCopy(Book book) {
        this.book = new Book(book.getTitle(), book.getAuthors(), book.getYear());
        this.condition= Condition.GOOD;
        checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
        assert book != null;
        assert condition != null;
    }
    
    /**
     * @return the Book of which this is a copy
     */
    public Book getBook() {
        return new Book(book.getTitle(),book.getAuthors(), book.getYear());
    }

    /**
     * @return the condition of this book copy
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Set the condition of a book copy.  This typically happens when a book copy is returned and a librarian inspects it.
     * @param condition the latest condition of the book copy
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
        checkRep();
    }

    /**
     * @return human-readable representation of this book that includes book.toString()
     *    and the words "good" or "damaged" depending on its condition
     */
    @Override
    public String toString() {
        return "BookCopy{" +
                "book=" + book +
                ", condition=" + condition +
                '}';
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 37 + this.book.hashCode();
        hashCode = hashCode * 37 + this.condition.hashCode();
        return hashCode;
    }

    @Override
    public boolean equals(Object that) {
        if (this==that){
            return true;
        }
        if (that==null){
            return false;
        }
        if (! (that instanceof BookCopy)) return false;
        Book other = (Book) that;
        return Objects.equals(other.getTitle(), this.book.getTitle())
                && other.getAuthors().equals(this.book.getAuthors())
                && other.getYear()==(this.book.getYear());
    }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
