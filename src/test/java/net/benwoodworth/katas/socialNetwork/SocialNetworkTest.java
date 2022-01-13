package net.benwoodworth.katas.socialNetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocialNetworkTest {
    private SocialNetwork socialNetwork;

    private final User alice = new User("Alice");
    private final User bob = new User("Bob");
    private final User charlie = new User("Charlie");

    private MockClock clock;
    Instant testStartInstant;

    @BeforeEach
    void setUp() {
        testStartInstant = Instant.now();
        clock = new MockClock(testStartInstant, ZoneId.systemDefault());
        socialNetwork = new SocialNetwork(clock);
    }

    @Test
    @DisplayName("Alice publishes messages to her personal timeline.")
    void testPublishing() {
        // Given
        socialNetwork.publish(alice, "I love the weather today.");

        // When
        var timeline = socialNetwork.viewTimeline(alice, alice);

        // Then
        var expectedTimeline = List.of(
                new Post(alice, "I love the weather today.", testStartInstant)
        );
        assertEquals(expectedTimeline, timeline);
    }

    // TODO Show time elapsed since posted?
    @Test
    @DisplayName("Alice views Bob's timeline.")
    void testTimeline()     {
        // Given
        socialNetwork.publish(bob, "Darn! We lost!");
        clock.advance(Duration.ofMinutes(1));
        socialNetwork.publish(bob, "Good game though.");

        // When
        var timeline = socialNetwork.viewTimeline(alice, bob);

        // Then
        var expectedTimeline = List.of(
                new Post(bob, "Good game though.", testStartInstant.plus(Duration.ofMinutes(1))),
                new Post(bob, "Darn! We lost!", testStartInstant)
        );
        assertEquals(expectedTimeline, timeline);
    }


    @Test
    @DisplayName("Charlie can follow Alice and Bob, and he views an aggregated list of all timelines.")
    void testFollowing() {
        // Given
        socialNetwork.publish(alice, "I love the weather today.");
        clock.advance(Duration.ofMinutes(1));
        socialNetwork.publish(bob, "Darn! We lost!");
        clock.advance(Duration.ofMinutes(1));
        socialNetwork.publish(bob, "Good game though.");
        clock.advance(Duration.ofMinutes(3));
        socialNetwork.publish(charlie, "I'm in New York today! Anyone wants to have a coffee?");

        // When
        socialNetwork.follow(charlie, alice);
        socialNetwork.follow(charlie, bob);
        var wall = socialNetwork.viewWall(charlie);

        // Then
        var expectedWall = List.of(
                new Post(charlie, "I'm in New York today! Anyone wants to have a coffee?", testStartInstant.plus(Duration.ofMinutes(5))),
                new Post(bob, "Good game though.", testStartInstant.plus(Duration.ofMinutes(2))),
                new Post(bob, "Darn! We lost!", testStartInstant.plus(Duration.ofMinutes(1))),
                new Post(alice, "I love the weather today.", testStartInstant)
        );
        assertEquals(expectedWall, wall);
    }

    @Test
    @DisplayName("Alice can follow and unfollow Bob. Alice should not see Bob's timeline")
    void testUnfollowing() {
        //Given
        socialNetwork.follow(alice, bob);
        socialNetwork.publish(bob, "Good game though.");

        //When
        socialNetwork.unfollow(alice, bob);

        //Then
        var wall = socialNetwork.viewWall(alice);
        assertEquals(List.of(), wall);
    }
}
