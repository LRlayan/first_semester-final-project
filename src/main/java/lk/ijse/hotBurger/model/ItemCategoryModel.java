package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.ItemCategoryDto;
import lk.ijse.hotBurger.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryModel {

    public List<ItemCategoryDto> loadAllItemCategory() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM itemCategory";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        List<ItemCategoryDto> itemCategoryList = new  ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            itemCategoryList.add(new ItemCategoryDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }

        return itemCategoryList;
    }
}
