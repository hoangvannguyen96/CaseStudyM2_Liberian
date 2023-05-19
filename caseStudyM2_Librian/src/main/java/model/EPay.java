package model;

public enum EPay {
    PAID(1, "Đã thanh toán"), UNPAID(2, "Chưa thanh toán");
    private int IDPay;
    private String status;

    EPay(int IDPay, String status) {
        this.IDPay = IDPay;
        this.status = status;
    }

    public int getIDPay() {
        return IDPay;
    }

    public void setIDPay(int IDPay) {
        this.IDPay = IDPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static EPay getEPayById(int IDPay) {
        for (EPay ePay : EPay.values()) {
            if (ePay.getIDPay() == IDPay) {
                return ePay;
            }
        }
        return null;
    }
    public static EPay getEPayByStatus(String status) {
        for (EPay ePay : EPay.values()) {
            if (ePay.toString().equals(status)) {
                return ePay;
            }
        }
        return null;
    }
}
