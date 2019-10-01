package arkpas.culinaryblog.serviceUnitTests;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.RecipeCattegory;
import arkpas.culinaryblog.domain.Tag;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import arkpas.culinaryblog.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    private RecipeService recipeService;

    @Before
    public void setup () {
        recipeService = new RecipeService(recipeRepository);
        doNothing().when(recipeRepository).saveRecipe(any(Recipe.class));
    }

    //addRecipe method tests

    @Test
    public void addRecipeShouldReturnNullWhenArgumentIsNull () {
        assertNull(recipeService.addRecipe(null));
    }

    @Test
    public void addRecipeShouldReturnRecipeObjectOnSuccess () {
        assertNotNull(recipeService.addRecipe(new Recipe()));
    }

    @Test
    public void addRecipeShouldSetCurrentDateTimeInRecipeObject () {
        Recipe recipe = new Recipe();
        recipeService.addRecipe(recipe);
        assertNotNull(recipe.getDateTime());
    }

    //updateRecipePartially method tests

    @Test
    public void updateRecipePartiallyShouldReturnNullWhenArgumentIsNull () {
        assertNull(recipeService.updateRecipePartially(null));
    }

    @Test
    public void updateRecipePartiallyShouldReturnNullWhenRecipeWithGivenIdDoesNotExist () {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        doReturn(null).when(recipeRepository).getRecipe(1);

        assertNull(recipeService.updateRecipePartially(recipe));
    }

    @Test
    public void updateRecipePartiallyShouldUpdateCertainValuesInOldObject () {
        Recipe oldRecipe = new Recipe();
        oldRecipe.setId(1);
        oldRecipe.setName("oldName");
        oldRecipe.setIngredients("oldIngredients");
        oldRecipe.setInstruction("oldInstruction");
        oldRecipe.setImageLink("oldLink");

        doReturn(oldRecipe).when(recipeRepository).getRecipe(1);

        Recipe newRecipe = new Recipe();
        newRecipe.setId(1);
        newRecipe.setName("newName");
        newRecipe.setIngredients("newIngredients");
        newRecipe.setInstruction("newInstruction");
        newRecipe.setImageLink("newLink");

        recipeService.updateRecipePartially(newRecipe);

        assertEquals(newRecipe.getName(), oldRecipe.getName());
        assertEquals(newRecipe.getIngredients(), oldRecipe.getIngredients());
        assertEquals(newRecipe.getInstruction(), oldRecipe.getInstruction());
        assertEquals(newRecipe.getImageLink(), oldRecipe.getImageLink());
    }

    @Test
    public void updateRecipePartiallyShouldNotChangeCollectionsInRecipe () {
        Recipe oldRecipe = new Recipe();
        oldRecipe.setId(1);

        doReturn(oldRecipe).when(recipeRepository).getRecipe(1);

        Recipe newRecipe = new Recipe();
        newRecipe.setId(1);
        newRecipe.addTag(new Tag());
        newRecipe.addRecipeCattegory(new RecipeCattegory());
        newRecipe.addComment(new Comment());

        recipeService.updateRecipePartially(newRecipe);

        assertTrue(oldRecipe.getTags().isEmpty());
        assertTrue(oldRecipe.getCattegories().isEmpty());
        assertTrue(oldRecipe.getComments().isEmpty());
    }

    @Test
    public void updateRecipePartiallyShouldNotChangeDateInRecipe () {
        Recipe oldRecipe = new Recipe();
        oldRecipe.setId(1);

        doReturn(oldRecipe).when(recipeRepository).getRecipe(1);

        Recipe newRecipe = new Recipe();
        newRecipe.setId(1);
        newRecipe.setDateTime();

        recipeService.updateRecipePartially(newRecipe);

        assertNull(oldRecipe.getDateTime());

    }

    //isDuplicate method tests

    @Test
    public void isDuplicateShouldReturnFalseWhenArgumentIsNull () {
        assertFalse(recipeService.isDuplicate(null));
    }

    @Test
    public void isDuplicateShouldReturnFalseWhenRecipeWithSameNameDoesNotExist () {
        String name = "i dont exist";

        doReturn(null).when(recipeRepository).getRecipe(name);

        Recipe recipe = new Recipe();
        recipe.setName(name);

        assertFalse(recipeService.isDuplicate(recipe));
    }

    @Test
    public void isDuplicateShouldReturnFalseWhenRecipeWithSameNameExistButHasTheSameId () {
        String name = "i exist";

        Recipe oldRecipe = new Recipe();
        oldRecipe.setId(1);
        doReturn(oldRecipe).when(recipeRepository).getRecipe(name);

        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName(name);

        assertFalse(recipeService.isDuplicate(recipe));
    }

    @Test
    public void isDuplicateShouldReturnTrueWhenNameIsTheSameButIdNot() {
        String name = "i exist";

        Recipe oldRecipe = new Recipe();
        oldRecipe.setId(4);
        doReturn(oldRecipe).when(recipeRepository).getRecipe(name);

        Recipe recipe = new Recipe();
        recipe.setId(5);
        recipe.setName(name);

        assertTrue(recipeService.isDuplicate(recipe));

    }


}
