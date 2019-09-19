package arkpas.culinaryblog.utils;

public enum CattegoryType {
    DIET ("Dieta"),
    TIME ("Pora"),
    MEAL ("Danie");

    private String name;

    private CattegoryType (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

}
