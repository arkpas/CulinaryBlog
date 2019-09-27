package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.RecipeCattegory;
import arkpas.culinaryblog.domain.repository.CattegoryRepository;
import arkpas.culinaryblog.utils.CattegoryType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CattegoryServiceTest {

    @Mock
    private CattegoryRepository cattegoryRepository;

    private CattegoryService cattegoryService;

    @Before
    public void setup () {
        cattegoryService = new CattegoryService(cattegoryRepository);
    }

    @Test
    public void getCattegoriesByCattegoryType () {
        List<Cattegory> cattegorySampleList = new ArrayList<>();
        Cattegory sample1 = new Cattegory();
        sample1.setCattegoryType(CattegoryType.TIME);
        Cattegory sample2 = new Cattegory();
        sample2.setCattegoryType(CattegoryType.MEAL);

        cattegorySampleList.add(sample1);
        cattegorySampleList.add(sample2);

        doReturn(cattegorySampleList).when(cattegoryRepository).getCattegories();

        //check if service filters cattegories properly
        assertEquals(1, cattegoryService.getCattegories(CattegoryType.TIME).size());
        assertEquals(1, cattegoryService.getCattegories(CattegoryType.MEAL).size());
        assertTrue(cattegoryService.getCattegories(CattegoryType.DIET).isEmpty());
        
    }
    
    @Test
    public void updateCattegoryPartially () {
        
        Cattegory originalCattegory = new Cattegory();
        originalCattegory.setCattegoryType(CattegoryType.DIET);
        originalCattegory.setName("originalCattegory");
        originalCattegory.addRecipeCattegory(new RecipeCattegory());

        doReturn(originalCattegory).when(cattegoryRepository).getCattegory(anyInt());

        Cattegory changedCattegory = new Cattegory();

        //we set different name and CattegoryType for this one
        changedCattegory.setCattegoryType(CattegoryType.MEAL);
        changedCattegory.setName("changedCattegory");

        cattegoryService.updateCattegoryPartially(changedCattegory);

        //now originalCattegory should be updated with information from changedCattegory
        assertEquals(originalCattegory.getName(), changedCattegory.getName());
        assertEquals(originalCattegory.getCattegoryType(), changedCattegory.getCattegoryType());

        //also originalCattegory should maintain his RecipeCattegory collection
        assertEquals(1, originalCattegory.getRecipes().size());
        
    }
}
