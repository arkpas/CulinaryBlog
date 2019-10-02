package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        entityManager.persist(comment);
    }

}
