package Model;

public class Payment {
    private String payID;
    private String date;
    private double amount;
    private String stId;

    public Payment() {
    }

    public Payment(String payID, String date, double amount, String stId) {
        this.payID = payID;
        this.date = date;
        this.amount = amount;
        this.stId = stId;
    }

    public String getPayID() {
        return payID;
    }

    public void setPayID(String payID) {
        this.payID = payID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payID='" + payID + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", stId='" + stId + '\'' +
                '}';
    }
}
