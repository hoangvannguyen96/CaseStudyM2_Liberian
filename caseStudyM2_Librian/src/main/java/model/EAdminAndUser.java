package model;

public enum EAdminAndUser {
    ADMIN(1,"ADMIN"),USER(2,"USER");
    int id;
    String name;

    EAdminAndUser(int id, String name) {
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
    public static EAdminAndUser getAdminAndUserById(int IDAdminAndUser) {
        for (EAdminAndUser eAdminAndUser : EAdminAndUser.values()) {
            if (eAdminAndUser.getId() == IDAdminAndUser) {
                return eAdminAndUser;
            }
        }
        return null;
    }
    public static EAdminAndUser getAdminAndUserByName(String name) {
        for (EAdminAndUser eAdminAndUser : EAdminAndUser.values()) {
            if (eAdminAndUser.getName().equals(name)) {
                return eAdminAndUser;
            }
        }
        return null;
    }
}
