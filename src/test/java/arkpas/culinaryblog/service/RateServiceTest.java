package arkpas.culinaryblog.service;


import arkpas.culinaryblog.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceTest {

    @Mock
    private RecipeService recipeService;
    @Mock
    private UserService userService;

    private RateService rateService;

    @Before
    public void setup () {
        rateService = new RateService(recipeService, userService);
    }

    //rateRecipe method tests

    @Test
    public void rateRecipeShouldReturnNullWhenArgumentIsNull () {
        assertNull(rateService.rateRecipe(null, 1));
    }

    @Test
    public void rateRecipeShouldReturnNullWhenUserIsNull () {
        doReturn(null).when(userService).getCurrentUser();

        assertNull(rateService.rateRecipe(new Recipe(), 2));
    }

    @Test
    public void rateRecipeShouldReturnNullWhenRecipeIsRatedAlready () {
        User user = new User();
        user.setId(1);

        UserDetails userDetails = new UserDetails();
        userDetails.setId(1);

        user.setUserDetails(userDetails);
        doReturn(user).when(userService).getCurrentUser();

        Recipe recipe = new Recipe();

        UserRate userRate = new UserRate();
        userRate.setUserDetails(userDetails);
        userRate.setRate(recipe.getRate());

        recipe.getRate().addUserRate(userRate);

        assertNull(rateService.rateRecipe(recipe, 6));
    }

    @Test
    public void rateRecipeShouldReturnRecipeObjectOnSuccess () {

        doReturn(new User()).when(userService).getCurrentUser();
        Recipe recipe = new Recipe();
        assertNotNull(rateService.rateRecipe(new Recipe(), 3));
    }

    @Test
    public void rateRecipeShouldAffectRecipeRating () {
        doReturn(new User()).when(userService).getCurrentUser();
        Recipe recipe = new Recipe();
        rateService.rateRecipe(recipe, 4);
        assertEquals(4, recipe.getRate().getRating(), 0.001);
    }

    @Test
    public void rateRecipeShouldAddUserRateToCollectionInRecipeObject () {
        doReturn(new User()).when(userService).getCurrentUser();
        Recipe recipe = new Recipe();
        rateService.rateRecipe(recipe, 4);
        assertEquals(1, recipe.getRate().getUserRates().size());
    }

    @Test
    public void rateRecipeShouldAddUserDetailsReferenceToUserRateObject () {
        User user = new User();
        doReturn(user).when(userService).getCurrentUser();
        Recipe recipe = new Recipe();
        rateService.rateRecipe(recipe, 10);
        Optional<UserRate> optional = recipe.getRate().getUserRates().stream().findAny();
        UserRate userRate = new UserRate();
        if (optional.isPresent())
            userRate = optional.get();
        assertEquals(user.getUserDetails(), userRate.getUserDetails());
    }


    //isRated method tests

    @Test
    public void isRatedShouldReturnFalseIfRecipeIsNull () {
        assertFalse(rateService.isRated(null, 1));
    }

    @Test
    public void isRatedShouldReturnFalseIfRecipeHasNoRates () {
        Recipe recipe = new Recipe();
        assertFalse(rateService.isRated(recipe, 1));
    }

    @Test
    public void isRatedShouldReturnTrueIfRecipeIsRatedAlreadyByUser () {
        Recipe recipe = new Recipe();
        User user = new User();
        user.setId(1);
        user.getUserDetails().setId(1);

        UserRate userRate = new UserRate();

        userRate.setUserDetails(user.getUserDetails());
        userRate.setRate(recipe.getRate());

        recipe.getRate().addUserRate(userRate);

        assertTrue(rateService.isRated(recipe, 1));

    }

    //getUserRate method tests

    @Test
    public void getUserRateShouldReturnZeroWithNullArguments () {
        assertEquals(0, rateService.getUserRate(null, 1));
    }

    @Test
    public void getUserRateShouldReturnZeroWhenRecipeHasNoRates () {
        assertEquals(0, rateService.getUserRate(new Recipe(), 1));
    }

    @Test
    public void getUserRateShouldReturnRateValue () {
        Recipe recipe = new Recipe();
        User user = new User();
        user.setId(1);
        user.getUserDetails().setId(1);

        UserRate userRate = new UserRate();

        userRate.setUserDetails(user.getUserDetails());
        userRate.setRate(recipe.getRate());
        userRate.setRateValue(15);

        recipe.getRate().addUserRate(userRate);

        assertEquals(15, rateService.getUserRate(recipe, 1));
    }


}
