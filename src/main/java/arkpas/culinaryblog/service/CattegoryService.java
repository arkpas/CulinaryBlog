package arkpas.culinaryblog.service;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.repository.CattegoryRepository;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CattegoryService {

    private CattegoryRepository cattegoryRepository;

    @Autowired
    public CattegoryService(CattegoryRepository cattegoryRepository) {
        this.cattegoryRepository = cattegoryRepository;
    }

    public Cattegory getCattegory (int id) {
        return cattegoryRepository.getCattegory(id);
    }
    public Cattegory getCattegory (String name) {
        return cattegoryRepository.getCattegory(name);
    }

    public List<Cattegory> getCattegories (CattegoryType cattegoryType) {
        List<Cattegory> results = cattegoryRepository.getCattegories();
        results = results.stream().filter(cattegory -> cattegory.getCattegoryType() == cattegoryType).collect(Collectors.toList());
        return results;
    }

    public void addCattegory (Cattegory cattegory) {
        cattegoryRepository.saveCattegory(cattegory);
    }
    public void updateCattegory (Cattegory cattegory) {
        cattegoryRepository.updateCattegory(cattegory);
    }
    public void deleteCattegory (Cattegory cattegory) {
        cattegoryRepository.removeCattegory(cattegory);
    }
}
