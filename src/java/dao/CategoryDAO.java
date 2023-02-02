/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import basicclass.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class CategoryDAO {
    //ham nay de lay tat ca cac categories
    public static ArrayList<Category> getCategories() throws Exception {
        ArrayList<Category> result = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select CateID, CateName\n"
                        + "from dbo.Categories";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int id = table.getInt("CateID");
                        String name = table.getString("CateName");
                        Category cate = new Category(id, name);
                        result.add(cate);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
