package arkpas.culinaryblog.integrationTests.repositoryIntegrationTests;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.Tag;
import arkpas.culinaryblog.domain.repository.TagRepository;
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
@SpringBootTest(properties = "culinaryblog.starter.enabled=false")
public class TagRepositoryIntegrationTest {

    @Autowired
    private TagRepository tagRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void getTagsShouldReturnListOfObjectsWithEqualName () {

        int amount = 5;
        String searchText = "carrot";
        for (int i = 0; i < amount; i++) {
            Tag tag = createTag(searchText);
            Recipe recipe = createRecipe(tag);
            entityManager.persist(recipe);
        }
        for (int i = 0; i < amount; i++) {
            Tag tag = createTag("apple");
            Recipe recipe = createRecipe(tag);
            entityManager.persist(recipe);
        }

        assertEquals(amount, tagRepository.getTags(searchText).size());
    }

    @Test
    @Transactional
    public void getTagsShouldNotBeCaseSensitive () {

        int amount = 3;
        String searchText = "carrot".toLowerCase();

        for (int i = 0; i < amount; i++) {
            Tag tag = createTag(searchText);
            Recipe recipe = createRecipe(tag);
            entityManager.persist(recipe);
        }
        for (int i = 0; i < amount; i++) {
            Tag tag = createTag("apple");
            Recipe recipe = createRecipe(tag);
            entityManager.persist(recipe);
        }
        searchText = searchText.toUpperCase();
        assertEquals(amount, tagRepository.getTags(searchText).size());
    }

    private Tag createTag(String name) {
        Tag tag = new Tag();
        tag.setTagName(name);
        return tag;
    }

    private Recipe createRecipe (Tag tag) {
        Recipe recipe = new Recipe();
        recipe.setName("name");
        recipe.setIngredients("ingredients");
        recipe.setInstruction("instruction");
        recipe.addTag(tag);
        return recipe;
    }

}

