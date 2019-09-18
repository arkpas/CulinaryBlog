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

    @Autowired
    public RecipeCattegoryService(RecipeCattegoryRepository recipeCattegoryRepository, CattegoryService cattegoryService) {
        this.recipeCattegoryRepository = recipeCattegoryRepository;
        this.cattegoryService = cattegoryService;
    }

    public RecipeCattegory getRecipeCattegory (int id) {
        return recipeCattegoryRepository.getRecipeCattegory(id);

    }
    public void addRecipeCattegory (Recipe recipe, Cattegory cattegory) {
        RecipeCattegory recipeCattegory = new RecipeCattegory();
        recipeCattegory.setRecipe(recipe);
        recipeCattegory.setCattegory(cattegory);
        recipeCattegoryRepository.saveRecipeCattegory(recipeCattegory);
    }
    public void addRecipeCattegories (Recipe recipe, int... cattegories) {
        for (int i = 0; i < cattegories.length; i++) {
            this.addRecipeCattegory(recipe, cattegories[i]);
        }
    }

    public void addRecipeCattegory (Recipe recipe, int cattegoryId) {
        RecipeCattegory recipeCattegory = new RecipeCattegory();
        Cattegory cattegory = cattegoryService.getCattegory(cattegoryId);
        if (cattegory != null && recipe != null) {
            recipeCattegory.setRecipe(recipe);
            recipeCattegory.setCattegory(cattegory);
            recipeCattegoryRepository.saveRecipeCattegory(recipeCattegory);
        }
    }

    public List<Recipe> getRecipesFromCattegory (String cattegoryName) {
        Cattegory cattegory = cattegoryService.getCattegory(cattegoryName);
        if (cattegory == null)
            return null;
        List<RecipeCattegory> results = recipeCattegoryRepository.getRecipeCattegoriesByCattegory(cattegory.getId());
        return results.stream().map(RecipeCattegory::getRecipe).collect(Collectors.toList());
    }


    public void updateRecipeCattegory (RecipeCattegory recipeCattegory) {
        recipeCattegoryRepository.updateRecipeCattegory(recipeCattegory);
    }
    public void deleteRecipeCattegory (RecipeCattegory recipeCattegory) {
        recipeCattegoryRepository.removeRecipeCattegory(recipeCattegory);
    }
}
