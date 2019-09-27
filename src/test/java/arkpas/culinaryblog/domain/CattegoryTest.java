package arkpas.culinaryblog.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CattegoryTest {

    private Cattegory cattegory;

    @Before
    public void setup () {
        cattegory = new Cattegory();
    }

    @Test
    public void addRecipeCattegory () {

        //check if set is initially empty
        assertTrue(cattegory.getRecipes().isEmpty());

        //check if null is added to collection
        RecipeCattegory recipeCattegory = null;
        cattegory.addRecipeCattegory(recipeCattegory);
        assertTrue(cattegory.getRecipes().isEmpty());

        recipeCattegory = new RecipeCattegory();
        cattegory.addRecipeCattegory(recipeCattegory);

        //set should have 1 element now
        assertEquals(1, cattegory.getRecipes().size());

        //check if added object has its parent set
        assertEquals(cattegory, recipeCattegory.getCattegory());

    }

    //collection from getter should be unmodifiable
    @Test (expected = UnsupportedOperationException.class)
    public void checkIfUnmodifiable () {

        Set<RecipeCattegory> set = cattegory.getRecipes();
        set.clear();
    }
}
