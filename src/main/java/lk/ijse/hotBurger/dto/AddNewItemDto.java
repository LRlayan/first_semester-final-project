package lk.ijse.hotBurger.dto;

public class AddNewItemDto {
    private String itemCode;
    private String name;
    private double unitPrice;
    private double unitCost;

    private String categoryId;
    private int id;

    public AddNewItemDto(){

    }
    public AddNewItemDto(String itemCode, String name, double unitPrice, double unitCost , String categoryId) {
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.categoryId = categoryId;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
