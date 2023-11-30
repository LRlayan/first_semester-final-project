package lk.ijse.hotBurger.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderTm {
   private int id;
   private double subTotal = 0;
   private double deliveryCharge;
   private double discount;
   private double total;
   private String date;
   private int customerId;
   private String type;
}
