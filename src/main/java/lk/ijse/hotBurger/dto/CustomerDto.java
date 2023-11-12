package lk.ijse.hotBurger.dto;

public class CustomerDto {

    private int id;
    private String fName;
    private String lName;
    private String addressLine3;
    private String phone1;

    public CustomerDto(){}

    public CustomerDto(int id, String fName, String lName, String addressLine3, String phone1) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.addressLine3 = addressLine3;
        this.phone1 = phone1;
    }

    public CustomerDto(String fName, String lName, String addressLine3, String phone1) {
        this.fName = fName;
        this.lName = lName;
        this.addressLine3 = addressLine3;
        this.phone1 = phone1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", fNAme='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", phone1='" + phone1 + '\'' +
                '}';
    }
}
