package arkpas.culinaryblog.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeTest {

    private Recipe recipe;

    @Before
    public void setup () {
        recipe = new Recipe();
    }

    @Test
    public void setRate () {

        //check for nullpointerexception
        recipe.setRate(null);

        Rate rate = new Rate();
        recipe.setRate(rate);

        assertEquals(rate, recipe.getRate());
        //parent setter should also call childs setter
        assertEquals(recipe, rate.getRecipe());

        recipe.setRate(null);

        //setting null should nulify references in both objects
        assertNull(recipe.getRate());
        assertNull(rate.getRecipe());

    }

    @Test
    public void addCommnet () {
        Comment comment = null;

        //set should be initially empty
        assertTrue(recipe.getComments().isEmpty());

        recipe.addComment(comment);
        //set should not add null elements
        assertTrue(recipe.getComments().isEmpty());

        comment = new Comment();

        recipe.addComment(comment);
        //set should have an element now
        assertEquals(1, recipe.getComments().size());
        //parent reference should be set in child object
        assertEquals(recipe, comment.getRecipe());

    }

    @Test
    public void addRecipeCattegory () {
        RecipeCattegory recipeCattegory = null;

        //set should be initially empty
        assertTrue(recipe.getCattegories().isEmpty());

        recipe.addRecipeCattegory(recipeCattegory);
        //set should not add null elements
        assertTrue(recipe.getCattegories().isEmpty());

        recipeCattegory = new RecipeCattegory();

        recipe.addRecipeCattegory(recipeCattegory);
        //set should have an element now
        assertEquals(1, recipe.getCattegories().size());
        //parent reference should be set in child object
        assertEquals(recipe, recipeCattegory.getRecipe());
    }

    @Test
    public void removeAllRecipeCattegories() {

        //called on empty set should not produce any exceptions
        recipe.removeAllRecipeCattegories();

        RecipeCattegory sample1 = new RecipeCattegory();
        RecipeCattegory sample2 = new RecipeCattegory();

        recipe.addRecipeCattegory(sample1);
        recipe.addRecipeCattegory(sample2);

        //samples should have reference to parent
        assertEquals(recipe, sample1.getRecipe());
        assertEquals(recipe, sample2.getRecipe());

        recipe.removeAllRecipeCattegories();

        //set should be empty now
        assertTrue(recipe.getCattegories().isEmpty());

        //parent references should be set to null
        assertNull(sample1.getRecipe());
        assertNull(sample2.getRecipe());
    }

    @Test
    public void addTag () {
        Tag tag = null;

        //set should be initially empty
        assertTrue(recipe.getTags().isEmpty());

        recipe.addTag(tag);
        //set should not add null elements
        assertTrue(recipe.getTags().isEmpty());

        tag = new Tag();

        recipe.addTag(tag);
        //set should have an element now
        assertEquals(1, recipe.getTags().size());
        //parent reference should be set in child object
        assertEquals(recipe, tag.getRecipe());
    }

    @Test
    public void removeAllTags() {

        //called on empty set should not produce any exceptions
        recipe.removeAllTags();

        Tag sample1 = new Tag();
        Tag sample2 = new Tag();

        recipe.addTag(sample1);
        recipe.addTag(sample2);

        //samples should have reference to parent
        assertEquals(recipe, sample1.getRecipe());
        assertEquals(recipe, sample2.getRecipe());

        recipe.removeAllTags();

        //set should be empty now
        assertTrue(recipe.getTags().isEmpty());

        //parent references should be set to null
        assertNull(sample1.getRecipe());
        assertNull(sample2.getRecipe());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void checkIfCommentsAreUnmodifiable() {
        recipe.getComments().clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void checkIfCattegoriesAreUnmodifiable() {
        recipe.getCattegories().clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void checkIfTagsAreUnmodifiable() {
        recipe.getTags().clear();
    }

}
