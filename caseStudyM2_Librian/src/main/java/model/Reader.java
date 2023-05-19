package model;

import utils.DateUtils;

import java.util.Date;

public class Reader implements IModel<Reader> {
    private long IDReader;
    private String nameReader;
    private String sexReader;
    private Date dateOfBirthReader;
    private String jobReader;
    private String addressReader;

    public Reader() {

    }

    public Reader(long IDReader, String nameReader, String sexReader, Date dateOfBirthReader, String jobReader, String addressReader) {
        this.IDReader = IDReader;
        this.nameReader = nameReader;
        this.sexReader = sexReader;
        this.dateOfBirthReader = dateOfBirthReader;
        this.jobReader = jobReader;
        this.addressReader = addressReader;
    }

    public long getIDReader() {
        return IDReader;
    }

    public void setIDReader(long IDReader) {
        this.IDReader = IDReader;
    }

    public String getNameReader() {
        return nameReader;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public String getSexReader() {
        return sexReader;
    }

    public void setSexReader(String sexReader) {
        this.sexReader = sexReader;
    }

    public Date getDateOfBirthReader() {
        return dateOfBirthReader;
    }

    public void setDateOfBirthReader(Date dateOfBirthReader) {
        this.dateOfBirthReader = dateOfBirthReader;
    }

    public String getJobReader() {
        return jobReader;
    }

    public void setJobReader(String jobReader) {
        this.jobReader = jobReader;
    }

    public String getAddressReader() {
        return addressReader;
    }

    public void setAddressReader(String addressReader) {
        this.addressReader = addressReader;
    }

    @Override
    public String toString() {
        return getIDReader() + "," + getNameReader() + "," + getSexReader() + "," + DateUtils.format(getDateOfBirthReader()) + "," +
                getJobReader() + "," + getAddressReader();
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        IDReader = Long.parseLong(items[0]);
        nameReader = items[1];
        sexReader = items[2];
        dateOfBirthReader = DateUtils.parse(items[3]);
        jobReader = items[4];
        addressReader = items[5];
    }
}
