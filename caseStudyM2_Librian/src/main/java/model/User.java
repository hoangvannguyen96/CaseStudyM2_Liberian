package model;

public class User implements IModel<User> {
    private long ID;
    private String userName;
    private String password;
    private EUser eUser;

    public User() {
    }

    public User(long ID, String userName, String password, EUser eUser) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.eUser = eUser;
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

    public EUser geteAdminAndUser() {
        return eUser;
    }

    public void seteAdminAndUser(EUser eUser) {
        this.eUser = eUser;
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
        eUser = EUser.getAdminAndUserByName(items[3]);
    }
}
