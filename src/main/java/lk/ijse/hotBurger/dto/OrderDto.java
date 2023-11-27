package lk.ijse.hotBurger.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    int id;
    double subTotal = 0;
    double deliveryCharge;
    double discount;
    double total;
    LocalDate date;
    int customerId;
    String type;
    String description;
    //CustomerDto customerDetail;
    DeliveryDto deliveryDetails;
    List<OrderDetailsDto> orderItem;
}
