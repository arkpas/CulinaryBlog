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
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/kategoria/dodaj")
    public String addCattegory (Model model) {
        Cattegory emptyCattegory = new Cattegory();
        model.addAttribute("cattegory", emptyCattegory);
        return "cattegoryForm";
    }

    @RequestMapping(value = "/kategoria/dodaj", method = RequestMethod.POST)
    public String addCattegory (Cattegory cattegory) {
        cattegoryService.addCattegory(cattegory);
        System.out.println(cattegory.getCattegoryType());
        return "redirect:/";
    }

    @RequestMapping("/kategoria/modyfikuj")
    public String modifyCattegory () {
        return "cattegoryModification";
    }

    @RequestMapping("/kategoria/edytuj/{cattegoryId}")
    public String editCattegory (@PathVariable("cattegoryId") int cattegoryId, Model model) {
        Cattegory cattegory = cattegoryService.getCattegory(cattegoryId);

        model.addAttribute("cattegory", cattegory);
        return "cattegoryEditionForm";
    }

    @RequestMapping(value = "/kategoria/edytuj/{cattegoryId}", method = RequestMethod.POST)
    public String editCattegory (Cattegory cattegory, Model model) {
        cattegoryService.updateCattegory(cattegory);
        return "redirect:/kategoria/modyfikuj";
    }

    @RequestMapping("/kategoria/usun/{cattegoryId}")
    public String removeCattegory (@PathVariable("cattegoryId") int cattegoryId, Model model) {
        Cattegory cattegory = cattegoryService.getCattegory(cattegoryId);
        recipeCattegoryService.deleteCattegoryFromRecipes(cattegory);
        cattegoryService.deleteCattegory(cattegory);
        return "redirect:/kategoria/modyfikuj";
    }
}
