package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.RecipeCattegory;
import arkpas.culinaryblog.domain.repository.RecipeCattegoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeCattegoryService {

    private RecipeCattegoryRepository recipeCattegoryRepository;
    private CattegoryService cattegoryService;
    private RecipeService recipeService;


    @Autowired
    public RecipeCattegoryService(RecipeCattegoryRepository recipeCattegoryRepository, CattegoryService cattegoryService, RecipeService recipeService) {
        this.recipeCattegoryRepository = recipeCattegoryRepository;
        this.cattegoryService = cattegoryService;
        this.recipeService = recipeService;
    }

    public void addRecipeCattegories (Recipe recipe, int... cattegoryIds) {
        for (int i = 0; i < cattegoryIds.length; i++) {
            Cattegory cattegory = cattegoryService.getCattegory(cattegoryIds[i]);
            this.addRecipeCattegory(recipe, cattegory);
        }
    }

    public RecipeCattegory addRecipeCattegory (Recipe recipe, Cattegory cattegory) {
        if (cattegory != null && recipe != null) {
            RecipeCattegory recipeCattegory = new RecipeCattegory();
            recipe.addRecipeCattegory(recipeCattegory);
            cattegory.addRecipeCattegory(recipeCattegory);
            recipeCattegoryRepository.saveRecipeCattegory(recipeCattegory);
            return recipeCattegory;
        }
        return null;
    }

    public List<Recipe> getRecipesFromCattegory (String cattegoryName) {
        if (cattegoryName == null)
            return null;
        Cattegory cattegory = cattegoryService.getCattegory(cattegoryName);
        if (cattegory == null)
            return null;
        List<RecipeCattegory> results = recipeCattegoryRepository.getRecipeCattegoriesByCattegory(cattegory.getId());
        return results.stream().map(RecipeCattegory::getRecipe).collect(Collectors.toList());
    }


    public void updateRecipeCattegories(Recipe recipe, int... cattegoryIds) {
        if (recipe != null) {
            recipe.removeAllRecipeCattegories();
            recipeService.updateRecipe(recipe);
            addRecipeCattegories(recipe, cattegoryIds);
        }
    }

}
