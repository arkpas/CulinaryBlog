package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Recipe addRecipe (Recipe recipe) {
        if (recipe == null)
            return null;
        recipe.setDateTime();
        recipeRepository.saveRecipe(recipe);
        return recipe;
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
        if (recipe == null)
            return null;

        Recipe originalRecipe = this.getRecipe(recipe.getId());
        if (originalRecipe == null)
            return null;

        originalRecipe.setName(recipe.getName());
        originalRecipe.setIngredients(recipe.getIngredients());
        originalRecipe.setInstruction(recipe.getInstruction());
        originalRecipe.setImageLink(recipe.getImageLink());
        updateRecipe(originalRecipe);

        return originalRecipe;
    }

    public boolean isDuplicate(Recipe recipe) {
        if (recipe == null)
            return false;
        Recipe duplicate = this.getRecipe(recipe.getName());
        if (duplicate != null && duplicate.getId() != recipe.getId())
            return true;
        else
            return false;
    }
}
