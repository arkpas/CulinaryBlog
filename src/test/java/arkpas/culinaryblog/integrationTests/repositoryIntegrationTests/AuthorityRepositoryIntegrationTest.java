package arkpas.culinaryblog.integrationTests.repositoryIntegrationTests;

import arkpas.culinaryblog.domain.Authority;
import arkpas.culinaryblog.domain.repository.AuthorityRepository;
import arkpas.culinaryblog.domain.repository.AuthorityRepositoryImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "culinaryblog.starter.enabled=false")
@AutoConfigureTestEntityManager
public class AuthorityRepositoryIntegrationTest {
    @Autowired
    private AuthorityRepository authorityRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void saveAuthorityShouldSaveObjectToDatabase () {
        Authority authority = new Authority();
        authority.setName("name");
        authority.setUsername("username");
        authorityRepository.saveAuthority(authority);
        int id = authority.getId();
        assertNotNull(entityManager.find(Authority.class, id));
    }




}
