package net.benwoodworth.katas.socialNetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {
    private SocialNetwork socialNetwork;

    private final User alice = new User("Alice");
    private final User bob = new User("Bob");

    @BeforeEach
    void setUp() {
        socialNetwork = new SocialNetwork();
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
                new Post(null, "I love the weather today.")
        );
        assertEquals(expectedTimeline, timeline);
    }

    // TODO Show time elapsed since posted?
    @Test
    @DisplayName("Alice views Bob's timeline.")
    void testTimeline() {
        // Given
        socialNetwork.publish(bob, "Darn! We lost!");
        socialNetwork.publish(bob, "Good game though.");

        // When
        var timeline = socialNetwork.viewTimeline(alice, bob);

        // Then
        var expectedTimeline = List.of(
                new Post(bob, "Good game though."),
                new Post(bob, "Darn! We lost!")
        );
        assertEquals(expectedTimeline, timeline);
    }
}
