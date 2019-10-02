package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Tag> getTags(String name) {
        return entityManager.createQuery("SELECT t FROM Tag AS t WHERE lower(tagName) = lower(:name)", Tag.class).setParameter("name", name).getResultList();
    }

}
