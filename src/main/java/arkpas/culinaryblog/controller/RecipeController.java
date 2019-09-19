package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.RecipeCattegoryService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.service.TagService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class RecipeController {
    private RecipeService recipeService;
    private RecipeCattegoryService recipeCattegoryService;
    private CattegoryService cattegoryService;
    private TagService tagService;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeCattegoryService recipeCattegoryService, CattegoryService cattegoryService, TagService tagService) {
        this.recipeService = recipeService;
        this.recipeCattegoryService = recipeCattegoryService;
        this.cattegoryService = cattegoryService;
        this.tagService = tagService;
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
        if (recipe == null)
            return "redirect:/";
        else {
            model.addAttribute("recipe", recipe);
            model.addAttribute("comment", emptyComment);
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
    public String addRecipe (Recipe recipe, int cattegoryId1, int cattegoryId2, int cattegoryId3, String tagsString) {
        recipeService.addRecipe(recipe);
        System.out.println("URL" + recipe.getImageLink());
        recipeCattegoryService.addRecipeCattegories(recipe, cattegoryId1, cattegoryId2, cattegoryId3);
        tagService.addTags(recipe, tagsString);
        return "redirect:/";
    }

    @RequestMapping(value = "/przepis/szukaj", method = RequestMethod.POST)
    public String searchRecipe (String searchText, Model model) {
        Set<Recipe> recipes = new HashSet<>(recipeService.searchRecipes(searchText));
        recipes.addAll(tagService.getRecipesByTag(searchText));
        model.addAttribute("recipes", recipes);
        return "searchResults";
    }

    @RequestMapping(value = "/przepis/edytuj/{recipeId}")
    public String editRecipe (@PathVariable("recipeId") int recipeId, Model model) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        model.addAttribute("recipe", recipe);
        return "recipeEditionForm";
    }

    @RequestMapping(value = "/przepis/edytuj", method = RequestMethod.POST)
    public String editRecipe (Recipe recipe, @RequestParam("timeCattegoryId") int timeCattegoryId, @RequestParam("dietCattegoryId") int dietCattegoryId, @RequestParam("mealCattegoryId") int mealCattegoryId ) {
        recipeService.updateRecipe(recipe);
        recipeCattegoryService.updateRecipeCattegories(recipe.getId(), timeCattegoryId, dietCattegoryId, mealCattegoryId);
        return "redirect:/przepis/" + recipe.getName();
    }


}
