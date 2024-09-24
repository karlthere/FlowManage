/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.service.AuthService;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author pc
 */
@Named(value = "authBean")
@Dependent
public class AuthBean {
    
    private String email;
    private String password;
    /**
     * Creates a new instance of AuthBean
     */
    public AuthBean() {
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        AuthService as = new AuthService();
        boolean isSuccess = as.login(email, password);
        
        if (isSuccess) {
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "You are now logged in!"));
        }
        
        return "dashboard";
    }
}
