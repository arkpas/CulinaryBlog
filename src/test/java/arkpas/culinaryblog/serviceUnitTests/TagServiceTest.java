package arkpas.culinaryblog.serviceUnitTests;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.Tag;
import arkpas.culinaryblog.domain.repository.TagRepository;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.service.TagService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    @Mock
    private TagRepository tagRepository;
    @Mock
    private RecipeService recipeService;

    private TagService tagService;

    @Before
    public void setup () {
        tagService = new TagService(tagRepository, recipeService);
    }

    //addTag method tests

    @Test
    public void addTagShouldReturnNullWhenRecipeArgumentIsNull () {
        tagService.addTag(null, "name");
    }

    @Test
    public void addTagShouldReturnNullWhenStringArgumentIsNull () {
        tagService.addTag(new Recipe(), null);
    }

    @Test
    public void addTagShouldReturnTagObjectOnSuccess () {
        doNothing().when(recipeService).updateRecipe(any(Recipe.class));
        assertNotNull(tagService.addTag(new Recipe(), "hello"));
    }

    @Test
    public void addTagShouldReturnTagObjectWithGivenName() {
        doNothing().when(recipeService).updateRecipe(any(Recipe.class));
        String name = "i am tag";
        Assert.assertEquals(name, tagService.addTag(new Recipe(), name).getTagName());
    }

    @Test
    public void addTagShouldAddObjectToCollectionInRecipe() {
        doNothing().when(recipeService).updateRecipe(any(Recipe.class));
        Recipe recipe = new Recipe();
        tagService.addTag(recipe, "ohayo");
        assertEquals(1, recipe.getTags().size());
    }

    //addTags method tests

    @Test
    public void addTagsShouldDoNothingWhenStringIsNull () {
        Recipe recipe = new Recipe();
        tagService.addTags(recipe, null);
        assertTrue(recipe.getTags().isEmpty());
    }

    @Test
    public void addTagsShouldDoNothingWhenStringIsEmpty () {
        Recipe recipe = new Recipe();
        tagService.addTags(recipe, " ");
        assertTrue(recipe.getTags().isEmpty());
    }

    @Test
    public void addTagsShouldSplitStringAndAddTagsToCollectionInRecipe () {
        doNothing().when(recipeService).updateRecipe(any(Recipe.class));
        String tag1 = "cheese";
        String tag2 = "bread";
        String tagString = tag1 + " " + tag2;

        Recipe recipe = new Recipe();
        tagService.addTags(recipe, tagString);

        List<String> tagsFromRecipe = recipe.getTags().stream().map(Tag::getTagName).collect(Collectors.toList());
        assertTrue(tagsFromRecipe.contains(tag1));
        assertTrue(tagsFromRecipe.contains(tag2));

    }

    @Test
    public void addTagsShouldCutAnyWhitespaceCharacter () {
        doNothing().when(recipeService).updateRecipe(any(Recipe.class));
        String tag1 = "cheese";
        String tag2 = "bread";
        String tagString = "   " + tag1 + "   " + tag2 + "     ";

        Recipe recipe = new Recipe();
        tagService.addTags(recipe, tagString);

        List<String> tagsFromRecipe = recipe.getTags().stream().map(Tag::getTagName).collect(Collectors.toList());

        assertTrue(tagsFromRecipe.contains(tag1));
        assertTrue(tagsFromRecipe.contains(tag2));
        assertEquals(2, recipe.getTags().size());
    }

    //getRecipesByTag method tests

    @Test
    public void getRecipesByTagShouldReturnEmptyListIfArgumentIsNull () {
        assertTrue(tagService.getRecipesByTag(null).isEmpty());
    }

    @Test
    public void getRecipesByTagShouldReturnEmptyListIfArgumentStringIsEmpty () {
        assertTrue(tagService.getRecipesByTag("").isEmpty());
    }

    @Test
    public void getRecipesByTagShouldReturnRecipesListOnSuccess () {

        String tagName = "tomato";

        Tag tag = new Tag();
        tag.setTagName(tagName);

        Recipe recipe = new Recipe();
        recipe.addTag(tag);

        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        doReturn(tagList).when(tagRepository).getTags(tagName);

        assertEquals(1, tagService.getRecipesByTag(tagName).size());

    }

    // updateTags method tests

    @Test
    public void updateTagsShouldDoNothingWhenRecipeIsNull () {
        Recipe recipe = null;
        tagService.updateTags(recipe, "we are tags");
        assertNull(recipe);
    }

    @Test
    public void updateTagsShouldDoNothingWhenStringArgIsNull () {
        Recipe recipe = new Recipe();
        recipe.addTag(new Tag());
        tagService.updateTags(recipe, null);
        assertEquals(1, recipe.getTags().size());
    }

    @Test
    public void updateTagsShouldDoNothingWhenStringArgIsEmpty () {
        Recipe recipe = new Recipe();
        recipe.addTag(new Tag());
        tagService.updateTags(recipe, "");
        assertEquals(1, recipe.getTags().size());
    }

    @Test
    public void updateTagsShouldRemoveCurrentTagsOnSuccess () {
        doNothing().when(recipeService).updateRecipe(any(Recipe.class));

        Recipe recipe = new Recipe();
        recipe.addTag(new Tag());
        tagService.updateTags(recipe, "  ");
        assertEquals(0, recipe.getTags().size());
    }

    @Test
    public void updateTagsShouldReplaceTagsOnSuccess () {
        doNothing().when(recipeService).updateRecipe(any(Recipe.class));

        Recipe recipe = new Recipe();
        recipe.addTag(new Tag());
        tagService.updateTags(recipe, "butter carrot");
        assertEquals(2, recipe.getTags().size());
    }



}
