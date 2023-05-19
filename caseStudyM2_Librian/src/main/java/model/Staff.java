package model;

public class Staff {
    private long IDStaff;
    private String nameStaff;
    private String sexStaff;
    private String addressStaff;
    private long phoneNumberStaff;

    public Staff() {
    }

    public Staff(long IDStaff, String nameStaff, String sexStaff, String addressStaff, long phoneNumberStaff) {
        this.IDStaff = IDStaff;
        this.nameStaff = nameStaff;
        this.sexStaff = sexStaff;
        this.addressStaff = addressStaff;
        this.phoneNumberStaff = phoneNumberStaff;
    }

    public long getIDStaff() {
        return IDStaff;
    }

    public void setIDStaff(long IDStaff) {
        this.IDStaff = IDStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public String getSexStaff() {
        return sexStaff;
    }

    public void setSexStaff(String sexStaff) {
        this.sexStaff = sexStaff;
    }

    public String getAddressStaff() {
        return addressStaff;
    }

    public void setAddressStaff(String addressStaff) {
        this.addressStaff = addressStaff;
    }

    public long getPhoneNumberStaff() {
        return phoneNumberStaff;
    }

    public void setPhoneNumberStaff(long phoneNumberStaff) {
        this.phoneNumberStaff = phoneNumberStaff;
    }
    @Override
    public String toString() {
        return getIDStaff() + "," + getNameStaff() + "," + getSexStaff() + "," + getAddressStaff()
                + "," + getPhoneNumberStaff();
    }
}
