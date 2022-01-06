package net.benwoodworth.katas.socialNetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {
    private SocialNetwork socialNetwork;

    private final User alice = new User("Alice");

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
        var timeline = socialNetwork.viewTimeline(alice);

        // Then
        var expectedTimeline = List.of(
                new Post("I love the weather today.")
        );
        assertEquals(expectedTimeline, timeline);
    }
}
