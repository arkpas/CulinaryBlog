package arkpas.culinaryblog.domain;

import javax.persistence.*;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    @OneToOne (targetEntity = Recipe.class)
    @MapsId
    @JoinColumn (name = "recipe_id")
    private Recipe recipe;
    private double rating = 0;
    private int votes = 0;


    public int getRecipeId() {
        return recipeId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public double getRating() {
        return rating;
    }

    public int getVotes() {
        return votes;
    }


    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
