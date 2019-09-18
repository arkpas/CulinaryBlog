package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.Tag;
import arkpas.culinaryblog.domain.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getTag (int id) {
        return tagRepository.getTag(id);
    }
    public Tag getTag (String name) {
        return tagRepository.getTag(name);
    }
    public void addTag(Recipe recipe, String tagName) {
        Tag tag = new Tag();
        tag.setRecipe(recipe);
        tag.setTagName(tagName);
        tagRepository.saveTag(tag);
    }
    public void addTags(Recipe recipe, String tags) {
        if (tags != null)
            Arrays.stream(tags.split(" ")).filter(s -> s.length() > 0).forEach(s -> this.addTag(recipe, s));

    }
    public void updateTag (Tag tag) {
        tagRepository.updateTag(tag);
    }
    public void deleteTag (Tag tag) {
        tagRepository.removeTag(tag);
    }
}
