package lk.ijse.hotBurger.dto.tm;

public class ItemTm {
        int id;
        String itemCode;
        String name;
        double unitPrice;
        double unitCost;
        String categoryId;

    public ItemTm(){}
    public ItemTm(int id, String itemCode, String name, double unitPrice, double unitCost, String categoryId) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "ItemTm{" +
                "id=" + id +
                ", itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitCost=" + unitCost +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
