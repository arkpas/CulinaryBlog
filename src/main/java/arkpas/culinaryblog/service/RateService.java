package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.UserRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService {

    private RecipeService recipeService;
    private UserService userService;

    @Autowired
    public RateService(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    public void rateRecipe (Recipe recipe, int rate) {
        User user = userService.getCurrentUser();
        if (recipe != null && user != null) {
            UserRate userRate = new UserRate();
            userRate.setRateValue(rate);
            recipe.getRate().addUserRate(userRate);
            userRate.setUserDetails(user.getUserDetails());
            recipe.getRate().calculateRating();
            recipeService.updateRecipe(recipe);
        }
    }
}
