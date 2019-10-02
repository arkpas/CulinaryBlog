package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class AuthorityRepositoryImpl implements AuthorityRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    public AuthorityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Authority saveAuthority(Authority authority) {
        entityManager.persist(authority);
        return authority;
    }



}
