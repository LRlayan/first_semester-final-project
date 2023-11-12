package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.DeliveryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryModel {

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
