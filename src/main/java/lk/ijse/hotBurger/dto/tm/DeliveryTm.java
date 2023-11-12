package lk.ijse.hotBurger.dto.tm;

public class DeliveryTm {
    private String addressL1;
    private String addressL2;
    private String addressL3;
    private String phone2;

    public DeliveryTm(){}
    public DeliveryTm(String addressL1, String addressL2, String addressL3, String phone2) {
        this.addressL1 = addressL1;
        this.addressL2 = addressL2;
        this.addressL3 = addressL3;
        this.phone2 = phone2;
    }

    public String getAddressL1() {
        return addressL1;
    }

    public void setAddressL1(String addressL1) {
        this.addressL1 = addressL1;
    }

    public String getAddressL2() {
        return addressL2;
    }

    public void setAddressL2(String addressL2) {
        this.addressL2 = addressL2;
    }

    public String getAddressL3() {
        return addressL3;
    }

    public void setAddressL3(String addressL3) {
        this.addressL3 = addressL3;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public String toString() {
        return "DeliveryDto{" +
                "addressL1='" + addressL1 + '\'' +
                ", addressL2='" + addressL2 + '\'' +
                ", addressL3='" + addressL3 + '\'' +
                ", phone2='" + phone2 + '\'' +
                '}';
    }
}
