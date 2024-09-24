/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.pojo.User;
import com.util.HashUtil;
import com.dao.UserDAO;

/**
 *
 * @author pc
 */
public class UserService {
    private final UserDAO userDAO = new UserDAO();
    public boolean createUser(String firstName, String lastName, String email, String password) {
        try {
            String hashedPassword = HashUtil.createHash(password);
            User newUser = new User(firstName, lastName, email, hashedPassword);
            boolean isSuccess = userDAO.addUser(newUser);
            if (!isSuccess) {
                throw new Exception("Failed to create new user");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}
