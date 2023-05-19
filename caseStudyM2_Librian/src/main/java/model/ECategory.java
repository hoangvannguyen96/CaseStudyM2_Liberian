package model;

public enum ECategory {
    Sience(1, "Khoa học"), Novel(2, "Tiểu thuyết"), Literature(3, "Văn học"), Jokes(4, "Truyện cười");
    private int id;
    private String name;

    ECategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ECategory getECategoryById(int idCategory) {
        for (ECategory eCategory : ECategory.values()) {
            if (eCategory.getId() == idCategory) {
                return eCategory;
            }
        }
        return null;
    }


    public static ECategory getECategoryByName(String name) {
        for (ECategory eCategory : ECategory.values()) {
            if (eCategory.toString().equals(name)) {
                return eCategory;
            }
        }
        return null;
    }
}
