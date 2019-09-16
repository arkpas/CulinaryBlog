package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
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
        model.addAttribute("recipe", emptyRecipe);
        return "recipeForm";
    }
    @RequestMapping(value = "/przepis/dodaj", method = RequestMethod.POST)
    public String addRecipe (Recipe recipe, String ingredients) {
        recipeService.addRecipe(recipe);
        return "redirect:/";
    }

    @RequestMapping(value = "/przepis/szukaj", method = RequestMethod.POST)
    public String searchRecipe (String searchText, Model model) {
        List<Recipe> recipes = recipeService.searchRecipes(searchText);
        model.addAttribute("recipes", recipes);
        return "searchResults";
    }
}
