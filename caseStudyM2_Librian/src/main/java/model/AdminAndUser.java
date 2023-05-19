package model;

public class AdminAndUser implements IModel<AdminAndUser> {
    private long ID;
    private String userName;
    private String password;
    private EAdminAndUser eAdminAndUser;

    public AdminAndUser() {
    }

    public AdminAndUser(long ID, String userName, String password, EAdminAndUser eAdminAndUser) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.eAdminAndUser = eAdminAndUser;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EAdminAndUser geteAdminAndUser() {
        return eAdminAndUser;
    }

    public void seteAdminAndUser(EAdminAndUser eAdminAndUser) {
        this.eAdminAndUser = eAdminAndUser;
    }

    @Override
    public String toString() {
        return getID()+","+getUserName()+","+getPassword()+","+geteAdminAndUser();
    }
    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        ID=Long.parseLong(items[0]);
        userName=items[1];
        password=items[2];
        eAdminAndUser=EAdminAndUser.getAdminAndUserByName(items[3]);
    }
}
