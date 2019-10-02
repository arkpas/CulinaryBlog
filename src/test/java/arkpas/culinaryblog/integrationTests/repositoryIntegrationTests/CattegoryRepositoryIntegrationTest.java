package arkpas.culinaryblog.integrationTests.repositoryIntegrationTests;


import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.repository.CattegoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "culinaryblog.starter.enabled=false")
public class CattegoryRepositoryIntegrationTest {

    @Autowired
    private CattegoryRepository cattegoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void getCattegoryShouldReturnNull () {
        assertNull(cattegoryRepository.getCattegory("i do not exist"));
    }

    @Test
    @Transactional
    public void getCattegoryShouldReturnCattegoryObject () {
        String name = "i do exist";

        Cattegory cattegory = new Cattegory();
        cattegory.setName(name);
        entityManager.persist(cattegory);

        assertNotNull(cattegoryRepository.getCattegory(name));
    }

    @Test
    @Transactional
    public void getCattegoriesShouldReturnAllPersistedCattegories () {
        Cattegory cattegory = new Cattegory();
        cattegory.setName("first");
        entityManager.persist(cattegory);

        cattegory = new Cattegory();
        cattegory.setName("second");
        entityManager.persist(cattegory);

        assertEquals(2, cattegoryRepository.getCattegories().size());
    }


}
