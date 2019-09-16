package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Comment getComment(int id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        entityManager.merge(comment);
    }
}
