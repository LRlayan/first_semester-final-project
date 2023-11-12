package lk.ijse.hotBurger.dto.tm;

public class CustomerTm {
    private int id;
    private String fNAme;
    private String lName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String phone1;
    private String phone2;

    public CustomerTm(){}

    public CustomerTm(int id ,String fNAme, String lName,String addressLine3, String phone1) {
        this.fNAme = fNAme;
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

    public String getfNAme() {
        return fNAme;
    }

    public void setfNAme(String fNAme) {
        this.fNAme = fNAme;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", fNAme='" + fNAme + '\'' +
                ", lName='" + lName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                '}';
    }
}
