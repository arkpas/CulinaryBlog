package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;

import java.util.List;

public interface RecipeRepository {

    public Recipe getRecipe (int id);
    public Recipe getRecipe (String name);
    public List<Recipe> getRecipes ();
    public List<Recipe> searchRecipes (String searchText);
    public void saveRecipe (Recipe recipe);
    public void updateRecipe (Recipe recipe);


}
