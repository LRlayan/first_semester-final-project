package lk.ijse.hotBurger.dto;

import com.jfoenix.controls.JFXCheckBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class IngrediantsDto {
    private JFXCheckBox ketchup;
    private JFXCheckBox mayonnaise;
    private JFXCheckBox sweet;
    private JFXCheckBox bbq;
    private JFXCheckBox tomato;

}
