package arkpas.culinaryblog.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String ingredients;
    private String instruction;
    @OneToMany (mappedBy = "recipe")
    private List<Comment> comments = new ArrayList<>();

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
    public List<Comment> getComments() {
        return comments;
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
