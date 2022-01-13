package net.benwoodworth.katas.socialNetwork;

import java.time.Clock;
import java.util.*;
import java.util.stream.Collectors;

public final class SocialNetwork {
    private final Clock snClock;

    public SocialNetwork(Clock clock) {
        this.snClock = clock;
    }

    private final Map<User, Profile> profiles = new HashMap<>();

    private Profile getProfile(User user) {
        return profiles.computeIfAbsent(user, Profile::new);
    }

    public void publish(User user, String message) {
        getProfile(user).posts.add(new Post(user, message, snClock.instant()));
    }

    public List<Post> viewTimeline(User viewer, User alice) {
        var timeline = new ArrayList<>(getProfile(alice).posts);
        Collections.reverse(timeline);
        return timeline;
    }

    public void follow(User user, User toFollow) {
        getProfile(user).followedUsers.add(toFollow);
    }

    public List<Post> viewWall(User user) {
        return getProfile(user).followedUsers.stream() // Get followed users
                .flatMap(u -> getProfile(u).posts.stream()) // Get their posts
                .sorted(Comparator.comparing(Post::getTime).reversed()) // Sort by newest
                .collect(Collectors.toList());
    }

    public void unfollow(User user, User toUnFollow) {
        getProfile(user).followedUsers.remove(toUnFollow);
    }

    private static class Profile {
        final List<Post> posts = new ArrayList<>();
        final Set<User> followedUsers = new HashSet<>();

        Profile(User user) {
            followedUsers.add(user); // Users follow themselves, so their wall shows their own posts
        }
    }
}
