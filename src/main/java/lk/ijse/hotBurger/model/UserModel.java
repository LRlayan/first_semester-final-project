package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.UserDto;
import lk.ijse.hotBurger.dto.tm.UserTm;

import java.sql.*;
import java.util.ArrayList;

public class UserModel {

   public void getAllUsers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT username,password FROM user where id = 1";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
           String dbName = resultSet.getString(1);
           String dbPassword =resultSet.getString(2);
            UserDto userDto = new UserDto();
            userDto.setUsername(dbName);
            userDto.setPassword(dbPassword);
        }
    }
}
