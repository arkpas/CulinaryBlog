package arkpas.culinaryblog.domain;

import javax.persistence.*;
import java.util.Collections;
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

    @OneToMany (mappedBy = "userDetails", targetEntity = UserRate.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<UserRate> userRates = new HashSet<>();

    public int getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(comments);
    }
    public Set<UserRate> getUserRates() {
        return Collections.unmodifiableSet(userRates);
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void addComment (Comment comment) {
        if (comment != null) {
            comments.add(comment);
            comment.setUserDetails(this);
        }
    }
}
