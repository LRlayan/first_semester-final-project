package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.AddNewItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewItemModel {
    public static boolean addNewItem(AddNewItemDto newItemDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO item VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,newItemDto.getId());
        preparedStatement.setString(2 , newItemDto.getItemCode());
        preparedStatement.setString(3 , newItemDto.getName());
        preparedStatement.setDouble(4 , newItemDto.getUnitPrice());
        preparedStatement.setDouble(5 , newItemDto.getUnitCost());
        preparedStatement.setString(6 , newItemDto.getCategoryId());
        preparedStatement.setString(7,"null");

        return preparedStatement.executeUpdate() > 0;
    }
}
