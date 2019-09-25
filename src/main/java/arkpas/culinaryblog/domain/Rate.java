package arkpas.culinaryblog.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany (targetEntity = UserRate.class, mappedBy = "rate", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRate> userRates = new HashSet<>();


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
    public Set<UserRate> getUserRates() {
        return Collections.unmodifiableSet(userRates);
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


    public void addUserRate (UserRate userRate) {
        if (userRate != null) {
            userRates.add(userRate);
            userRate.setRate(this);
        }
    }

    public void removeUserRate (UserRate userRate) {
        if (userRates.contains(userRate)) {
            userRates.remove(userRate);
            userRate.setRate(null);
        }
    }

    public void calculateRating () {
        votes = userRates.size();
        rating = userRates.stream().mapToInt(UserRate::getRateValue).sum() / votes;
    }
}
