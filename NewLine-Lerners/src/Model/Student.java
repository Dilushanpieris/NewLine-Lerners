package Model;

public class Student {
     private String st_ID;
     private String name;
     private int age;
     private String vehicle_Type;
     private String email;
     private String address;
     private String telephone;

    public Student() {
    }

    public Student(String st_ID, String name, int age, String vehicle_Type, String email, String address, String telephone) {
        this.st_ID = st_ID;
        this.name = name;
        this.age = age;
        this.vehicle_Type = vehicle_Type;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
    }

    public String getSt_ID() {
        return st_ID;
    }

    public void setSt_ID(String st_ID) {
        this.st_ID = st_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVehicle_Type() {
        return vehicle_Type;
    }

    public void setVehicle_Type(String vehicle_Type) {
        this.vehicle_Type = vehicle_Type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}