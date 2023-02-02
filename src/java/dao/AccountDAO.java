/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utils.DBUtils;
import basicclass.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
// lop nay de chua cac function thuc hien cac cau queries lay data trong DB
public class AccountDAO {

    //ham nay de lay tat ca cac accounts
    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> result = new ArrayList<>();
        //b1: make connection
        try {
            Connection cn = DBUtils.makeConnection();
            //b2: viet sql and exec
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role \n"
                        + "from dbo.Accounts";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                //b3: xu lly dap an
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        result.add(acc);
                    }
                }
                //b4: close connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //ham nay de lay tat ca cac account voi role = 0 or 1
    public static ArrayList<Account> getAccounts(int role) throws Exception {
        ArrayList<Account> result = new ArrayList<>();
        //b1: make connection
        try {
            Connection cn = DBUtils.makeConnection();
            //b2: viet sql and exec
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role \n"
                        + "from dbo.Accounts where role=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, role);
                ResultSet table = pst.executeQuery();
                //b3: xu lly dap an
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
//                        int role = table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        result.add(acc);
                    }
                }
                //b4: close connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //ham nay de lay mot account dua vao email, password va status = 1(active)
    public static Account getAccounts(String email, String password) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select[accID],[email],[password],[fullname],[phone],[status],[role] \n"
                        + "from [dbo].[Accounts]\n"
                        + "where status=1 and email= ? and password= ? COLLATE LATIN1_General_CS_AS ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int accid = rs.getInt("accID");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    acc = new Account(accid, email, password, fullname, phone, status, role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    //ham nay de chen 1 account vao bang accounts
    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus, int newRole) {
        Connection cn = null;
        int status1 = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[Accounts]\n"
                        + "VALUES (?, ?, ?, ?, ?, ?, null)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);
                pst.setInt(5, newSatus);
                pst.setInt(6, newRole);
                status1 = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return status1 != 0;
    }

    //ham nay de sua status cua 1 account khi biet accid
    public static boolean updateAccountStatus(String email, int status) {
        Connection cn = null;
        int status1 = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Accounts]\n"
                        + "SET status=?\n"
                        + "WHERE email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, email);
                status1 = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return status1 != 0;
    }

    //ham nay de sua profile(sua cac cot ngoai tru accid)
    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {
        Connection cn = null;
        int status2 = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Accounts]\n"
                        + "SET password = ?, fullname = ?, phone = ?\n"
                        + "WHERE email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newPassword);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.setString(4, email);
                status2 = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return status2 != 0;
    }
    
    //cap nhat token dua theo email
    public static void updateToken(String token, String email) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Accounts]\n"
                        + "SET token=?\n"
                        + "WHERE email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
                pst.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //ham nay de lay account voi token = ...
    public static Account getAccount(String token) throws Exception {
        Account acc = null;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role \n"
                        + "from dbo.Accounts where token=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet table = pst.executeQuery();
                //b3: xu lly dap an
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        acc = new Account(accid, email, password, fullname, phone, status, role);
                    }
                }
                //b4: close connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return acc;
    }
    
    //check exist email
    public static boolean checkAccount(String email) throws Exception {
        boolean rs = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role \n"
                        + "from dbo.Accounts where email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                //b3: xu lly dap an
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        rs = true;
                    }
                }
                //b4: close connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}

//DELETE FROM table_name WHERE condition;
