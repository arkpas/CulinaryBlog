package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.Tag;
import arkpas.culinaryblog.domain.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService {
    private TagRepository tagRepository;
    private RecipeService recipeService;

    @Autowired
    public TagService(TagRepository tagRepository, RecipeService recipeService) {
        this.tagRepository = tagRepository;
        this.recipeService = recipeService;
    }

    public Tag addTag(Recipe recipe, String tagName) {
        if (recipe == null || tagName == null)
            return null;

        Tag tag = new Tag();
        tag.setTagName(tagName);
        recipe.addTag(tag);
        recipeService.updateRecipe(recipe);
        return tag;

    }
    public void addTags(Recipe recipe, String tags) {
        if (tags != null && tags.length() > 0)
            Arrays.stream(tags.split(" ")).filter(s -> s.length() > 0).forEach(s -> this.addTag(recipe, s));

    }

    public Set<Recipe> getRecipesByTag (String tag) {

        Set<Recipe> recipes = new HashSet<>();
        if (tag == null || tag.length() == 0)
            return recipes;

        List<Tag> tagList = tagRepository.getTags(tag);
        if (!tagList.isEmpty())
            recipes.addAll(tagList.stream().map(Tag::getRecipe).collect(Collectors.toSet()));
        return recipes;
    }

    public void updateTags(Recipe recipe, String tagsString) {
        if (recipe != null && tagsString != null && tagsString.length() > 0) {
            recipe.removeAllTags();
            recipeService.updateRecipe(recipe);
            this.addTags(recipe, tagsString);
        }
    }
}
