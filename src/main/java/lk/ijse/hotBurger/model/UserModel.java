package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.UserDto;

import java.sql.*;
import java.util.ArrayList;

public class UserModel {

    UserDto userDto = new UserDto();

    public static ArrayList<UserDto> getAllUsers() throws SQLException {
        ArrayList<UserDto> arrayList = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * from user";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            UserDto userDto = new UserDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            arrayList.add(userDto);
        }
        return arrayList;
    }

    public boolean updateUser(String confirmUsername,int userId ) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE user SET username = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

       // System.out.println(confirmUsername  + "  "  + oldUsername);
      preparedStatement.setString(1,confirmUsername);
     // preparedStatement.setString(2,oldUsername);
        preparedStatement.setInt(2,userId);

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateUserPassword(String confirmPassword , int userId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString( 1 , confirmPassword);
        preparedStatement.setInt(2,userId);

        return  preparedStatement.executeUpdate() > 0;
    }
}
