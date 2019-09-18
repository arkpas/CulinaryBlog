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
    public Authority getAuthority(int authorityId) {
        return entityManager.find(Authority.class, authorityId);
    }

    @Override
    public Authority getAuthority(String name) {
        //not implemented yet
        return null;
    }

    @Override
    @Transactional
    public void saveAuthority(Authority authority) {
        entityManager.persist(authority);
    }

    @Override
    @Transactional
    public void updateAuthority(Authority authority) {
        entityManager.merge(authority);
    }

    @Override
    @Transactional
    public void removeAuthority(Authority authority) {
        entityManager.remove(authority);
    }
}
