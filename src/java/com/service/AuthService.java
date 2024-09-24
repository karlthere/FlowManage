/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;
import com.dao.UserDAO;
import com.util.HashUtil;
import com.model.pojo.User;

/**
 *
 * @author pc
 */
public class AuthService {
    private final UserDAO userDAO = new UserDAO();
    
    public boolean login(String email, String password) {
        // Compare password
        User dbUser = userDAO.getUserByEmail(email);
        
        if (dbUser == null) {
            return false;
        }
        
        boolean isValid;
        
        try {
            isValid = HashUtil.validatePassword(password, dbUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return isValid;
    }
}
