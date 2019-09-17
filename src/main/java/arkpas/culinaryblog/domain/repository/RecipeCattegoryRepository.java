package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.RecipeCattegory;

import java.util.List;

public interface RecipeCattegoryRepository {

    public RecipeCattegory getRecipeCattegory (int id);
    public List<RecipeCattegory> getRecipeCattegoriesByCattegory (int cattegoryId);
    public void saveRecipeCattegory (RecipeCattegory recipeCattegory);
    public void updateRecipeCattegory (RecipeCattegory recipeCattegory);
    public void removeRecipeCattegory (RecipeCattegory recipeCattegory);
}
