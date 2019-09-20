package arkpas.culinaryblog.domain;

import arkpas.culinaryblog.utils.CattegoryType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cattegory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min=3, message = "Nazwa jest za krótka!")
    private String name;
    private CattegoryType cattegoryType;
    @OneToMany (mappedBy = "cattegory", targetEntity = RecipeCattegory.class, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public void addRecipeCattegory (RecipeCattegory recipeCattegory) {
        recipes.add(recipeCattegory);
        recipeCattegory.setCattegory(this);
    }

    public void removeRecipeCattegory (RecipeCattegory recipeCattegory) {
        if (recipes.contains(recipeCattegory)) {
            recipes.remove(recipeCattegory);
            recipeCattegory.setCattegory(null);
        }
    }

}
