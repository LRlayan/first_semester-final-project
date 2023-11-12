package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.AddNewItemDto;
import lk.ijse.hotBurger.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

   /* public boolean saveCustomer(CustomerDto customerDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer(id,firstName,lastName,address,phone) VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,customerDto.getId());
        preparedStatement.setString(2,customerDto.getfName());
        preparedStatement.setString(3,customerDto.getlName());
        preparedStatement.setString(4,customerDto.getAddressLine3());
        preparedStatement.setString(5,customerDto.getPhone1());

        boolean isSaved = preparedStatement.executeUpdate() > 0;

        return isSaved;
    }

   /* public List<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<CustomerDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            dtoList.add(
                    new CustomerDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }*/
}
