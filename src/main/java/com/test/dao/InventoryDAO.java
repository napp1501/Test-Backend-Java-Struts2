package com.test.dao;

import com.test.model.Inventory;
import com.test.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    public List<Inventory> getDataInventory() throws Exception{
        List<Inventory> inventories = new ArrayList<>();
        try{
            Connection conn = DBConn.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT inventory.id, item.name, inventory.qty, inventory.type, item.id AS item_id FROM inventory JOIN item ON inventory.item_id = item.id");

            while(rs.next()) {

                Inventory inventory = new Inventory();

                inventory.setId(rs.getInt("id"));
                inventory.setName(rs.getString("name"));
                inventory.setQty(rs.getInt("qty"));
                inventory.setType(rs.getString("type"));
                inventory.setItem_id(rs.getInt("item_id"));

                inventories.add(inventory);
            }

            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return inventories;
    }

    public void saveData(Integer itemId, Integer qty, String type) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "INSERT INTO INVENTORY (item_id, qty, `type`) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);
            ps.setInt(2, qty);
            ps.setString(3, type);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void editData(Integer id, Integer itemId, Integer qty, String type) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "UPDATE INVENTORY SET item_id = ?, qty = ?, `type` = ? WHERE  INVENTORY.id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, itemId);
        ps.setInt(2, qty);
        ps.setString(3, type);
        ps.setInt(4, id);

        ps.executeUpdate();

        ps.close();
        conn.close();

    }

    public void deleteData(Integer id) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "DELETE FROM INVENTORY where INVENTORY.id = ?";

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

    public Inventory getDetailById(Integer id) throws Exception {
        Inventory inventory = new Inventory();

        Connection conn = DBConn.getConnection();

        String sql = "SELECT * FROM Inventory WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();


        if(rs.next()){
            inventory.setId(rs.getInt("id"));
            inventory.setItem_id(rs.getInt("itemId"));
            inventory.setQty(rs.getInt("qty"));
            inventory.setType(rs.getString("type"));
        }

        conn.close();

        return inventory;
    }

}
