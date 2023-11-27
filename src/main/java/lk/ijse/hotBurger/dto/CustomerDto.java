package lk.ijse.hotBurger.dto;

public class CustomerDto {

    private int id;
    private String fName;
    private String lName;
    private String address;
    private String newAddress;
    private String mobileNo;
    private String additionalMobileNo;

    public CustomerDto(){}

    public CustomerDto(int id, String fName, String lName, String address, String mobileNo) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.mobileNo = mobileNo;
    }

    public CustomerDto(String fName, String lName, String mobileNo, String additionalMobileNo , String address , String newAddress){
        this.fName = fName;
        this.lName = lName;
        this.mobileNo = mobileNo;
        this.address = address;
        this.newAddress = newAddress;
        this.additionalMobileNo = additionalMobileNo;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAdditionalMobileNo() {
        return additionalMobileNo;
    }

    public void setAdditionalMobileNo(String additionalMobileNo) {
        this.additionalMobileNo = additionalMobileNo;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", fNAme='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", addressLine3='" + address + '\'' +
                ", phone1='" + mobileNo + '\'' +
                '}';
    }
}
