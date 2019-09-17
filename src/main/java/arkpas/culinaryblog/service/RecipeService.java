package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
        recipeRepository.saveRecipe(recipe);
    }

    public void updateRecipe (Recipe recipe) {
        recipeRepository.updateRecipe(recipe);
    }

    public List<Recipe> searchRecipes (String searchText) {
        return recipeRepository.searchRecipes(searchText);
    }

    public List<Recipe> getRecipes () {
        return recipeRepository.getRecipes();
    }

}
