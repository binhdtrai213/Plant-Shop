/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import basicclass.Account;
import dao.AccountDAO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Tester {
    public static void main(String[] args) {
        try {
            int opt = 5;
            switch (opt) {
                case 1:
                    ArrayList<Account> list = AccountDAO.getAccounts();
                    for (Account account : list) {
                        System.out.println(account);
                    }
                    break;
                case 2:
                    Account acc = AccountDAO.getAccounts("user@gmail.com", "12345");
                    if (acc != null) {
                        System.out.println(acc);
                        if (acc.getRole() == 1) {
                            System.out.println("I am a admin");
                        } else {
                            System.out.println("I am a user");
                        }
                    } else {
                        System.out.println("Login failed!");
                    }
                    break;
                case 3:
                    boolean statusUpdate = AccountDAO.updateAccountStatus("admin@gmail.com", 1);
                    if (statusUpdate) 
                        System.out.println("Updated successfully!");
                    else 
                        System.out.println("Updated failed!");
                    break;
                case 4: 
                    boolean statusUpdate2 = AccountDAO.updateAccount("admin@gmail.com", "12345", "binh dep trai", "1234567");
                    if (statusUpdate2) 
                        System.out.println("Updated successfully!");
                    else 
                        System.out.println("Updated failed!");
                    break;
                case 5: 
                    boolean statusInsert = AccountDAO.insertAccount("d@gmail.com", "12345", "something", "45353453", 0 ,0);
                    if (statusInsert) 
                        System.out.println("Inserted successfully!");
                    else 
                        System.out.println("Inserted failed!");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
