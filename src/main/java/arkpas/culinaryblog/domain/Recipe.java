package arkpas.culinaryblog.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String ingredients;
    private String instruction;
    @OneToMany (mappedBy = "recipe", targetEntity = Comment.class)
    private Set<Comment> comments = new HashSet<>();
    @OneToMany (mappedBy = "recipe", targetEntity = RecipeCattegory.class)
    private Set<RecipeCattegory> cattegories = new HashSet<>();

    public Recipe () {

    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getIngredients() {
        return ingredients;
    }
    public String getInstruction() {
        return instruction;
    }
    public Set<Comment> getComments() {
        return comments;
    }
    public Set<RecipeCattegory> getCattegories() {
        return cattegories;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void addComment (Comment comment) {
        comments.add(comment);
    }
}
