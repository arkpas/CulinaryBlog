package arkpas.culinaryblog.service;


import arkpas.culinaryblog.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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

    @Test
    public void rateRecipe () {
        User user = new User();
        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);
        user.setId(1);
        doReturn(user).when(userService).getCurrentUser();

        Recipe recipe = new Recipe();
        Rate rate = new Rate();
        recipe.setRate(rate);

        rateService.rateRecipe(recipe, 5);

        assertEquals(1, recipe.getRate().getUserRates().size());
        assertEquals(5, recipe.getRate().getRating(), 0.01);
        assertEquals(1, recipe.getRate().getVotes());


    }

    @Test
    public void rateRecipeWithNulls () {
        User user = null;
        doReturn(user).when(userService).getCurrentUser();

        Recipe recipe = null;

        //should not throw nullpointerexception
        rateService.rateRecipe(recipe, 5);


    }


    @Test
    public void isRated () {

        UserDetails userDetails = new UserDetails();
        userDetails.setId(1);

        UserRate sample1 = new UserRate();
        sample1.setUserDetails(userDetails);

        Rate rate = new Rate();
        rate.addUserRate(sample1);
        Recipe recipe = new Recipe();
        recipe.setRate(rate);

        assertTrue(rateService.isRated(recipe, 1));
        assertFalse(rateService.isRated(recipe, 2));

    }

    @Test
    public void getUserRate () {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(1);

        UserRate sample1 = new UserRate();
        sample1.setUserDetails(userDetails);
        sample1.setRateValue(5);

        Rate rate = new Rate();
        rate.addUserRate(sample1);
        Recipe recipe = new Recipe();
        recipe.setRate(rate);

        assertEquals(5, rateService.getUserRate(recipe, 1));
        assertEquals(0, rateService.getUserRate(recipe, 2));
    }
}
