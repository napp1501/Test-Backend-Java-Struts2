package com.test.dao;

import com.test.model.Order;
import com.test.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> getDataOrder() throws Exception{
        List<Order> orders = new ArrayList<>();
        try{
            Connection conn = DBConn.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT orders.order_no, item.name, orders.qty, orders.item_id, orders.id FROM orders JOIN item on orders.item_id = item.id");

            while(rs.next()) {

                Order order = new Order();

                order.setOrder_no(rs.getString("order_no"));
                order.setName(rs.getString("name"));
                order.setQty(rs.getInt("qty"));
                order.setItem_id(rs.getInt("item_id"));
                order.setId(rs.getInt("id"));

                orders.add(order);
            }

            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return orders;
    }

    public void saveDataOrder(Integer itemId, Integer qty) throws Exception {

        Connection conn = DBConn.getConnection();

        String sql = "INSERT INTO orders (item_id, qty) VALUES (?, ?)";

        PreparedStatement ps = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
        );

        ps.setInt(1, itemId);
        ps.setInt(2, qty);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            int id = rs.getInt(1);

            String orderNo = "O" + id;

            PreparedStatement update = conn.prepareStatement(
                    "UPDATE orders SET order_no=? WHERE id=?"
            );

            update.setString(1, orderNo);
            update.setInt(2, id);

            update.executeUpdate();
            update.close();
        }

        rs.close();
        ps.close();
        conn.close();
    }

    public void editDataOrder(Integer id, String orderNo, Integer item_id, Integer qty) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "UPDATE orders SET order_no = ?, item_id = ?, qty = ? WHERE  orders.id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, orderNo);
        ps.setInt(2, item_id);
        ps.setInt(3, qty);
        ps.setInt(4, id);

        ps.executeUpdate();

        ps.close();
        conn.close();

    }

    public void deleteDataOrder(Integer id) throws Exception{
        Connection conn = DBConn.getConnection();

        String sql = "DELETE FROM orders where id = ?";

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

    public Order getDetailById(Integer id) throws Exception {
        Order order = new Order();

        Connection conn = DBConn.getConnection();

        String sql = "SELECT * FROM Orders WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();


        if(rs.next()){
            order.setId(rs.getInt("id"));
            order.setOrder_no(rs.getString("orderNo"));
            order.setItem_id(rs.getInt("itemId"));
            order.setQty(rs.getInt("qty"));
        }

        conn.close();

        return order;
    }
}
