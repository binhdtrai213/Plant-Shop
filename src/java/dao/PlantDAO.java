/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import basicclass.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID', CateName\n"
                        + "from [dbo].[Plants] join [dbo].[Categories] \n"
                        + "on Plants.CateID = Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "where Plants.Pname like ?";
                } else {
                    sql = sql + "where CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("cateid");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //get plant by pid
    public static Plant getPlants(String pid) {
        Plant result = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID', CateName\n"
                        + "from [dbo].[Plants] join [dbo].[Categories] \n"
                        + "on Plants.CateID = Categories.CateID\n" 
                        + "where Plants.PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, pid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("cateid");
                        String catename = rs.getString("CateName");
                        result = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    //change status plant by pid
    public static void updateStatusPlant(String pid, int status) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Plants]\n"
                        + "SET status=?\n"
                        + "WHERE Plants.PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, pid);
                pst.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
