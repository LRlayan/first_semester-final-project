package lk.ijse.hotBurger.model;

import javafx.scene.control.Alert;
import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.CustomerDto;
import lk.ijse.hotBurger.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public CustomerDto saveCustomer(CustomerDto customerDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,0);
        preparedStatement.setString(2,customerDto.getFName());
        preparedStatement.setString(3,customerDto.getLName());
        preparedStatement.setString(4,customerDto.getAddress());
        preparedStatement.setString(5,customerDto.getMobile());

        int affectedRow = preparedStatement.executeUpdate();
        if (affectedRow > 0){
            new Alert(Alert.AlertType.INFORMATION,"Customer created successfully!");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                int generatedId = generatedKeys.getInt(1);
                customerDto.setId(generatedId);
                return customerDto;
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Error");
        }
        return null;
    }

    public CustomerDto dineCustomerSave(CustomerDto customerDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,0);
        preparedStatement.setString(2,customerDto.getFName());
        preparedStatement.setString(3,customerDto.getLName());
        preparedStatement.setString(4,"No Address");
        preparedStatement.setString(5,customerDto.getMobile());

        int affectedRow = preparedStatement.executeUpdate();
        if (affectedRow > 0){
            new Alert(Alert.AlertType.INFORMATION,"Customer created successfully!");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()){
                int generatedId = generatedKeys.getInt(1);
                customerDto.setId(generatedId);
                return customerDto;
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Error");
        }
        return null;
    }

    public List<CustomerDto> getAllCustomers() throws SQLException {
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            CustomerDto customerDto = new CustomerDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }
}
