package arkpas.culinaryblog.domain;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne (targetEntity = Recipe.class)
    @JoinColumn (name = "recipe_id")
    private Recipe recipe;

    @ManyToOne (targetEntity = UserDetails.class, fetch = FetchType.EAGER, cascade = {})
    @JoinColumn (name = "user_id")
    private UserDetails userDetails;

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
