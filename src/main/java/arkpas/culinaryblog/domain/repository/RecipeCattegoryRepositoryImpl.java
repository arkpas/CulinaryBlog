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
    public List<RecipeCattegory> getRecipeCattegoriesByCattegory(int cattegoryId) {
        return entityManager.createQuery("SELECT rc FROM RecipeCattegory AS rc WHERE cattegory_id = :cattegoryId", RecipeCattegory.class).setParameter("cattegoryId", cattegoryId).getResultList();
    }

    @Override
    @Transactional
    public void saveRecipeCattegory(RecipeCattegory recipeCattegory) {
        entityManager.persist(recipeCattegory);
    }

}
