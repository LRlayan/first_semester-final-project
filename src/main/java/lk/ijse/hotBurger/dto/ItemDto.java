package lk.ijse.hotBurger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ItemDto {
    int id;
    String itemCode;
    String name;
    double unitPrice;
    double unitCost;
    String categoryId;
    private String image;

}
