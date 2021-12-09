package library;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test suite for Library ADT.
 */
@RunWith(Parameterized.class)
public class LibraryTest {

    /*
     * Note: all the tests you write here must be runnable against any
     * Library class that follows the spec.  JUnit will automatically
     * run these tests against both SmallLibrary and BigLibrary.
     */

    /**
     * Implementation classes for the Library ADT.
     * JUnit runs this test suite once for each class name in the returned array.
     *
     * @return array of Java class names, including their full package prefix
     */
    @Parameters(name = "{0}")
    public static Object[] allImplementationClassNames() {
        return new Object[]{
                "library.SmallLibrary",
                "library.BigLibrary"
        };
    }

    /**
     * Implementation class being tested on this run of the test suite.
     * JUnit sets this variable automatically as it iterates through the array returned
     * by allImplementationClassNames.
     */
    @Parameter
    public String implementationClassName;

    /**
     * @return a fresh instance of a Library, constructed from the implementation class specified
     * by implementationClassName.
     */
    public Library makeLibrary() {
        try {
            Class<?> cls = Class.forName(implementationClassName);
            return (Library) cls.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /*
     * Testing strategy
     * ==================
     *
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */

    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    private final Book book1 = new Book("deeznuts", Arrays.asList("gottem", "smekthan"), 1990);

    private final Book book2 = new Book("book2", Arrays.asList("kek", "rekthan"), 2000);

    private final Book book3 = new Book("book3", Arrays.asList("lek"), 2010);

    @Test
    public void buyBook() {
        Library library = makeLibrary();
        BookCopy buy = library.buy(book1);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        HashSet<BookCopy> bookCopies = new HashSet<>();
        bookCopies.add(buy);
        assertEquals(bookCopies, library.availableCopies(book1));
        assertEquals(true, library.isAvailable(buy));
        assertEquals(bookCopies, library.find("deeznuts"));
    }

    @Test
    public void checkOutAndIn() {
        Library library = makeLibrary();
        BookCopy buy = library.buy(book2);
        library.checkin(buy);
        HashSet<BookCopy> bookCopies = new HashSet<>();
        assertEquals(bookCopies, library.availableCopies(book2));
        assertEquals(false, library.isAvailable(buy));

        bookCopies.add(buy);
        library.checkout(buy);
        assertEquals(bookCopies, library.availableCopies(book2));
        assertEquals(true, library.isAvailable(buy));


        assertEquals(bookCopies, library.allCopies(book2));

        library.lose(buy);

        assertEquals(new HashSet<>(), library.allCopies(book2));
    }

    @Test
    public void allCopies() {
    }

    @Test
    public void lose() {
    }


    @Test
    public void testExampleTest() {
        Library library = makeLibrary();
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals(Collections.emptySet(), library.availableCopies(book));
    }


    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
