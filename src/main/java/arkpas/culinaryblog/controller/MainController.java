package arkpas.culinaryblog.controller;


import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private RecipeService recipeService;
    private CattegoryService cattegoryService;

    @Autowired
    public MainController(RecipeService recipeService, CattegoryService cattegoryService) {
        this.recipeService = recipeService;
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


    @RequestMapping("/")
    public String landingPage (Model model) {
        List<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);
        return "landing";
    }
}
