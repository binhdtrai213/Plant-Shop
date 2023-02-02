/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action=request.getParameter("action");
            String url="index.jsp";
            switch(action) {
                case "login":
                    url = "LoginServlet";
                    break;
                case "register":
                    url = "registerServlet";
                    break;
                case "search":
                    url = "index.jsp";
                    break;
                case "logout":
                    url = "LogoutServlet";
                    break;
                case "edit":
                    url = "EditAccountServlet";
                    break;
                case "addtocart":
                    url = "AddToCartServlet";
                    break;
                case "viewcart":
                    url = "ViewCart.jsp";
                    break;
                case "update":
                    url = "UpdateCartServlet";
                    break;
                case "delete":
                    url = "DeleteCartServlet";
                    break;
                case "saveOrder":
                    url = "SaveCartServlet";
                    break;
                case "Filter":
                    url = "FilterOrderServlet";
                    break;
                case "manageAcc":
                    url = "ManageAccountServlet";
                    break;
                case "updateStatusAccount":
                    url = "UpdateStatusAccountServlet";
                    break;
                case "searchAccount":
                    url = "SearchAccountServlet";
                    break;
                case "manageOrder":
                    url = "ManageOrderServlet";
                    break;
                case "updateStatusOrder":
                    url = "UpdateStatusOrderServlet";
                    break;
                case "searchOrder":
                    url = "SearchOrderServlet";
                    break;
                case "managePlant":
                    url = "ManagePlantServlet";
                    break;
                case "updateStatusPlant":
                    url = "UpdateStatusPlantServlet";
                    break;
                case "searchPlant":
                    url = "SearchPlantServlet";
                    break;
                case "manageCategory":
                    url = "ManageCategoryServlet";
                    break;
                case "searchCategory":
                    url = "SearchCategoryServlet";
                    break;
                case "viewOrderDetail":
                    url = "ViewOrderDetailServlet";
                    break;
            }
            RequestDispatcher rd=request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
