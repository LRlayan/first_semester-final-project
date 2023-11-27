package lk.ijse.hotBurger.dto.tm;

import com.jfoenix.controls.JFXButton;

public class ItemTm {
        int id;
        String itemCode;
        String name;
        double unitPrice;
        double unitCost;
        String categoryId;
        String image;

        JFXButton update;
        JFXButton delete;

    public ItemTm(int id, String itemCode, String name, double unitPrice, double unitCost, String categoryId ,JFXButton update, JFXButton delete ) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.categoryId = categoryId;
        this.image = image;
        this.update = update;
        this.delete = delete;

    }

    public ItemTm(double unitPrice , String name){
        this.unitPrice = (unitPrice);
        this.name = name;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public JFXButton getUpdate() {
        return update;
    }

    public void setUpdate(JFXButton update) {
        this.update = update;
    }

    public JFXButton getDelete() {
        return delete;
    }

    public void setDelete(JFXButton delete) {
        this.delete = delete;
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
