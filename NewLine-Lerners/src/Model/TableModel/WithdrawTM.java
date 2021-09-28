package Model.TableModel;

public class WithdrawTM {
    private String with_id;
    private String with_Date;
    private double with_Amount;
    private double with_Balance;

    public WithdrawTM() {
    }

    public WithdrawTM(String with_id, String with_Date, double with_Amount, double with_Balance) {
        this.with_id = with_id;
        this.with_Date = with_Date;
        this.with_Amount = with_Amount;
        this.with_Balance = with_Balance;
    }

    public String getWith_id() {
        return with_id;
    }

    public void setWith_id(String with_id) {
        this.with_id = with_id;
    }

    public String getWith_Date() {
        return with_Date;
    }

    public void setWith_Date(String with_Date) {
        this.with_Date = with_Date;
    }

    public double getWith_Amount() {
        return with_Amount;
    }

    public void setWith_Amount(double with_Amount) {
        this.with_Amount = with_Amount;
    }

    public double getWith_Balance() {
        return with_Balance;
    }

    public void setWith_Balance(double with_Balance) {
        this.with_Balance = with_Balance;
    }

    @Override
    public String toString() {
        return "WithdrawTM{" +
                "with_id='" + with_id + '\'' +
                ", with_Date='" + with_Date + '\'' +
                ", with_Amount=" + with_Amount +
                ", with_Balance=" + with_Balance +
                '}';
    }
}
