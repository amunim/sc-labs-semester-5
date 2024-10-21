/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;
import static org.junit.Assert.*;
import org.junit.Test;

import java.time.Instant;
import java.util.*;

public class SocialNetworkTest {

    // Test for guessFollowsGraph()
    @Test
    public void testEmptyListOfTweets() {
        List<Tweet> tweets = new ArrayList<>();
        Map<String, Set<String>> result = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testTweetsWithoutMentions() {
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "Amunim", "boop beep!", Instant.now()),
            new Tweet(2, "Zain", "locolocoloco", Instant.now())
        );
        Map<String, Set<String>> result = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleMention() {
        List<Tweet> tweets = Collections.singletonList(
            new Tweet(1, "Ernie", "Hey @Bert!", Instant.now())
        );
        Map<String, Set<String>> result = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals(2, result.size());
        assertTrue(result.get("Ernie").contains("Bert"));
        assertTrue(result.containsKey("Bert"));
    }

    @Test
    public void testMultipleMentions() {
        List<Tweet> tweets = Collections.singletonList(
            new Tweet(1, "Ernie", "Hello @Bert and @Alice!", Instant.now())
        );
        Map<String, Set<String>> result = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals(3, result.size());
        assertTrue(result.get("Ernie").contains("Bert"));
        assertTrue(result.get("Ernie").contains("Alice"));
        assertTrue(result.containsKey("Bert"));
        assertTrue(result.containsKey("Alice"));
    }

    @Test
    public void testMultipleTweetsFromOneUser() {
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "Ernie", "Hey @Bert!", Instant.now()),
            new Tweet(2, "Ernie", "Hello @Alice!", Instant.now())
        );
        Map<String, Set<String>> result = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals(3, result.size());
        assertTrue(result.get("Ernie").contains("Bert"));
        assertTrue(result.get("Ernie").contains("Alice"));
        assertTrue(result.containsKey("Bert"));
        assertTrue(result.containsKey("Alice"));
    }

    // Test for influencers()
    @Test
    public void testEmptyGraphForInfluencers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> result = SocialNetwork.influencers(followsGraph);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleUserWithoutFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("Alice", new HashSet<>());
        List<String> result = SocialNetwork.influencers(followsGraph);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleInfluencer() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("Alice", new HashSet<>(Arrays.asList("Bob")));
        followsGraph.put("Bob", new HashSet<>());
        
        List<String> result = SocialNetwork.influencers(followsGraph);
        assertEquals(Collections.singletonList("Bob"), result);
    }

    @Test
    public void testMultipleInfluencers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("Alice", new HashSet<>(Arrays.asList("Bob", "Charlie")));
        followsGraph.put("Bob", new HashSet<>());
        followsGraph.put("Charlie", new HashSet<>());
        
        List<String> result = SocialNetwork.influencers(followsGraph);
        assertEquals(Arrays.asList("Bob", "Charlie"), result);
    }

    @Test
    public void testTiedInfluence() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("Alice", new HashSet<>(Arrays.asList("Bob")));
        followsGraph.put("Bob", new HashSet<>(Arrays.asList("Alice")));
        followsGraph.put("Charlie", new HashSet<>(Arrays.asList("Alice", "Bob"))); 

        List<String> result = SocialNetwork.influencers(followsGraph);
        assertEquals(Arrays.asList("Alice", "Bob"), result); 
    }
}
