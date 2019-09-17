package arkpas.culinaryblog.application;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.RecipeCattegoryService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Starter implements CommandLineRunner {

    private RecipeService recipeService;
    private CattegoryService cattegoryService;
    private RecipeCattegoryService recipeCattegoryService;

    @Autowired
    public Starter(RecipeService recipeService, CattegoryService cattegoryService, RecipeCattegoryService recipeCattegoryService) {
        this.recipeService = recipeService;
        this.cattegoryService = cattegoryService;
        this.recipeCattegoryService = recipeCattegoryService;
    }

    @Override
    public void run (String[] args) {
        Recipe recipe = new Recipe();
        recipe.setIngredients("Jabłko 5 szt \nWoda 2 litry");
        recipe.setInstruction("Ugotuj jabłka w wodzie");
        recipe.setName("Kompot");
        recipeService.addRecipe(recipe);

        createDietCattegories();
        createMealCattegories();
        createTimeCattegories();


    }

    public void createTimeCattegories () {
        Cattegory cattegory = new Cattegory();
        cattegory.setName("śniadanie");
        cattegory.setCattegoryType(CattegoryType.TIME);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("obiad");
        cattegory.setCattegoryType(CattegoryType.TIME);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("kolacja");
        cattegory.setCattegoryType(CattegoryType.TIME);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("deser");
        cattegory.setCattegoryType(CattegoryType.TIME);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("przekąska");
        cattegory.setCattegoryType(CattegoryType.TIME);
        cattegoryService.addCattegory(cattegory);
    }

    public void createDietCattegories () {
        Cattegory cattegory = new Cattegory();
        cattegory.setName("mięsne");
        cattegory.setCattegoryType(CattegoryType.DIET);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("wegetariańskie");
        cattegory.setCattegoryType(CattegoryType.DIET);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("wegańskie");
        cattegory.setCattegoryType(CattegoryType.DIET);
        cattegoryService.addCattegory(cattegory);
    }

    public void createMealCattegories () {
        Cattegory cattegory = new Cattegory();
        cattegory.setName("zupy");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("makarony");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("kasze");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("burgery");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("ryby");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("koktajle");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("warzywa");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("owoce");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("zapiekanki");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        cattegoryService.addCattegory(cattegory);
    }


}
