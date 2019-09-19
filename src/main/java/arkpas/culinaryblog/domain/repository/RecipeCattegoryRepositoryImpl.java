package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.RecipeCattegory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RecipeCattegoryRepositoryImpl implements RecipeCattegoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public RecipeCattegory getRecipeCattegory(int id) {
        return entityManager.find(RecipeCattegory.class, id);
    }

    @Override
    public List<RecipeCattegory> getRecipeCattegoriesByCattegory(int cattegoryId) {
        return entityManager.createQuery("SELECT rc FROM RecipeCattegory AS rc WHERE cattegory_id = :cattegoryId", RecipeCattegory.class).setParameter("cattegoryId", cattegoryId).getResultList();
    }

    @Override
    public List<RecipeCattegory> getRecipeCattegoriesByRecipe(int recipeId) {
        return entityManager.createQuery("SELECT rc FROM RecipeCattegory AS rc WHERE recipe_id = :recipeId", RecipeCattegory.class).setParameter("recipeId", recipeId).getResultList();
    }


    @Override
    @Transactional
    public void saveRecipeCattegory(RecipeCattegory recipeCattegory) {
        entityManager.persist(recipeCattegory);
    }

    @Override
    @Transactional
    public void updateRecipeCattegory(RecipeCattegory recipeCattegory) {
        entityManager.merge(recipeCattegory);
    }

    @Override
    @Transactional
    public void removeRecipeCattegory(RecipeCattegory recipeCattegory) {
        entityManager.remove(recipeCattegory);
    }
}
