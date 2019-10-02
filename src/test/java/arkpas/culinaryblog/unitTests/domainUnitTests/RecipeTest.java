package arkpas.culinaryblog.unitTests.domainUnitTests;

import arkpas.culinaryblog.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeTest {

    private Recipe recipe;

    @Before
    public void setup () {
        recipe = new Recipe();
    }

    //setDateTime method tests

    @Test
    public void setDateTimeShouldAssignValueToField () {
        recipe.setDateTime();
        assertNotNull(recipe.getDateTime());
    }

    //setRate method tests

    @Test
    public void setRateShouldAssignObjectToRateField () {
        recipe.setRate(new Rate());
        assertNotNull(recipe.getRate());
    }

    @Test
    public void setRateShouldAssignReferenceToArgumentObject () {
        Rate rate = new Rate();
        recipe.setRate(rate);
        assertEquals(recipe, rate.getRecipe());
    }

    @Test
    public void setRateShouldRemoveReferenceFromOldObjectWhenNewObjectIsAssigned () {
        Rate oldRate = new Rate();
        recipe.setRate(oldRate);
        recipe.setRate(new Rate());

        assertNull(oldRate.getRecipe());
    }

    @Test
    public void setRateShouldRemoveReferenceFromOldObjectWhenNullIsAssigned () {
        Rate oldRate = new Rate();
        recipe.setRate(oldRate);
        recipe.setRate(null);

        assertNull(oldRate.getRecipe());
    }

    @Test
    public void setRateShouldNotProduceNullpointerExceptionWhenNullArgumentsAreUsed () {
        recipe.setRate(null);
    }

    //addComment method tests

    @Test
    public void addCommentShouldAddElementToSet () {
        Comment comment = new Comment();
        recipe.addComment(comment);
        assertTrue(recipe.getComments().contains(comment));
    }

    @Test
    public void addCommentShouldNotAddNullElementToSet () {
        recipe.addComment(null);
        assertTrue(recipe.getComments().isEmpty());
    }

    @Test
    public void addCommentShouldAssignReferenceInArgumentObject () {
        Comment comment = new Comment();
        recipe.addComment(comment);
        assertEquals(recipe, comment.getRecipe());
    }


    //addRecipeCattegory method tests

    @Test
    public void addRecipeCattegoryShouldAddElementToSet () {
        RecipeCattegory recipeCattegory = new RecipeCattegory();
        recipe.addRecipeCattegory(recipeCattegory);
        assertTrue(recipe.getCattegories().contains(recipeCattegory));
    }

    @Test
    public void addRecipeCattegoryShouldNotAddNullElementToSet () {
        recipe.addRecipeCattegory(null);
        assertTrue(recipe.getCattegories().isEmpty());
    }

    @Test
    public void addRecipeCattegoryShouldAssignReferenceInArgumentObject () {
        RecipeCattegory recipeCattegory = new RecipeCattegory();
        recipe.addRecipeCattegory(recipeCattegory);
        assertEquals(recipe, recipeCattegory.getRecipe());
    }

    //removeAllRecipeCattegories method tests

    @Test
    public void removeAllRecipeCattegoriesShouldDeleteAllElementsFromSet () {
        recipe.addRecipeCattegory(new RecipeCattegory());
        recipe.addRecipeCattegory(new RecipeCattegory());
        recipe.removeAllRecipeCattegories();
        assertTrue(recipe.getCattegories().isEmpty());
    }

    @Test
    public void removeAllRecipeCattegoriesShouldNulifyReferencesInRemovedObjects () {
        RecipeCattegory recipeCattegory = new RecipeCattegory();
        recipe.addRecipeCattegory(recipeCattegory);
        recipe.removeAllRecipeCattegories();
        assertNull(recipeCattegory.getRecipe());
    }

    //addTag method tests

    @Test
    public void addTagShouldAddElementToSet () {
        Tag tag = new Tag();
        recipe.addTag(tag);
        assertTrue(recipe.getTags().contains(tag));
    }

    @Test
    public void addTagShouldNotAddNullElementToSet () {
        recipe.addTag(null);
        assertTrue(recipe.getTags().isEmpty());
    }

    @Test
    public void addTagShouldAssignReferenceInArgumentObject () {
        Tag tag = new Tag();
        recipe.addTag(tag);
        assertEquals(recipe, tag.getRecipe());
    }

    //removeAllTags method tests

    @Test
    public void removeAllTagsShouldDeleteAllElementsFromSet () {
        recipe.addTag(new Tag());
        recipe.addTag(new Tag());
        recipe.removeAllTags();
        assertTrue(recipe.getTags().isEmpty());
    }

    @Test
    public void removeAllTagsShouldNulifyReferencesInRemovedObjects () {
        Tag tag = new Tag();
        recipe.addTag(tag);
        recipe.removeAllTags();
        assertNull(tag.getRecipe());
    }

    //collection getters tests

    @Test(expected = UnsupportedOperationException.class)
    public void getCommentsShouldReturnUnmodifiableSet () {
        recipe.getComments().clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getCattegoriesShouldReturnUnmodifiableSet () {
        recipe.getCattegories().clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getTagsShouldReturnUnmodifiableSet () {
        recipe.getTags().clear();
    }

}
