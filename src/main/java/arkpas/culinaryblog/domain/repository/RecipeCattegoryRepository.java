package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.RecipeCattegory;

import java.util.List;

public interface RecipeCattegoryRepository {

    List<RecipeCattegory> getRecipeCattegoriesByCattegory (int cattegoryId);
    void saveRecipeCattegory (RecipeCattegory recipeCattegory);

}
