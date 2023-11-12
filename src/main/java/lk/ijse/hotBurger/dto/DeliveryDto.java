package lk.ijse.hotBurger.dto;

public class DeliveryDto {

    private int id;
    private String address;
    private String additionalMobileNo;
    private int customerId;

    public DeliveryDto(){}
    public DeliveryDto( String address, String additionalMobileNo ) {
        this.address = address;
        this.additionalMobileNo = additionalMobileNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionalMobileNo() {
        return additionalMobileNo;
    }

    public void setAdditionalMobileNo(String additionalMobileNo) {
        this.additionalMobileNo = additionalMobileNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "DeliveryDto{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", additionalMobileNo='" + additionalMobileNo + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
