package lk.ijse.hotBurger.model;

import lk.ijse.hotBurger.db.DbConnection;
import lk.ijse.hotBurger.dto.ItemDto;
import lk.ijse.hotBurger.dto.tm.ItemTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {


    public static List<ItemDto> loadAllItem() {
       ArrayList<ItemDto> itemDtos = new ArrayList<>();
       try {
           Connection connection = DbConnection.getInstance().getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM item");
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               ItemDto itemDto = new ItemDto(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getString(6),resultSet.getString(7));
               itemDtos.add(itemDto);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return itemDtos;
       }
    public static List<ItemDto> loadAllItemCategoryVise(int catID) {
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM item WHERE categoryId = ?");

            preparedStatement.setInt(1,catID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ItemDto itemDto = new ItemDto(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getString(6),resultSet.getString(7));
                itemDtos.add(itemDto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemDtos;
    }

    public boolean updateItem(ItemDto itemDto) throws SQLException {
       Connection connection = DbConnection.getInstance().getConnection();
       String sql = "UPDATE item SET name = ?, itemCode = ?,unitPrice = ?,unitCost = ?,categoryId = ? WHERE id = ?";

       PreparedStatement preparedStatement = connection.prepareStatement(sql);
       preparedStatement.setString(1,itemDto.getName());
        preparedStatement.setString(2,itemDto.getItemCode());
        preparedStatement.setDouble(3,itemDto.getUnitPrice());
        preparedStatement.setDouble(4,itemDto.getUnitCost());
        preparedStatement.setString(5, (itemDto.getCategoryId()));
       preparedStatement.setInt(6,itemDto.getId());

       return preparedStatement.executeUpdate() > 0;
    }
}
