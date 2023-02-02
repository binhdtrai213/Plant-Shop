/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import basicclass.Order;
import basicclass.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {
    public static ArrayList<Order> getOrders() throws Exception {
        ArrayList<Order> result = new ArrayList<>();
        //bước 1 make connection
        try {
            Connection cn = DBUtils.makeConnection();
            //bước 2 viết sql and exec
            if (cn != null) {
                String sql = "select [dbo].[Orders].[OrderID],[OrdDate],[shipDate],[dbo].[Orders].[status],[dbo].[Orders].[AccID] \n"
                        + "from [dbo].[Orders]";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                //bước 3 xử lý đáp án
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order ord = new Order(orderID, orderDate, shipDate, status, accID);
                        result.add(ord);
                    }
                }
                //bước 4 close connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> result = new ArrayList<>();
        //bước 1 make connection
        try {
            Connection cn = DBUtils.makeConnection();
            //bước 2 viết sql and exec
            if (cn != null) {
                String sql = "select [dbo].[Orders].[OrderID],[OrdDate],[shipDate],[dbo].[Orders].[status],[dbo].[Orders].[AccID] \n"
                        + "from [dbo].[Orders] join [dbo].[Accounts] on [dbo].[Orders].[AccID] = [dbo].[Accounts].[accID]\n"
                        + "where [Accounts].[email]=? COLLATE LATIN1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                //bước 3 xử lý đáp án
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order ord = new Order(orderID, orderDate, shipDate, status, accID);
                        result.add(ord);
                    }
                }
                //bước 4 close connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws Exception {
        Connection cn = null;
        ArrayList<OrderDetail> list = new ArrayList<>();
        //bước 1 make connection
        try {
            cn = DBUtils.makeConnection();
            //bước 2 viết sql and exec
            if (cn != null) {
                String sql = "select [DetailId], [OrderID], [PID], [PName], [price], [imgPath], [quantity]\n"
                        + "from [dbo].[OrderDetails], [dbo].[Plants]\n"
                        + "where [OrderID] = ? and [FID] = [PID]";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                //bước 3 xử lý đáp án
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("detailID");
                        int PlantID = rs.getInt("PID");
                        String PlantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail orderDetail = new OrderDetail(detailID, orderID, PlantID, PlantName, price, imgPath, quantity);
                        list.add(orderDetail);
                    }
                }
                //bước 4 close connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //ham nay de sua status cua 1 order khi biet orderID
    public static int updateOrderStatus(String orderid, int status) {
        int status1 = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Orders]\n"
                        + "SET status = ?\n"
                        + "WHERE OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, orderid);
                status1 = pst.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status1;
    }
    
    //ham nay de sua status cua 1 order khi biet orderID
    public static int updateOrderStatus2(String orderid, int status) {
        int status1 = 0;
        Date today = new Date(System.currentTimeMillis());
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Orders]\n"
                        + "SET status = ?, OrdDate = ?\n"
                        + "WHERE OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setDate(2, today);
                pst.setString(3, orderid);
                status1 = pst.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status1;
    }
    
    //ham nay de sua status cua 1 order khi biet orderID
    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false); // off auto commit
                //get accid by email
                String sql = "select accID from [dbo].[Accounts] where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if(rs != null && rs.next()) accid = rs.getInt("accID");
                //insert a new order
                System.out.println("accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date:" + d);
                sql = "insert Orders(OrdDate, status, AccID) values(?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                //get order id that is the lagest number
                sql = "select top 1 orderID from [dbo].[Orders] order by OrderID desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs != null && rs.next()) orderid = rs.getInt("orderID");
                //insert order details
                System.out.println("orderid:" + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?, ?, ?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            }
            else {
                System.out.println("Can not insert order!");
            }
        } catch (Exception e) {
            try {
                if(cn != null) cn.rollback(); 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
