package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Recipe;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RecipeRepositoryImpl  implements RecipeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Recipe getRecipe(int id) {
       return entityManager.find(Recipe.class, id);
    }

    @Override
    public Recipe getRecipe (String name) {
        List<Recipe> results = entityManager.createQuery("SELECT r FROM Recipe AS r WHERE r.name = :name", Recipe.class).setParameter("name", name).getResultList();
        Recipe recipe = null;
        if (!results.isEmpty())
            recipe = results.get(0);
        return recipe;
    }

    @Override
    public List<Recipe> getRecipes() {
        return entityManager.createQuery("SELECT r FROM Recipe AS r", Recipe.class).getResultList();
    }

    @Override
    public List<Recipe> searchRecipes (String searchText) {
        return entityManager.createQuery("SELECT r FROM Recipe AS r WHERE lower(r.name) LIKE lower(:searchText)", Recipe.class).setParameter("searchText", "%"+searchText+"%").getResultList();
    }

    @Override
    @Transactional
    public void saveRecipe (Recipe recipe) {
        entityManager.persist(recipe);
    }

    @Override
    @Transactional
    public void updateRecipe (Recipe recipe) {
       entityManager.merge(recipe);
    }

    @Override
    @Transactional
    public void removeRecipe(Recipe recipe) {
        entityManager.remove(recipe);
    }

}
