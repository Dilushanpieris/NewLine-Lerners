package Model;

public class Teacher {
    private String t_id;
    private String name;
    private String address;
    private String tel;

    public Teacher() {
    }

    public Teacher(String t_id, String name, String address, String tel) {
        this.t_id = t_id;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
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
        return "Teacher{" +
                "t_id='" + t_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
