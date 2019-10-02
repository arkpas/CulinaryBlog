package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Recipe;

import java.util.List;

public interface RecipeRepository {

    Recipe getRecipe (int id);
    Recipe getRecipe (String name);
    List<Recipe> getRecipes ();
    List<Recipe> searchRecipes (String searchText);
    void saveRecipe (Recipe recipe);
    void updateRecipe (Recipe recipe);
    void removeRecipe(Recipe recipe);
}
