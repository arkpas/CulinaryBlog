package arkpas.culinaryblog.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne (targetEntity = User.class, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany (mappedBy = "userDetails", targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    public int getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Set<Comment> getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void addComment (Comment comment) {
        if (comment != null) {
            comments.add(comment);
            comment.setUserDetails(this);
        }
    }

    public void removeComment (Comment comment) {
        comments.remove(comment);
        comment.setUserDetails(null);
    }
}
