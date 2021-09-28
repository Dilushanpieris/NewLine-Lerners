package Model;

public class Withdraw {
      private String with_id;
      private String with_date;
      private double with_Amount;
      private double with_balance;

    public Withdraw(String with_id, String with_date, double with_Amount, double with_balance) {
        this.with_id = with_id;
        this.with_date = with_date;
        this.with_Amount = with_Amount;
        this.with_balance = with_balance;
    }

    public Withdraw() {
    }

    public String getWith_id() {
        return with_id;
    }

    public void setWith_id(String with_id) {
        this.with_id = with_id;
    }

    public String getWith_date() {
        return with_date;
    }

    public void setWith_date(String with_date) {
        this.with_date = with_date;
    }

    public double getWith_Amount() {
        return with_Amount;
    }

    public void setWith_Amount(double with_Amount) {
        this.with_Amount = with_Amount;
    }

    public double getWith_balance() {
        return with_balance;
    }

    public void setWith_balance(double with_balance) {
        this.with_balance = with_balance;
    }

    @Override
    public String toString() {
        return "Withdraw{" +
                "with_id='" + with_id + '\'' +
                ", with_date='" + with_date + '\'' +
                ", with_Amount=" + with_Amount +
                ", with_balance=" + with_balance +
                '}';
    }
}
