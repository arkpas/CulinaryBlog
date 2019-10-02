package arkpas.culinaryblog.integrationTests.repositoryIntegrationTests;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest (properties = "culinaryblog.starter.enabled=false")
public class RecipeRepositoryIntegrationTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void getRecipeShouldReturnNullIfRecipeWithCertainNameIsNotFound () {
        assertNull(recipeRepository.getRecipe("i do not exist"));
    }

    @Test
    @Transactional
    public void getRecipeShouldReturnObjectIfRecipeWithCertainNameIsFound () {
        String name = "i exist";
        Recipe recipe = createRecipe(name);
        entityManager.persist(recipe);

        assertEquals(name, recipeRepository.getRecipe(name).getName());

    }

    @Test
    @Transactional
    public void getRecipesShouldReturnAllRecipes () {
        int amount = 10;
        for (int i = 0; i < amount; i++) {
            entityManager.persist(createRecipe("recipe" + i));
        }

        assertEquals(amount, recipeRepository.getRecipes().size());
    }

    @Test
    @Transactional
    public void searchRecipesShouldReturnRecipesContainingCertainStringInName () {
        String searchText = "bar";
        int amount = 5;
        for (int i = 0; i < amount; i++) {
            entityManager.persist(createRecipe("foo" + searchText + i));
        }
        for (int i = 0; i < amount; i++) {
            entityManager.persist(createRecipe("baar" + i));
        }

        assertEquals(amount, recipeRepository.searchRecipes(searchText).size());
    }

    @Test
    @Transactional
    public void searchRecipesShouldNotBeCaseSensitive () {
        String searchText = "bar".toLowerCase();

        int amount = 3;
        for (int i = 0; i < amount; i++) {
            entityManager.persist(createRecipe("foo" + searchText + i));
        }
        for (int i = 0; i < amount; i++) {
            entityManager.persist(createRecipe("baar" + i));
        }
        searchText = searchText.toUpperCase();
        assertEquals(amount, recipeRepository.searchRecipes(searchText).size());
    }

    private Recipe createRecipe (String name) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setIngredients("ingredients");
        recipe.setInstruction("instruction");
        return recipe;
    }
}
