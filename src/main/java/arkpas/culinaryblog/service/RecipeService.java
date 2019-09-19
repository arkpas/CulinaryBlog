package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Comment;
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
        recipe.setDateTime();
        recipeRepository.saveRecipe(recipe);
    }

    public void updateRecipe (Recipe recipe) {
        if (recipe == null)
            return;

        Recipe originalRecipe = this.getRecipe(recipe.getId());
        if (originalRecipe == null)
            return;

        recipe.setComments(originalRecipe.getComments());
        recipe.setCattegories(originalRecipe.getCattegories());
        recipe.setTags(originalRecipe.getTags());
        recipe.setDateTime(originalRecipe.getDateTime());

        recipeRepository.updateRecipe(recipe);
    }

    public Set<Recipe> searchRecipes (String searchText) {
        return new HashSet<>(recipeRepository.searchRecipes(searchText));

    }

    public List<Recipe> getRecipes () {
        return recipeRepository.getRecipes();
    }

}