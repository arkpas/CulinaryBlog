package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.RecipeCattegory;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.RecipeCattegoryService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CattegoryController {

    private CattegoryService cattegoryService;
    private RecipeCattegoryService recipeCattegoryService;

    @Autowired
    public CattegoryController(CattegoryService cattegoryService, RecipeCattegoryService recipeCattegoryService) {
        this.cattegoryService = cattegoryService;
        this.recipeCattegoryService = recipeCattegoryService;
    }

    // this attribute is used in menu on every page
    @ModelAttribute("timeCattegories")
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

    @RequestMapping("/kategoria/{name}")
    public String getRecipesFromCattegory (@PathVariable("name") String name, Model model) {
        List<Recipe> recipes = recipeCattegoryService.getRecipesFromCattegory(name);
        model.addAttribute("recipes", recipes);
        return "searchResults";
    }
}
