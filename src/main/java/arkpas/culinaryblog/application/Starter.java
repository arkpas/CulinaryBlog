package arkpas.culinaryblog.application;

import arkpas.culinaryblog.domain.Comment;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.RecipeRepository;
import arkpas.culinaryblog.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Starter implements CommandLineRunner {

    private RecipeService recipeService;

    @Autowired
    public Starter(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public void run (String[] args) {
        Recipe recipe = new Recipe();
        recipe.setIngredients("Jabłko 5 szt \nWoda 2 litry");
        recipe.setInstruction("Ugotuj jabłka w wodzie");
        recipe.setName("Kompot");
        recipeService.addRecipe(recipe);




    }
}
