package arkpas.culinaryblog.unitTests.serviceUnitTests;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.RecipeCattegory;
import arkpas.culinaryblog.domain.repository.RecipeCattegoryRepository;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.RecipeCattegoryService;
import arkpas.culinaryblog.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RecipeCattegoryServiceTest {

    @Mock
    private RecipeCattegoryRepository recipeCattegoryRepository;
    @Mock
    private CattegoryService cattegoryService;
    @Mock
    private RecipeService recipeService;

    private RecipeCattegoryService recipeCattegoryService;

    @Before
    public void setup () {
        recipeCattegoryService = new RecipeCattegoryService(recipeCattegoryRepository, cattegoryService, recipeService);
        doNothing().when(recipeCattegoryRepository).saveRecipeCattegory(any(RecipeCattegory.class));
    }


    //addRecipeCattegory method tests

    @Test
    public void addRecipeCattegoryShouldReturnNullWhenRecipeArgumentIsNull () {
        assertNull(recipeCattegoryService.addRecipeCattegory(null, new Cattegory()));
    }


    @Test
    public void addRecipeCattegoryShouldReturnNullWhenCattegoryArgumentIsNull () {
        assertNull(recipeCattegoryService.addRecipeCattegory(new Recipe(), null));
    }

    @Test
    public void addRecipeCattegoryShouldAddRecipeCattegoryToRecipeOnSuccess () {
        Recipe recipe = new Recipe();
        recipeCattegoryService.addRecipeCattegory(recipe, new Cattegory());
        assertEquals(1, recipe.getCattegories().size());
    }

    @Test
    public void addRecipeCattegoryShouldAddRecipeCattegoryToCattegoryOnSuccess () {
        Cattegory cattegory = new Cattegory();
        recipeCattegoryService.addRecipeCattegory(new Recipe(), cattegory);
        assertEquals(1, cattegory.getRecipes().size());
    }

    @Test
    public void addRecipeCattegoryShouldReturnRecipeCattegoryObjectOnSuccess () {
        assertNotNull(recipeCattegoryService.addRecipeCattegory(new Recipe(), new Cattegory()));

    }

    //getRecipesFromCattegory method tests

    @Test
    public void getRecipesFromCattegoryShouldReturnNullIfArgumentIsNull () {
        assertNull(recipeCattegoryService.getRecipesFromCattegory(null));
    }

    @Test
    public void getRecipesFromCattegoryShouldReturnNullIfCattegoryDoesNotExist () {
        doReturn(null).when(cattegoryService).getCattegory(anyString());
        assertNull(recipeCattegoryService.getRecipesFromCattegory("i dont exist"));
    }

    @Test
    public void getRecipesFromCattegoryShouldReturnEmptyListWhenThereIsNoRecipesFromCertainCattegory () {
        doReturn(new Cattegory()).when(cattegoryService).getCattegory(anyString());
        doReturn(new ArrayList<>()).when(recipeCattegoryRepository).getRecipeCattegoriesByCattegory(anyInt());

        assertTrue(recipeCattegoryService.getRecipesFromCattegory("i exist").isEmpty());
    }

    @Test
    public void getRecipesFromCattegoryShouldReturnRecipeListOnSuccess () {

        List<RecipeCattegory> recipeCattegories = new ArrayList<>();
        Recipe recipe = new Recipe();
        RecipeCattegory recipeCattegory = new RecipeCattegory();
        recipeCattegory.setRecipe(recipe);
        recipeCattegories.add(recipeCattegory);

        doReturn(new Cattegory()).when(cattegoryService).getCattegory(anyString());
        doReturn(recipeCattegories).when(recipeCattegoryRepository).getRecipeCattegoriesByCattegory(anyInt());

        assertTrue(recipeCattegoryService.getRecipesFromCattegory("i exist and have recipe").contains(recipe));
    }

    //updateRecipeCattegories method tests

    @Test
    public void updateRecipeCattegoriesNullTest () {
        recipeCattegoryService.updateRecipeCattegories(null, 1);
    }



}
