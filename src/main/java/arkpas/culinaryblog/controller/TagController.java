package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.TagService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class TagController {

    private CattegoryService cattegoryService;
    private TagService tagService;

    @Autowired
    public TagController(CattegoryService cattegoryService, TagService tagService) {
        this.cattegoryService = cattegoryService;
        this.tagService = tagService;
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

    @RequestMapping("/tag/{tagName}")
    public String getRecipesByTag (@PathVariable("tagName") String tagName, Model model) {
        Collection recipes = tagService.getRecipesByTag(tagName);
        model.addAttribute("recipes", recipes);
        return "searchResults";
    }


}
