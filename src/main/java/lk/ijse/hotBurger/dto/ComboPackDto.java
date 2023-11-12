package lk.ijse.hotBurger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ComboPackDto {
    int id;
    String itemCode;
    String name;
    double unitPrice;
    double unitCost;
    String categoryId;
    private String image;
}
