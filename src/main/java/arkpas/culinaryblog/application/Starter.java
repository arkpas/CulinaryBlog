package arkpas.culinaryblog.application;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.service.UserService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "culinaryblog.starter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Starter implements CommandLineRunner {

    private RecipeService recipeService;
    private CattegoryService cattegoryService;
    private UserService userService;

    @Autowired
    public Starter(RecipeService recipeService, CattegoryService cattegoryService, UserService userService) {
        this.recipeService = recipeService;
        this.cattegoryService = cattegoryService;
        this.userService = userService;
    }


    @Override
    public void run (String[] args) {
        Recipe recipe = new Recipe();
        recipe.setIngredients("Jabłko 5 szt \nWoda 2 litry");
        recipe.setInstruction("Ugotuj jabłka w wodzie");
        recipe.setName("Kompot");
        recipe.setImageLink("https://d3iamf8ydd24h9.cloudfront.net/pictures/articles/2019/08/1065779-v-1080x1080.jpg");
        recipeService.addRecipe(recipe);


        userService.addUser("arek", "123", "123", "blogger");


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
