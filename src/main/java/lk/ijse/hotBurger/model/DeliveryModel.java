package lk.ijse.hotBurger.model;

import javafx.scene.control.Alert;
import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.DeliveryDto;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {
    public DeliveryDto saveDelivery(DeliveryDto deliveryDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO deliveryDetail VALUES(?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,0);
        preparedStatement.setString(2,deliveryDto.getAddress());
        preparedStatement.setString(3,deliveryDto.getAdditionalMobileNo());
        preparedStatement.setInt(4,deliveryDto.getCustomerId());

        int affectedRow = preparedStatement.executeUpdate();
        if (affectedRow > 0){
            new Alert(Alert.AlertType.INFORMATION,"Delivery details added successfully!");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                int generatedId = generatedKeys.getInt(1);
                deliveryDto.setId(generatedId);
                return deliveryDto;
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Error!!!");
        }
        return null;
    }

  /*  public boolean deliveryCustomerDetailsSave(DeliveryDto deliveryDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO deliveryDetail(id,addressLane1,addressLane2,addressLane3,phone,customerId) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,0);
        preparedStatement.setString(2,deliveryDto.getAddressL1());
        preparedStatement.setString(3,deliveryDto.getAddressL2());
        preparedStatement.setString(4,deliveryDto.getAddressL3());
        preparedStatement.setString(5,deliveryDto.getPhone2());
        preparedStatement.setInt(6,deliveryDto.getCustomerId());

        boolean isSaved = preparedStatement.executeUpdate() > 0;

        return isSaved;
    }*/
}
