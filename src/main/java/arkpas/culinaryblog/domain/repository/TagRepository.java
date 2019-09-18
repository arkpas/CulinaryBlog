package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Tag;

public interface TagRepository {

    public Tag getTag (int id);
    public Tag getTag (String name);
    public void saveTag(Tag tag);
    public void updateTag (Tag tag);
    public void removeTag (Tag tag);
}
