package lk.ijse.hotBurger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerDto {

    private int id;
    private String fName;
    private String lName;
    private String address;
    private String mobile;

}
