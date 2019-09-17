package arkpas.culinaryblog.utils;

public enum CattegoryType {
    DIET ("Dieta"),
    TIME ("Pora"),
    MEAL ("Typ");

    private String name;

    private CattegoryType (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

}
