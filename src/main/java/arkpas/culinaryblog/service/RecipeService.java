package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Rate;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;


    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }




    public Recipe getRecipe (int id) {
        return recipeRepository.getRecipe(id);
    }

    public Recipe getRecipe (String name) {
        return recipeRepository.getRecipe(name);

    }

    public void addRecipe (Recipe recipe) {

        Rate rate = new Rate();

        recipe.setRate(rate);
        recipe.setDateTime();

        recipeRepository.saveRecipe(recipe);
    }

    public void updateRecipe (Recipe recipe) {
        recipeRepository.updateRecipe(recipe);
    }

    public void deleteRecipe (Recipe recipe) {
        recipeRepository.removeRecipe(recipe);
    }

    public Set<Recipe> searchRecipes (String searchText) {
        return new HashSet<>(recipeRepository.searchRecipes(searchText));
    }

    public List<Recipe> getRecipes () {
        return recipeRepository.getRecipes();
    }

    public Recipe updateRecipePartially(Recipe recipe) {
        Recipe originalRecipe = this.getRecipe(recipe.getId());
        if (originalRecipe != null) {
            originalRecipe.setName(recipe.getName());
            originalRecipe.setIngredients(recipe.getIngredients());
            originalRecipe.setInstruction(recipe.getInstruction());
            originalRecipe.setImageLink(recipe.getImageLink());
            updateRecipe(originalRecipe);

        }
        return originalRecipe;
    }

    public boolean isDuplicate(Recipe recipe) {
        Recipe duplicate = getRecipe(recipe.getName());
        if (duplicate != null && duplicate.getId() != recipe.getId())
            return true;
        else
            return false;
    }
}
