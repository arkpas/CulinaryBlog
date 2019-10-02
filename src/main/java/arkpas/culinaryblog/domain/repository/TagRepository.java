package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> getTags (String name);

}
