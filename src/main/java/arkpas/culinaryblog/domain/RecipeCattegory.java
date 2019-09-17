package arkpas.culinaryblog.domain;

import javax.persistence.*;

@Entity
public class RecipeCattegory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne (targetEntity = Recipe.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "recipe_id")
    private Recipe recipe;
    @ManyToOne (targetEntity = Cattegory.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "cattegory_id")
    private Cattegory cattegory;

    public RecipeCattegory () {

    }

    public int getId() {
        return id;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public Cattegory getCattegory() {
        return cattegory;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public void setCattegory(Cattegory cattegory) {
        this.cattegory = cattegory;
    }
}
