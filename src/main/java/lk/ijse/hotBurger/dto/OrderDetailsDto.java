package lk.ijse.hotBurger.dto;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetailsDto {
    int id;
    String size;
    int qty;
    double total;
    int orderId;
    double unitCost;
    String itemCode;
    String name;
    double unitPrice;

    public OrderDetailsDto(int id, String itemCode, String name, double unitPrice, int qty, double total) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
    }


}
