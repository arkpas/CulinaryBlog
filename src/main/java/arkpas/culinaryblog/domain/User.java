package arkpas.culinaryblog.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull (message = "Pole nie może być puste")
    @Size(min=3, message="Nazwa jest za krótka - minimum 3 znaki")
    @Size(max=16, message="Nazwa jest za długa - maksymalnie 16 znaków")
    private String username;
    @NotNull (message = "Pole nie może być puste")
    @Size(min=3, message = "Hasło jest za krótkie - minimum 3 znaki")
    private String password;
    private boolean active = true;
    @OneToOne (mappedBy = "user", targetEntity = UserDetails.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetails userDetails;

    public User () {
        this.setUserDetails(new UserDetails());
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserDetails(UserDetails userDetails) {
        if (this.userDetails != null)
            this.userDetails.setUser(null);
        if (userDetails != null)
            userDetails.setUser(this);
        this.userDetails = userDetails;
    }
}
