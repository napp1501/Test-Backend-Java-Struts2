package com.test.dao;
import com.test.model.Item;
import com.test.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class ItemDAO {

    public List<Item> getAllItems() throws Exception {

        List<Item> items = new ArrayList<>();
        try {
            Connection conn = DBConn.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM item");

            while(rs.next()) {

                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setStock(rs.getInt("stock"));

                items.add(item);
            }

            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return items;
    }

    public void saveData(String name, Integer price, Integer stock) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "INSERT INTO item (name, price, stock) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, stock);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void editData(Integer id, String name, Integer price, Integer stock) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "UPDATE ITEM SET name = ?, price = ?, stock = ? WHERE  ITEM.id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, price);
        ps.setInt(3, stock);
        ps.setInt(4, id);

        ps.executeUpdate();

        ps.close();
        conn.close();

    }

    public void deleteData(Integer id) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "DELETE FROM item where item.id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public int getStock(Integer itemId) throws Exception {

        Connection conn = DBConn.getConnection();

        String sql = "SELECT stock FROM item WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, itemId);

        ResultSet rs = ps.executeQuery();

        int stock = 0;

        if(rs.next()){
            stock = rs.getInt("stock");
        }

        conn.close();

        return stock;
    }

    public Item getNameById(Integer itemId) throws Exception {
        Item item = new Item();

        Connection conn = DBConn.getConnection();

        String sql = "SELECT * FROM item WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, itemId);

        ResultSet rs = ps.executeQuery();


        if(rs.next()){
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getInt("price"));
            item.setStock(rs.getInt("stock"));
        }

        conn.close();

        return item;
    }
}
