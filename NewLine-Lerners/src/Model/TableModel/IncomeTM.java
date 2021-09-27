package Model.TableModel;

public class IncomeTM {
       private String pay_ID;
       private String st_ID;
       private String pay_Date;
       private Double amount;

    public IncomeTM() {
    }

    public IncomeTM(String pay_ID, String st_ID, String pay_Date, Double amount) {
        this.pay_ID = pay_ID;
        this.st_ID = st_ID;
        this.pay_Date = pay_Date;
        this.amount = amount;
    }

    public String getPay_ID() {
        return pay_ID;
    }

    public void setPay_ID(String pay_ID) {
        this.pay_ID = pay_ID;
    }

    public String getSt_ID() {
        return st_ID;
    }

    public void setSt_ID(String st_ID) {
        this.st_ID = st_ID;
    }

    public String getPay_Date() {
        return pay_Date;
    }

    public void setPay_Date(String pay_Date) {
        this.pay_Date = pay_Date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IncomeTM{" +
                "pay_ID='" + pay_ID + '\'' +
                ", st_ID='" + st_ID + '\'' +
                ", pay_Date='" + pay_Date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
