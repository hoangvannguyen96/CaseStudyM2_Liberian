package model;

public enum EPosition {
    A(1, "Kệ A1"), B(2, "Kệ B2"), C(3, "Kệ C3"), D(4, "Kệ D4");
    private int id;
    private String name;


    EPosition(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EPosition getEPostionById(int id) {
        for (EPosition ePosition : EPosition.values()) {
            if (ePosition.getId() == id) {
                return ePosition;
            }
        }
        return null;
    }

    public static EPosition getEPostionByName(String name) {
        for (EPosition ePosition : EPosition.values()) {
            if (ePosition.toString().equals(name)) {
                return ePosition;
            }
        }
        return null;
    }
}
