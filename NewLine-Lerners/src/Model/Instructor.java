package Model;

public class Instructor {
     private String ins_id;
     private String name;
     private String address;
     private String tel;

    public Instructor() {
    }

    public Instructor(String ins_id, String name, String address, String tel) {
        this.ins_id = ins_id;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public String getIns_id() {
        return ins_id;
    }

    public void setIns_id(String ins_id) {
        this.ins_id = ins_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "ins_id='" + ins_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
