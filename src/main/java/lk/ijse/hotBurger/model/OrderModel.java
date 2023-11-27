package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.OrderDto;

import java.awt.image.DataBuffer;
import java.sql.*;

public class OrderModel {
    public OrderDto saveOrder(OrderDto order) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql , PreparedStatement.NO_GENERATED_KEYS);

        preparedStatement.setInt(1,order.getCustomerId());
        preparedStatement.setString(2,order.getType());
        preparedStatement.setDate(3, Date.valueOf(order.getDate()));
        preparedStatement.setString(4,order.getDescription());
        preparedStatement.setDouble(5,order.getSubTotal());
        preparedStatement.setDouble(6,order.getDiscount());
        preparedStatement.setDouble(7,order.getDeliveryCharge());
        preparedStatement.setDouble(8,order.getTotal());
        preparedStatement.setInt(9,order.getCustomerId());

        preparedStatement.executeUpdate();

        int affectedRow = preparedStatement.executeUpdate();
        if (affectedRow > 0){
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                int generatedId = generatedKeys.getInt(1);
                order.setId(generatedId);
                return order;
            }
        }
        return null;
    }
}
