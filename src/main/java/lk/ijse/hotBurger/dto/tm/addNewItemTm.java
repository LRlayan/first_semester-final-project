package lk.ijse.hotBurger.dto.tm;

public class addNewItemTm {
    private String itemCode;
    private String name;
    private String unitPrice;
    private String unitCost;

    public addNewItemTm(){

    }
    public addNewItemTm(String itemCode, String name, String unitPrice, String unitCost) {
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public String toString() {
        return "addNewItemDto{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unitCost='" + unitCost + '\'' +
                '}';
    }
}
