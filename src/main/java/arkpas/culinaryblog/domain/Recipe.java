package arkpas.culinaryblog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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
        return Collections.unmodifiableSet(comments);
    }
    public Set<RecipeCattegory> getCattegories() {
        return Collections.unmodifiableSet(cattegories);
    }
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getImageLink() {
        return imageLink;
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
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    public void setCattegories(Set<RecipeCattegory> cattegories) {
        this.cattegories = cattegories;
    }
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    public void setDateTime () {
        dateTime = LocalDateTime.now();
    }

    public void addComment (Comment comment) {
        comments.add(comment);
    }
    public void removeComment (Comment comment) {
        if (comments.contains(comment)) {
            comments.remove(comment);
            comment.setRecipe(null);
        }
    }
    public void addRecipeCattegory (RecipeCattegory recipeCattegory) { cattegories.add(recipeCattegory); }
    public void removeRecipeCattegory (RecipeCattegory recipeCattegory) {
        if (cattegories.contains(recipeCattegory)) {
            cattegories.remove(recipeCattegory);
            recipeCattegory.setRecipe(null);
        }
    }
    public void addTag (Tag tag) { tags.add(tag); }
    public void removeTag (Tag tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
            tag.setRecipe(null);
        }
    }

    @Override
    public String toString () {
        return "Recipe[id=" + id + ", " + name + "]";
    }
}
