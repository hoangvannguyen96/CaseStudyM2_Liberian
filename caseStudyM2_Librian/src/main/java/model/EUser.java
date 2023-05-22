package model;

public enum EUser {
    ADMIN(1,"ADMIN"),USER(2,"USER");
    int id;
    String name;

    EUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public static EUser getAdminAndUserById(int IDAdminAndUser) {
        for (EUser eUser : EUser.values()) {
            if (eUser.getId() == IDAdminAndUser) {
                return eUser;
            }
        }
        return null;
    }
    public static EUser getAdminAndUserByName(String name) {
        for (EUser eUser : EUser.values()) {
            if (eUser.getName().equals(name)) {
                return eUser;
            }
        }
        return null;
    }
}
