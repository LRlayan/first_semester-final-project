package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.OrderDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDetailsModel {

    public void saveOrderDetail(OrderDetailsDto orderDetailsDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO orderdetail VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,0);
        preparedStatement.setString(2, "DEFAULT");
        preparedStatement.setInt(3,orderDetailsDto.getQty());
        preparedStatement.setDouble(4,orderDetailsDto.getTotal());
        preparedStatement.setInt(5,orderDetailsDto.getOrderId());
        preparedStatement.setDouble(6,orderDetailsDto.getUnitCost());
        preparedStatement.setString(7,orderDetailsDto.getItemCode());
        preparedStatement.setString(8,orderDetailsDto.getName());

        preparedStatement.executeUpdate();

    }
}
