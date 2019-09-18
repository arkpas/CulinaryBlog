package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Tag getTag(int tagId) {
        return entityManager.find(Tag.class, tagId);
    }

    @Override
    public Tag getTag(String name) {
        //not implemented yet
        return null;
    }

    @Override
    @Transactional
    public void saveTag(Tag tag) {
        entityManager.persist(tag);
    }

    @Override
    @Transactional
    public void updateTag(Tag tag) {
        entityManager.merge(tag);
    }

    @Override
    @Transactional
    public void removeTag(Tag tag) {
        entityManager.remove(tag);
    }
}
