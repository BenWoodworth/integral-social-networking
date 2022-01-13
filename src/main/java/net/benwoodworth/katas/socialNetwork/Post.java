package net.benwoodworth.katas.socialNetwork;

import java.time.Instant;
import java.util.Objects;

public final class Post {
    private final User user;
    private final String message;
    private final Instant time;

    public Post(User user, String message, Instant time) {
        this.user = user;
        this.message = message;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        return user.equals(post.user) && message.equals(post.message) && time.equals(post.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, message, time);
    }

    @Override
    public String toString() {
        return "Post{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
