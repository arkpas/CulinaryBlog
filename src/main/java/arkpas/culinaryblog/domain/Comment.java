package arkpas.culinaryblog.domain;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String text;
    @ManyToOne (cascade = CascadeType.ALL)
    private Recipe recipe;

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getText() {
        return text;
    }
    public Recipe getRecipe() {
        return recipe;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
