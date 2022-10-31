/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.*;
import java.util.ArrayList;
import models.User;
import services.RoleService;

/**
 *
 * @author William Lau
 */
public class UserDB {

    public ArrayList<User> getAll() throws Exception {

        ArrayList<User> users = new ArrayList();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement userPs = null;
        ResultSet userRs = null;
        String userSql = "SELECT * FROM user";

        
        try {
            userPs = con.prepareStatement(userSql);
            userRs = userPs.executeQuery();
            while (userRs.next()) {
                String email = userRs.getString(1);
                String firstName = userRs.getString(2);
                String lastName = userRs.getString(3);
                String password = userRs.getString(4);
                int roleId = userRs.getInt(5);

                User user = new User(email, firstName, lastName, password, RoleService.get(roleId));
                users.add(user);
            }
        } 
        finally {
            DBUtil.closeResultSet(userRs);
            DBUtil.closePreparedStatement(userPs);
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(String email) throws Exception {

        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement userPs = null;
        ResultSet userRs = null;
        String userSql = "SELECT * FROM user WHERE email=?";

        try {
            userPs = con.prepareStatement(userSql);
            userPs.setString(1, email);
            userRs = userPs.executeQuery();
            while (userRs.next()) {
                String firstName = userRs.getString(2);
                String lastName = userRs.getString(3);
                String password = userRs.getString(4);

                int roleID = userRs.getInt(5);

                user = new User(email, firstName, lastName, password, RoleService.get(roleID));
            }
        } 
        finally {
            DBUtil.closeResultSet(userRs);
            DBUtil.closePreparedStatement(userPs);
            cp.freeConnection(con);
        }

        return user;
    }

    public void insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement userPs = null;
        
        
        String userSql = "INSERT INTO user (email, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?)";
   
        try {
            String test = user.getFirstName();
            userPs = con.prepareStatement(userSql);
            userPs.setString(1, user.getEmail());
            userPs.setString(2, user.getFirstName());
            userPs.setString(3, user.getLastName());
            userPs.setString(4, user.getPassword());
            userPs.setInt(5, user.getRole().getRoleID());
            userPs.executeUpdate();

        } 
        finally {
            DBUtil.closePreparedStatement(userPs);
            cp.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement userPs = null;
        String userSql = "DELETE FROM user WHERE email=?";
        try {
            userPs = con.prepareStatement(userSql);
            userPs.setString(1, user.getEmail());
            userPs.executeUpdate();
        } 
        finally {
            DBUtil.closePreparedStatement(userPs);
            cp.freeConnection(con);
        }
    }
 
    public void update(User user) throws Exception {

        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement userPs = null;
        String userSql = "UPDATE user SET first_name=?, last_name=?, password=?, role=? WHERE email=?";
        try {
            userPs = con.prepareStatement(userSql);
            userPs.setString(1, user.getFirstName());
            userPs.setString(2, user.getLastName());
            userPs.setString(3, user.getPassword());
            userPs.setInt(4, user.getRole().getRoleID());
            userPs.setString(5, user.getEmail());
            userPs.executeUpdate();
        } 
        finally {
            DBUtil.closePreparedStatement(userPs);
            cp.freeConnection(con);
        }
    }
}
