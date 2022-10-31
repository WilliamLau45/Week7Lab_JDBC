/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.ArrayList;
import models.User;
import models.Role;

/**
 *
 * @author William Lau
 */
public class UserService {

    public UserService() {
    }

    public User get(String email) throws Exception {

        UserDB userDB = new UserDB();
        User user = userDB.get(email);

        return user;
    }

    public ArrayList<User> getAll() throws Exception {
        ArrayList<User> users = null;
        UserDB userDB = new UserDB();
        users = userDB.getAll();
        
        return users;
    }

    public void insert(String email, String firstName,
            String lastName, String password, String roleName) throws Exception {
        
        RoleDB roleDB = new RoleDB();
        Role role = null;
        UserDB userDB = new UserDB();

        if (roleName.equals("system admin")) {
            role = roleDB.get(1);
        } 
        else if (roleName.equals("regular user")) {
            role = roleDB.get(2);
        }

        User user = new User(email, firstName, lastName, password, role);
        userDB.insert(user);
    }

    public void delete(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }

    public void update(String email, String firstName,
            String lastName, String password, String roleName) throws Exception {
        Role role = null;
        RoleDB roleDB = new RoleDB();
        UserDB userDB = new UserDB();

        if (roleName.equals("system admin")) {
            role = roleDB.get(1);
        } 
        else if (roleName.equals("regular user")) {
            role = roleDB.get(2);
        }

        User user = new User(email, firstName, lastName, password, role);
        userDB.update(user);
    }
}
