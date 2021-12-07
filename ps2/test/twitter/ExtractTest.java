package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.*;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");

    private static final Instant d3 = Instant.parse("2018-03-13T11:00:00Z");

    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);

    private static final Tweet tweetWithUser = new Tweet(3, "deeznuts", "hello @Pikcha not so good with your btw send me a message on gmail deeznuts@gmail.com", d3);

    private static final Tweet tweetWithUsers = new Tweet(4, "deeznuts", "hello @Pikcha not so good with your btw send me a message on gmail deeznuts@gmail.com but wait i do like @hatsunaMiku, and @Pokicha", d3);

    private static final Tweet tweetWithUsersAndAnnoying = new Tweet(4, "deeznuts", "hello @Pikcha not so good with your btw send me a message on gmail deeznuts@gmail.com but wait i do like @hatsunaMiku, and @Pokicha,@trekachi, maybe @smak_tYtian", d3);

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
//
//    @Test
//    public void testGetMentionedUsersNoMention() {
//       Instant d9 = Instant.parse("2019-03-13T11:00:00:00Z");
//
//      Tweet tweet6 = new Tweet(5, "alyssa", "is it reasonable to talk about rivest so much?", d9);
//
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6));
//
//        assertTrue("expected empty set", mentionedUsers.isEmpty());
//    }


    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

//    @Test
//    public void testGetMentionedUser(){
//
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithUsers));
//
//        assertEquals(mentionedUsers.contains("Pikcha"), "found pikcha" );
//    }


    @Test
    public void testGetMentionedUsers(){
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithUsers));

        assertEquals(
                mentionedUsers.containsAll(Arrays.asList("Pikcha", "Pokicha", "hatsunaMiku")), "found the tagget users" );
    }


    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
