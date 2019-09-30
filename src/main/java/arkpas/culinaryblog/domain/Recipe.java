package arkpas.culinaryblog.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=3, message="Nazwa jest za krótka!")
    private String name;

    @Column (length = 1000)
    @NotBlank (message = "Pole nie może być puste!")
    private String ingredients;

    @Column(length = 5000)
    @NotBlank(message = "Pole nie może być puste!")
    private String instruction;

    @OneToMany (mappedBy = "recipe", targetEntity = Comment.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany (mappedBy = "recipe", targetEntity = RecipeCattegory.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<RecipeCattegory> cattegories = new HashSet<>();

    @OneToMany (mappedBy = "recipe", targetEntity = Tag.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    private LocalDateTime dateTime;
    private String imageLink;

    @OneToOne (mappedBy = "recipe", targetEntity = Rate.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Rate rate;


    public Recipe () {
        this.setRate(new Rate());
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
    public Rate getRate() {
        return rate;
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
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setDateTime () {
        dateTime = LocalDateTime.now();
    }

    public void setRate(Rate rate) {
        if (this.rate != null)
            this.rate.setRecipe(null);
        if (rate != null)
            rate.setRecipe(this);
        this.rate = rate;

    }

    public void addComment (Comment comment) {
        if (comment != null) {
            comments.add(comment);
            comment.setRecipe(this);
        }
    }

    public void addRecipeCattegory (RecipeCattegory recipeCattegory) {
        if (recipeCattegory != null) {
            cattegories.add(recipeCattegory);
            recipeCattegory.setRecipe(this);
        }
    }

    public void removeAllRecipeCattegories () {
        for (RecipeCattegory rc : cattegories) {
            rc.setRecipe(null);
        }
        cattegories.clear();
    }
    public void addTag (Tag tag) {
        if (tag != null) {
            tags.add(tag);
            tag.setRecipe(this);
        }
    }

    public void removeAllTags () {
        for (Tag t : tags) {
            t.setRecipe(null);
        }
        tags.clear();
    }

    @Override
    public String toString () {
        return "Recipe[id=" + id + ", " + name + "]";
    }
}
