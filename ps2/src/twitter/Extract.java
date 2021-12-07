package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * <p>
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     *
     * @param tweets list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     * every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        Instant start = null;
        Instant end = null;
        for (int i = 0; i < tweets.size() - 1; i++) {
            //this is the initial stage
            if (start == null && end == null) {
                if (tweets.get(i).getTimestamp().isBefore(tweets.get(i + 1).getTimestamp())) {
                    start = tweets.get(i).getTimestamp();
                    end = tweets.get(i+1).getTimestamp();
                    i++;
                } else {
                    end = tweets.get(i).getTimestamp();
                    start = tweets.get(i+1).getTimestamp();
                    i++;
                }
            }
            assert start != null;
            if (tweets.get(i).getTimestamp().isBefore(start)) {
                start = tweets.get(i).getTimestamp();
            }
            if (tweets.get(i).getTimestamp().isAfter(end)) {
                end = tweets.get(i).getTimestamp();
            }

        }
        assert start != null;
        return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     *
     * @param tweets list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     * A username-mention is "@" followed by a Twitter username (as
     * defined by Tweet.getAuthor()'s spec).
     * The username-mention cannot be immediately preceded or followed by any
     * character valid in a Twitter username.
     * For this reason, an email address like bitdiddle@mit.edu does NOT
     * contain a mention of the username mit.
     * Twitter usernames are case-insensitive, and the returned set may
     * include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        HashSet<String> userNames = new HashSet<>();
        Pattern pattern = Pattern.compile("(\\w+(-*))*@(\\w+(-*))+");
        Matcher matcher = pattern.matcher(tweets.get(0).getText());
        while (matcher.find()){
            if (matcher.group(0).indexOf("@") == 0){
                userNames.add(matcher.group(0));
            }
        }

        return userNames;
    }

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
