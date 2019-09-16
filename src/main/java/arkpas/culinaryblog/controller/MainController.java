package arkpas.culinaryblog.controller;


import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import arkpas.culinaryblog.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private RecipeService recipeService;

    @Autowired
    public MainController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String landingPage (Model model) {
        List<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);
        return "landing";
    }
}
