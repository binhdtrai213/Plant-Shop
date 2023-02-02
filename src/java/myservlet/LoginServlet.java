/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import basicclass.Account;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

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
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String save = request.getParameter("savelogin");
            Account acc = null;
            try {
                if (email == null || email.equals("") || password == null || password.equals("")) {
                    Cookie[] c = request.getCookies();
                    String token = "";
                    if (c != null) {
                        for (Cookie a : c) {
                            if (a.getName().equals("selector")) {
                                token = a.getValue();
                            }
                        }
                    }
                    if (!token.equals("")) {
                        acc = AccountDAO.getAccount(token);
                        if(acc.getRole() == 1)
                            response.sendRedirect("AdminPage.jsp");
                        else
                            response.sendRedirect("personalPage.jsp");
                    } else {
                        request.setAttribute("WARNING", "Email or Password wrong!");
                        request.setAttribute("email", email);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    acc = AccountDAO.getAccounts(email, password);
                    if (acc != null) {
                        HttpSession session = request.getSession(true);
                        if (acc.getRole() == 1) {
                            if(session != null) {
                                session.setAttribute("name", acc.getFullname());
                                session.setAttribute("phone", acc.getPhone());
                                session.setAttribute("email", email);
                                session.setAttribute("password", password);
                                session.setAttribute("role", "admin");
                                if(save != null) {
                                    String token = "ABC123"; //sample
                                    AccountDAO.updateToken(token, email);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 2); //sample
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("AdminPage.jsp");
                            }
                        } else {
                            if (session != null) {
                                session.setAttribute("name", acc.getFullname());
                                session.setAttribute("phone", acc.getPhone());
                                session.setAttribute("email", email);
                                session.setAttribute("password", password);
                                session.setAttribute("role", "user");
                                if(save != null) {
                                    String token = "ABC123"; //sample
                                    AccountDAO.updateToken(token, email);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 2); //sample
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("personalPage.jsp");
                            }
                        }
                    }
                    else {
                        request.setAttribute("WARNING", "email or password wrong!");
                        request.setAttribute("email", email);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
