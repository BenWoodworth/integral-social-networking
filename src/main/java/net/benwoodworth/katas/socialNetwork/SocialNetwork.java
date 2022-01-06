package net.benwoodworth.katas.socialNetwork;

import java.util.*;

public final class SocialNetwork {
    private final Map<User, Profile> profiles = new HashMap<>();

    private Profile getProfile(User user) {
        return profiles.computeIfAbsent(user, (key) -> new Profile());
    }

    public void publish(User user, String message) {
        getProfile(user).posts.add(new Post(message));
    }

    public List<Post> viewTimeline(User alice) {
        var timeline = new ArrayList<>(getProfile(alice).posts);
        Collections.reverse(timeline);
        return timeline;
    }

    private static class Profile {
        final List<Post> posts = new ArrayList<>();
    }
}
