package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.Cattegory;

import java.util.List;

public interface CattegoryRepository {

    public Cattegory getCattegory (int id);
    public Cattegory getCattegory (String name);
    public List<Cattegory> getCattegories();
    public void saveCattegory (Cattegory cattegory);
    public void updateCattegory (Cattegory cattegory);
    public void removeCattegory (Cattegory cattegory);

}
