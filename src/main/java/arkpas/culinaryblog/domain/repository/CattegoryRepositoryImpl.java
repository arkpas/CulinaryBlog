package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Cattegory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CattegoryRepositoryImpl implements CattegoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Cattegory getCattegory(int id) {
        return entityManager.find(Cattegory.class, id);
    }

    @Override
    public Cattegory getCattegory(String name) {
        List<Cattegory> results = entityManager.createQuery("SELECT c FROM Cattegory AS c WHERE c.name = :name", Cattegory.class).setParameter("name", name).getResultList();
        Cattegory cattegory = null;
        if (!results.isEmpty())
            cattegory = results.get(0);
        return cattegory;
    }

    @Override
    public List<Cattegory> getCattegories() {
        return entityManager.createQuery("SELECT c FROM Cattegory AS c", Cattegory.class).getResultList();
    }

    @Override
    @Transactional
    public void saveCattegory(Cattegory cattegory) {
        entityManager.persist(cattegory);
    }

    @Override
    @Transactional
    public void updateCattegory(Cattegory cattegory) {
        entityManager.merge(cattegory);
    }

    @Override
    @Transactional
    public void removeCattegory(Cattegory cattegory) {
        entityManager.remove(cattegory);
    }
}
