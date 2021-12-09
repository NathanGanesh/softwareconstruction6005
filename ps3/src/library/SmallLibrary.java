package library;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SmallLibrary represents a small collection of books, like a single person's home collection.
 */
public class SmallLibrary implements Library {

    // This rep is required! 
    // Do not change the types of inLibrary or checkedOut, 
    // and don't add or remove any other fields.
    // (BigLibrary is where you can create your own rep for
    // a Library implementation.)

    // rep
    private Set<BookCopy> inLibrary;
    private Set<BookCopy> checkedOut;

    // rep invariant:
    //    the intersection of inLibrary and checkedOut is the empty set
    //
    // abstraction function:
    //    represents the collection of books inLibrary union checkedOut,
    //      where if a book copy is in inLibrary then it is available,
    //      and if a copy is in checkedOut then it is checked out


    public SmallLibrary(Set<BookCopy> inLibrary, Set<BookCopy> checkedOut) {
        this.inLibrary = inLibrary;
        this.checkedOut = checkedOut;
        checkRep();
    }

    // assert the rep invariant
    private void checkRep() {
        assert inLibrary.stream().anyMatch(checkedOut::contains);
    }

    @Override
    public BookCopy buy(Book book) {
        BookCopy bookCopy = new BookCopy(book);
        inLibrary.add(bookCopy);
        return bookCopy;
    }

    @Override
    public void checkout(BookCopy copy) {
        if (isAvailable(copy)) {
            checkedOut.remove(copy);
            inLibrary.add(copy);

        }
    }

    @Override
    public void checkin(BookCopy copy) {
        if (checkedOut.contains(copy)) {
            inLibrary.remove(copy);
            checkedOut.add(copy);
        }
    }

    @Override
    public boolean isAvailable(BookCopy copy) {
        return inLibrary.contains(copy);
    }

    @Override
    public Set<BookCopy> allCopies(Book book) {
        Set<BookCopy> allCopiesSet = inLibrary.stream().filter(p ->
                p.getBook().getTitle().equals(book.getTitle())
        ).collect(Collectors.toSet());

        allCopiesSet.addAll(checkedOut.stream().filter(p -> p.getBook().getTitle().equals(book.getTitle())).collect(Collectors.toSet()));
        return allCopiesSet;
    }

    @Override
    public Set<BookCopy> availableCopies(Book book) {
        return inLibrary.stream().filter(p ->
                p.getBook().getTitle().equals(book.getTitle())
        ).collect(Collectors.toSet());
    }

    @Override
    public List<Book> find(String query) {
        List<Book> inlibraryQuery = new ArrayList<>();

        inLibrary.stream().map(p -> {
            if (p.getBook().getAuthors().contains(query) || p.getBook().getTitle().equals(query)) {
                inlibraryQuery.add(p.getBook());
                return true;
            } else {
                return false;
            }
        });


        checkedOut.stream().map(p ->
                {
                    if (p.getBook().getTitle().equals(query) || p.getBook().getAuthors().contains(query)) {
                        inlibraryQuery.add(p.getBook());
                        return true;
                    } else {
                        return false;
                    }
                }
        );


        return inlibraryQuery;
    }

    @Override
    public void lose(BookCopy copy) {
        throw new RuntimeException("not implemented yet");
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    //

    @Override
    public int hashCode() {
        return super.hashCode();
    }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
