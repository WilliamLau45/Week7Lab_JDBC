/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Role;

/**
 *
 * @author William Lau
 */
public class RoleDB {
    public Role get(int roleID) throws Exception {
        Role role = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement rolePs = null;
        ResultSet roleRs = null;
        String roleSql = "SELECT * FROM role WHERE role_id=?";

        try {
            rolePs = con.prepareStatement(roleSql);
            rolePs.setInt(1, roleID);
            roleRs = rolePs.executeQuery();
            while (roleRs.next()) {
                int dbRoleID = roleRs.getInt(1);
                String roleName = roleRs.getString(2);
                role = new Role(roleName, dbRoleID);
            }

        } 
        finally {
            DBUtil.closeResultSet(roleRs);
            DBUtil.closePreparedStatement(rolePs);
            cp.freeConnection(con);
        }

        return role;
    }
}
