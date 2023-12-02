package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.OrderDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public OrderDto saveOrder(OrderDto order) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, 0);
        preparedStatement.setString(2, order.getType());
        preparedStatement.setString(3, order.getDate());
        //preparedStatement.setString(4,order.getDescription());
        preparedStatement.setDouble(4, order.getSubTotal());
        preparedStatement.setDouble(5, order.getDiscount());
        preparedStatement.setDouble(6, order.getDeliveryCharge());
        preparedStatement.setDouble(7, order.getTotal());
        preparedStatement.setInt(8, order.getCustomerId());

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

    public List<OrderDto> loadAllOrders() throws SQLException {
        ArrayList<OrderDto> orders = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM orders";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            OrderDto orderDto = new OrderDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getInt(8)
            );
            orders.add(orderDto);
        }
        return orders;
    }

    public OrderDto totalSalesAmount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT SUM(total) FROM orders";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        OrderDto orderDtos = new OrderDto();
        while (resultSet.next()){
           orderDtos.setTotal(resultSet.getDouble(1));
        }
        return orderDtos;
    }

    public int totalSales() throws SQLException {
        int totalSales = 0;
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(id) FROM orders";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        OrderDto orderDto = new OrderDto();
        while (resultSet.next()){
           totalSales = resultSet.getInt(1);
        }
        return totalSales;
    }

    public OrderDto profit() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "";
        return null;
    }
}
