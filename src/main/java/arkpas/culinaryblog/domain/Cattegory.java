package arkpas.culinaryblog.domain;

import arkpas.culinaryblog.utils.CattegoryType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cattegory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;
    private CattegoryType cattegoryType;
    @OneToMany (mappedBy = "cattegory", targetEntity = RecipeCattegory.class)
    private Set<RecipeCattegory> recipes = new HashSet<>();

    public Cattegory() {}

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public CattegoryType getCattegoryType() {
        return cattegoryType;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCattegoryType(CattegoryType cattegoryType) {
        this.cattegoryType = cattegoryType;
    }

}
