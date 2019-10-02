package arkpas.culinaryblog.integrationTests.repositoryIntegrationTests;

import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "culinaryblog.starter.enabled=false")
public class UserRepositoryIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void getUserShouldReturnNullWhenNoUserIsFound () {
        assertNull(userRepository.getUser("i dont exist"));
    }

    @Test
    @Transactional
    public void getUserShouldReturnObjectWhenUserIsFound () {
        String name = "user";
        entityManager.persist(createUser(name));

        assertEquals(name, userRepository.getUser(name).getUsername());
    }

    private User createUser (String name) {
        User user = new User();
        user.setUsername(name);
        user.setPassword("password");
        return user;
    }

}
