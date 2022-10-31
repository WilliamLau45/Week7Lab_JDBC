package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;

/**
 *
 * @author William Lau
 */
public class UserServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        User user;
        ArrayList<User> users;
        String action = request.getParameter("action");
        String email = (String) request.getParameter("userEmail");

        if (action != null && action.equals("edit")) {
            try {
                user = us.get(email);
                request.setAttribute("updateUser", user);
                request.setAttribute("page", "edit");

            } 
            catch (Exception e) {
                request.setAttribute("task", "error");
            }

        } 
        else if (action != null && action.equals("delete")) {
            String delete = request.getParameter("userEmail");
            try {
                us.delete(delete);
            } 
            catch (Exception e) {
                request.setAttribute("task", "error");
            }
        }

        try {
            users = us.getAll();
            if (users.isEmpty()) {
                request.setAttribute("message", "No users to be displayed");
            }
            request.setAttribute("users", users);

        } 
        catch (Exception e) {
            request.setAttribute("task", "error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        ArrayList<User> users = null;
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        
        try {
            if (action != null && action.equals("add")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                String role = request.getParameter("role");

                us.insert(email, firstName, lastName, password, role);
            } 
            else if (action != null && action.equals("edit")) {

                email = (String) request.getParameter("updateUserEmail");
                String editFirstName = request.getParameter("editFirstName");
                String editLastName = request.getParameter("editLastName");
                String editPassword = request.getParameter("editPassword");
                String editRole = request.getParameter("editRole");
                us.update(email, editFirstName, editLastName, editPassword, editRole);
                request.setAttribute("task", action);

            }

            request.setAttribute("users", users);
        } 
        catch (Exception e) {
            request.setAttribute("task", "error");
        }
        try {
            users = us.getAll();
            if (users.isEmpty()) {
                request.setAttribute("message", "No users to be displayed");
            }
            request.setAttribute("users", users);

        } 
        catch (Exception e) {
            request.setAttribute("task", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp")
                .forward(request, response);
    }
}
