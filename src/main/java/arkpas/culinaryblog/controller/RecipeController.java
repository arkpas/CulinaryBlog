package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.FileService;
import arkpas.culinaryblog.service.RecipeCattegoryService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;


@Controller
public class RecipeController {
    private RecipeService recipeService;
    private RecipeCattegoryService recipeCattegoryService;
    private CattegoryService cattegoryService;

    @Autowired
    private FileService fileService;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeCattegoryService recipeCattegoryService, CattegoryService cattegoryService) {
        this.recipeService = recipeService;
        this.recipeCattegoryService = recipeCattegoryService;
        this.cattegoryService = cattegoryService;
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
        List<Cattegory> timeCattegories = cattegoryService.getCattegories(CattegoryType.TIME);
        List<Cattegory> dietCattegories = cattegoryService.getCattegories(CattegoryType.DIET);
        List<Cattegory> mealCattegories = cattegoryService.getCattegories(CattegoryType.MEAL);
        model.addAttribute("recipe", emptyRecipe);
        model.addAttribute("cattegory", emptyCattegory);
        model.addAttribute("timeCattegories", timeCattegories);
        model.addAttribute("dietCattegories", dietCattegories);
        model.addAttribute("mealCattegories", mealCattegories);
        return "recipeForm";
    }
    @RequestMapping(value = "/przepis/dodaj", method = RequestMethod.POST)
    public String addRecipe (Recipe recipe, int cattegoryId1, int cattegoryId2, int cattegoryId3, @RequestParam(value = "file") MultipartFile multipartFile) {
        recipeService.addRecipe(recipe);
        recipeCattegoryService.addRecipeCattegory(recipe, cattegoryId1);
        recipeCattegoryService.addRecipeCattegory(recipe, cattegoryId2);
        recipeCattegoryService.addRecipeCattegory(recipe, cattegoryId3);
        fileService.saveFile(multipartFile, recipe.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/przepis/szukaj", method = RequestMethod.POST)
    public String searchRecipe (String searchText, Model model) {
        List<Recipe> recipes = recipeService.searchRecipes(searchText);
        model.addAttribute("recipes", recipes);
        return "searchResults";
    }


}
