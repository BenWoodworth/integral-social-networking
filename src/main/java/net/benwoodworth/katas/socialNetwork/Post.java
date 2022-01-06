package net.benwoodworth.katas.socialNetwork;

import java.util.Objects;

public final class Post {
    private final User user;
    private final String message;

    public Post(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;
        return user.equals(post.user) && message.equals(post.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, message);
    }

    @Override
    public String toString() {
        return "Post{" +
                "user=" + user +
                ", message='" + message + '\'' +
                '}';
    }
}
