package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.UserRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.OptionalInt;

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
        if (recipe != null && user != null && !isRated(recipe, user.getId())) {
                UserRate userRate = new UserRate();
                userRate.setRateValue(rate);
                recipe.getRate().addUserRate(userRate);
                userRate.setUserDetails(user.getUserDetails());
                recipe.getRate().calculateRating();
                recipeService.updateRecipe(recipe);
        }
    }

    //this method checks if recipe has been already rated by user
    public boolean isRated (Recipe recipe, int userId) {
        if (recipe != null)
            return recipe.getRate().getUserRates().stream().anyMatch(userRate -> userRate.getUserDetails().getId() == userId);
        else
            return false;
    }

    //returns user rate if present, otherwise 0
    public int getUserRate (Recipe recipe, int userId) {
        if (recipe != null) {
            OptionalInt optionalInt = recipe.getRate().getUserRates().stream().filter(userRate -> userRate.getUserDetails().getId() == userId).mapToInt(UserRate::getRateValue).findFirst();
            return optionalInt.isPresent() ? optionalInt.getAsInt() : 0;
        }
        else
            return 0;
    }
}
