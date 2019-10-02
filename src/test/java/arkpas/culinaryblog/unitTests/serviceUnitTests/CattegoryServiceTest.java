package arkpas.culinaryblog.unitTests.serviceUnitTests;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.RecipeCattegory;
import arkpas.culinaryblog.domain.repository.CattegoryRepository;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CattegoryServiceTest {

    @Mock
    private CattegoryRepository cattegoryRepository;

    private CattegoryService cattegoryService;

    @Before
    public void setup () {
        cattegoryService = new CattegoryService(cattegoryRepository);
    }


    //getCattegoriesByCattegoryType method tests

    @Test
    public void getCattegoriesByCattegoryTypeShouldFilterObjectsProperly () {

        List<Cattegory> cattegorySampleList = new ArrayList<>();
        int timeCattegoryElements = 10;
        int mealCattegoryElements = 15;
        int dietCattegoryElements = 13;

        for (int i = 0; i < timeCattegoryElements; i++) {
            Cattegory sample = new Cattegory();
            sample.setCattegoryType(CattegoryType.TIME);
            cattegorySampleList.add(sample);
        }

        for (int i = 0; i < mealCattegoryElements; i++) {
            Cattegory sample = new Cattegory();
            sample.setCattegoryType(CattegoryType.MEAL);
            cattegorySampleList.add(sample);
        }

        for (int i = 0; i < dietCattegoryElements; i++) {
            Cattegory sample = new Cattegory();
            sample.setCattegoryType(CattegoryType.DIET);
            cattegorySampleList.add(sample);
        }

        doReturn(cattegorySampleList).when(cattegoryRepository).getCattegories();


        assertEquals(timeCattegoryElements, cattegoryService.getCattegories(CattegoryType.TIME).size());
        assertEquals(mealCattegoryElements, cattegoryService.getCattegories(CattegoryType.MEAL).size());
        assertEquals(dietCattegoryElements, cattegoryService.getCattegories(CattegoryType.DIET).size());

    }

    //updateCattegoryPartially method tests

    @Test
    public void updateCattegoryPartiallyShouldReturnObjectWithChangedValues () {
        Cattegory cattegory = new Cattegory();
        cattegory.setName("old");
        cattegory.setCattegoryType(CattegoryType.DIET);

        doReturn(cattegory).when(cattegoryRepository).getCattegory(anyInt());

        Cattegory newCattegory = new Cattegory();
        newCattegory.setName("new");
        newCattegory.setCattegoryType(CattegoryType.MEAL);

        cattegoryService.updateCattegoryPartially(newCattegory);

        assertEquals(newCattegory.getName(), cattegory.getName());
        assertEquals(newCattegory.getCattegoryType(), cattegory.getCattegoryType());

    }

    @Test
    public void updateCattegoryPartiallyShouldNotChangeRecipeCattegorySet () {
        Cattegory cattegory = new Cattegory();
        cattegory.addRecipeCattegory(new RecipeCattegory());
        cattegory.addRecipeCattegory(new RecipeCattegory());

        doReturn(cattegory).when(cattegoryRepository).getCattegory(anyInt());

        Cattegory newCattegory = new Cattegory();
        newCattegory.addRecipeCattegory(new RecipeCattegory());

        cattegoryService.updateCattegoryPartially(newCattegory);

        assertEquals(2, cattegory.getRecipes().size());

    }

    @Test
    public void updateCattegoryPartiallyShouldReturnNullIfArgumentIsNull () {
        assertNull(cattegoryService.updateCattegoryPartially(null));
    }

    @Test
    public void updateCattegoryPartiallyShouldReturnNullIfQueriedCattegoryDoesNotExist () {
        doReturn(null).when(cattegoryRepository).getCattegory(anyInt());

        Cattegory newCattegory = new Cattegory();
        assertNull(cattegoryService.updateCattegoryPartially(newCattegory));
    }
}
