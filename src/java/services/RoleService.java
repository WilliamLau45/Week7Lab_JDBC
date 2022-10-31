/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Role;
import dataaccess.RoleDB;

/**
 *
 * @author William Lau
 */
public class RoleService {
    
    public static Role get(int roleID) throws Exception{
        RoleDB roleDb = new RoleDB();
        Role role = roleDb.get(roleID);
        
        return role;
        
}
}
