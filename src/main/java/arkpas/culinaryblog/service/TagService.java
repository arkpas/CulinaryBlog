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

    public Tag getTag (int id) {
        return tagRepository.getTag(id);
    }
    public Tag getTag (String name) {
        return tagRepository.getTag(name);
    }

    public void addTag(Recipe recipe, String tagName) {
        if (recipe != null && tagName != null) {
            Tag tag = new Tag();
            tag.setTagName(tagName);
            recipe.addTag(tag);
            recipeService.updateRecipe(recipe);
        }
    }
    public void addTags(Recipe recipe, String tags) {
        if (tags != null && tags.length() > 0)
            Arrays.stream(tags.split(" ")).filter(s -> s.length() > 0).forEach(s -> this.addTag(recipe, s));

    }

    public Set<Recipe> getRecipesByTag (String tag) {
        List<Tag> tagList = tagRepository.getTags(tag);
        Set<Recipe> recipes = new HashSet<>();
        if (!tagList.isEmpty())
            recipes.addAll(tagList.stream().map(Tag::getRecipe).collect(Collectors.toSet()));
        return recipes;
    }

    public void updateTag (Tag tag) {
        tagRepository.updateTag(tag);
    }

    public void deleteTag (Tag tag) {
        tagRepository.removeTag(tag);
    }
}
