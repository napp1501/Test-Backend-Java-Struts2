package com.test.dao;

import com.test.model.Item;
import com.test.model.Report;
import com.test.util.DBConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    public List<Report> getAllItems() throws Exception {

        List<Report> reports = new ArrayList<>();
        try {
            Connection conn = DBConn.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT i.id, i.name, i.price, \n" +
                    "    SUM(o.qty) total_order, \n" +
                    "    i.stock, \n" +
                    "    SUM(o.qty * i.price) revenue \n" +
                    "    FROM item i \n" +
                    "   JOIN orders o ON i.id=o.item_id  \n" +
                    "    GROUP BY i.id,i.name,i.price,i.stock \n" +
                    "    ORDER BY revenue DESC");

            while(rs.next()) {

                Report report = new Report();

                report.setRevenue(rs.getInt("revenue"));
                report.setTotalOrder(rs.getInt("total_order"));
                report.setName(rs.getString("name"));
                report.setPrice(rs.getInt("price"));
                report.setStock(rs.getInt("stock"));

                reports.add(report);
            }

            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return reports;
    }

    public int getTotalRevenue() throws Exception {
        Integer totalRevenue = 0;

        try {
            Connection conn = DBConn.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SUM(o.qty * i.price) AS total_revenue FROM orders o JOIN item i ON o.item_id = i.id");

            while(rs.next()) {

                totalRevenue = rs.getInt("total_revenue");

            }

            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return totalRevenue;
    }

    public int getTotalInven() throws Exception {
        Integer totalInventory = 0;

        try {
            Connection conn = DBConn.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SUM(stock) AS total_inventory FROM item");

            while(rs.next()) {

                totalInventory = rs.getInt("total_inventory");

            }

            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return totalInventory;
    }

    public int getTotalOrder() throws Exception {
        Integer totalOrder = 0;

        try {
            Connection conn = DBConn.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT SUM(qty) AS total_order FROM orders");

            while(rs.next()) {

                totalOrder = rs.getInt("total_order");

            }

            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return totalOrder;
    }
}
