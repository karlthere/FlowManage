/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.UserDAO;
import com.model.pojo.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author pc
 */
@Named(value = "projectBean")
@RequestScoped
public class ProjectBean {
    
    private String title;
    private String description;
    private String manager;
    private List<User> userList;
    private UserDAO userDAO = new UserDAO();
    /**
     * Creates a new instance of ProjectBean
     */
    public ProjectBean() {
    }
    
    @PostConstruct
    public void init() {
        userList = userDAO.getAllUsers();
    }
}
