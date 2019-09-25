package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.*;
import arkpas.culinaryblog.service.*;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class RecipeController {
    private RecipeService recipeService;
    private RecipeCattegoryService recipeCattegoryService;
    private CattegoryService cattegoryService;
    private TagService tagService;
    private RateService rateService;
    private UserService userService;


    @Autowired
    public RecipeController(RecipeService recipeService, RecipeCattegoryService recipeCattegoryService, CattegoryService cattegoryService, TagService tagService, RateService rateService, UserService userService) {
        this.recipeService = recipeService;
        this.recipeCattegoryService = recipeCattegoryService;
        this.cattegoryService = cattegoryService;
        this.tagService = tagService;
        this.rateService = rateService;
        this.userService = userService;
    }

    // this attribute is used in menu on every page
    @ModelAttribute ("timeCattegories")
    public List<Cattegory> getTimeCattegories () {
        return cattegoryService.getCattegories(CattegoryType.TIME);
    }

    // this attribute is used in menu on every page
    @ModelAttribute ("dietCattegories")
    public List<Cattegory> getDietCattegories () {
        return cattegoryService.getCattegories(CattegoryType.DIET);
    }

    // this attribute is used in menu on every page
    @ModelAttribute ("mealCattegories")
    public List<Cattegory> getMealCattegories () {
        return cattegoryService.getCattegories(CattegoryType.MEAL);
    }

    @RequestMapping("/przepis/{name}")
    public String getRecipe (@PathVariable(value = "name") String name, Model model) {
        Recipe recipe = recipeService.getRecipe(name);
        Comment emptyComment = new Comment();
        User user = userService.getCurrentUser();
        boolean isRated = false;
        int userRateValue = 0;
        if (user != null) {
            int userId = user.getId();
            isRated = recipe.getRate().getUserRates().stream().anyMatch(userRate -> userRate.getUserDetails().getId() == userId);
            if (isRated) {
                userRateValue = recipe.getRate().getUserRates().stream().filter(userRate -> userRate.getUserDetails().getId() == userId).mapToInt(UserRate::getRateValue).findFirst().getAsInt();
            }

        }
        if (recipe == null)
            return "redirect:/";
        else {
            model.addAttribute("recipe", recipe);
            model.addAttribute("comment", emptyComment);
            model.addAttribute("isRated", isRated);
            model.addAttribute("userRateValue", userRateValue);
            return "recipePage";
        }
    }


    @RequestMapping(value = "/przepis/dodaj")
    public String addRecipe (Model model) {
        Recipe emptyRecipe = new Recipe();
        Cattegory emptyCattegory = new Cattegory();
        model.addAttribute("recipe", emptyRecipe);
        model.addAttribute("cattegory", emptyCattegory);
        return "recipeForm";
    }
    @RequestMapping(value = "/przepis/dodaj", method = RequestMethod.POST)
    public String addRecipe (@Valid Recipe recipe, BindingResult result, @RequestParam("timeCattegoryId") int timeCattegoryId, @RequestParam("dietCattegoryId") int dietCattegoryId, @RequestParam("mealCattegoryId") int mealCattegoryId, @RequestParam("tagsString") String tagsString, Model model) {
        if (result.hasErrors())
            return "recipeForm";
        if (recipeService.isDuplicate(recipe)) {
            result.rejectValue("name", "error.recipe", "Przepis o tej nazwie już istnieje!");
            return "recipeForm";
        }
        else {
            recipeService.addRecipe(recipe);
            recipeCattegoryService.addRecipeCattegories(recipe, timeCattegoryId, dietCattegoryId, mealCattegoryId);
            tagService.addTags(recipe, tagsString);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/przepis/szukaj", method = RequestMethod.POST)
    public String searchRecipe (String searchText, Model model) {
        Set<Recipe> recipes = new HashSet<>(recipeService.searchRecipes(searchText));
        recipes.addAll(tagService.getRecipesByTag(searchText));
        model.addAttribute("recipes", recipes);
        return "searchResults";
    }

    @RequestMapping(value = "/przepis/modyfikuj/edytuj/{recipeId}")
    public String editRecipe (@PathVariable("recipeId") int recipeId, Model model) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        model.addAttribute("recipe", recipe);
        return "recipeEditionForm";
    }

    @RequestMapping(value = "/przepis/modyfikuj/edytuj", method = RequestMethod.POST)
    public String editRecipe (@Valid Recipe recipe, BindingResult result, @RequestParam("timeCattegoryId") int timeCattegoryId, @RequestParam("dietCattegoryId") int dietCattegoryId, @RequestParam("mealCattegoryId") int mealCattegoryId, @RequestParam("tagsString") String tagsString) {
        if (result.hasErrors())
            return "recipeEditionForm";

        if (recipeService.isDuplicate(recipe)) {
            result.rejectValue("name", "error.recipe", "Przepis o tej nazwie już istnieje!");
            return "recipeEditionForm";
        }
        else {
            recipe = recipeService.updateRecipePartially(recipe);
            recipeCattegoryService.updateRecipeCattegories(recipe, timeCattegoryId, dietCattegoryId, mealCattegoryId);
            //tagService.updateTags(recipe.getId(), tagsString);
            return "redirect:/przepis/" + recipe.getName();
        }
    }

    @RequestMapping(value = "/przepis/modyfikuj/usun/{id}")
    public String deleteRecipe (@PathVariable("id") int recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (recipe != null) {
            recipeService.deleteRecipe(recipe);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/przepis/ocen", method = RequestMethod.POST)
    public String rateRecipe (@RequestParam("rate") int rate, @RequestParam("recipeId") int recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        rateService.rateRecipe(recipe, rate);
        return "redirect:/przepis/" + recipe.getName();
    }


}
