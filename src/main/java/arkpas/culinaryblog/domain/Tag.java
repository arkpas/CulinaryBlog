package arkpas.culinaryblog.domain;

import javax.persistence.*;

@Entity
public class Tag {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne (targetEntity = Recipe.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "recipeId")
    private Recipe recipe;
    private String tagName;

    public Tag () {

    }

    public int getId() {
        return id;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public String getTagName() {
        return tagName;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}

