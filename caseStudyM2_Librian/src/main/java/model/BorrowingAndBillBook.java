package model;

import utils.DateUtils;

import java.util.Date;

public class BorrowingAndBillBook implements IModel<BorrowingAndBillBook> {

    private long IDBrrowing;
    private long IDReader;
    private long IDSatff;
    private long IDBook;
    private String nameBook;
    private int quantity;
    private Date borrowDate;
    private Date payDate;
    private float deposit;
    private EPay ePay;

    public BorrowingAndBillBook() {

    }

    public BorrowingAndBillBook(long IDBrrowing, long IDReader, long IDSatff, long IDBook, String nameBook, int quantity, Date borrowDate, Date payDate, float deposit, EPay ePay) {
        this.IDBrrowing = IDBrrowing;
        this.IDReader = IDReader;
        this.IDSatff = IDSatff;
        this.IDBook = IDBook;
        this.nameBook = nameBook;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.payDate = payDate;
        this.deposit = deposit;
        this.ePay = ePay;
    }

    public long getIDBrrowing() {
        return IDBrrowing;
    }

    public void setIDBrrowing(long IDBrrowing) {
        this.IDBrrowing = IDBrrowing;
    }

    public long getIDReader() {
        return IDReader;
    }

    public void setIDReader(long IDReader) {
        this.IDReader = IDReader;
    }

    public long getIDSatff() {
        return IDSatff;
    }

    public void setIDSatff(long IDSatff) {
        this.IDSatff = IDSatff;
    }

    public long getIDBook() {
        return IDBook;
    }

    public void setIDBook(long IDBook) {
        this.IDBook = IDBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public EPay getePay() {
        return ePay;
    }

    public void setePay(EPay ePay) {
        this.ePay = ePay;
    }

    @Override
    public String toString() {
        return getIDBrrowing() + "," + getIDReader() + "," + getIDSatff() + "," + getIDBook() + "," + getNameBook() + "," +
                getQuantity() + "," + DateUtils.format(getBorrowDate()) + "," +
                DateUtils.format(getPayDate()) + "," + getDeposit()+","+getePay();
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        IDBrrowing = Long.parseLong(items[0]);
        IDReader = Long.parseLong(items[1]);
        IDSatff = Long.parseLong(items[2]);
        IDBook = Long.parseLong(items[3]);
        nameBook = items[4];
        quantity = Integer.parseInt(items[5]);
        borrowDate = DateUtils.parse(items[6]);
        payDate = DateUtils.parse(items[7]);
        deposit = Float.parseFloat(items[8]);
        ePay = EPay.getEPayByStatus(items[9]);
    }
}
