package arkpas.culinaryblog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column (length = 1000)
    private String ingredients;
    @Column(length = 5000)
    private String instruction;
    @OneToMany (mappedBy = "recipe", targetEntity = Comment.class)
    private Set<Comment> comments = new HashSet<>();
    @OneToMany (mappedBy = "recipe", targetEntity = RecipeCattegory.class)
    private Set<RecipeCattegory> cattegories = new HashSet<>();
    @OneToMany (mappedBy = "recipe", targetEntity = Tag.class)
    private Set<Tag> tags = new HashSet<>();
    private LocalDateTime dateTime;
    private String imageLink;


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
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getImageLink() {
        return imageLink;
    }
    public Set<Tag> getTags() {
        return tags;
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
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void addComment (Comment comment) {
        comments.add(comment);
    }
    public void setDateTime () {
        dateTime = LocalDateTime.now();
    }
}
