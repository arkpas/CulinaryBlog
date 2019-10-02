package arkpas.culinaryblog.integrationTests.repositoryIntegrationTests;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.RecipeCattegory;
import arkpas.culinaryblog.domain.repository.RecipeCattegoryRepository;
import arkpas.culinaryblog.utils.CattegoryType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest (properties = "culinaryblog.starter.enabled=false")
public class RecipeCattegoryRepositoryIntegrationTest {
    @Autowired
    private RecipeCattegoryRepository recipeCattegoryRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Test
    @Transactional
    public void getRecipeCattegoriesByCattegoryShouldReturnOneObjectOutOfMany () {

        int cattegoryId = -1;
        for (int i = 0; i < 5; i++) {
            Recipe recipe = createRecipe();
            entityManager.persist(recipe);

            Cattegory cattegory = createCattegory();
            entityManager.persist(cattegory);
            cattegoryId = cattegory.getId();

            RecipeCattegory recipeCattegory = new RecipeCattegory();
            recipe.addRecipeCattegory(recipeCattegory);
            cattegory.addRecipeCattegory(recipeCattegory);

            entityManager.persist(recipeCattegory);
        }

        assertEquals(1, recipeCattegoryRepository.getRecipeCattegoriesByCattegory(cattegoryId).size());

    }


    private Recipe createRecipe () {
        Recipe recipe = new Recipe();
        recipe.setName("name");
        recipe.setIngredients("ingredients");
        recipe.setInstruction("instruction");
        return recipe;
    }

    private Cattegory createCattegory () {
        Cattegory cattegory = new Cattegory();
        cattegory.setName("name");
        cattegory.setCattegoryType(CattegoryType.MEAL);
        return cattegory;
    }
}
