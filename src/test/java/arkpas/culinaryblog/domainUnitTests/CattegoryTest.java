package arkpas.culinaryblog.domainUnitTests;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.RecipeCattegory;
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
    public void addRecipeCattegoryNullArgumentShouldNotBeAccepted () {
        cattegory.addRecipeCattegory(null);
        assertTrue(cattegory.getRecipes().isEmpty());
    }

    @Test
    public void addRecipeCattegoryElementShouldBeAdded () {
        cattegory.addRecipeCattegory(new RecipeCattegory());
        assertFalse(cattegory.getRecipes().isEmpty());
    }

    @Test
    public void addRecipeCattegoryArgumentShouldHaveItsParentReferenceAssigned () {
        RecipeCattegory recipeCattegory = new RecipeCattegory();
        cattegory.addRecipeCattegory(recipeCattegory);
        assertEquals(cattegory, recipeCattegory.getCattegory());
    }

    //collection from getter should be unmodifiable
    @Test (expected = UnsupportedOperationException.class)
    public void recipeCattegorySetFromGetterShouldBeUnmodifiable () {
        Set<RecipeCattegory> set = cattegory.getRecipes();
        set.clear();
    }
}
