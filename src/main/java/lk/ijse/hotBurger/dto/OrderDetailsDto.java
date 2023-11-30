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
    String itemCode;
    String name;
    double unitPrice;
    int qty;
    double Total;
}
