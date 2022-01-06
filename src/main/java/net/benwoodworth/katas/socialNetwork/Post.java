package net.benwoodworth.katas.socialNetwork;

public final class Post {
    private final String message;

    public Post(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;
        return message.equals(post.message);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                '}';
    }
}
