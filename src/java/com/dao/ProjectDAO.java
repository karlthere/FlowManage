/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.Project;
import com.model.pojo.Task;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pc
 */
public class ProjectDAO {
    public List<Project> getAllProjects() {
        List<Project> projects = null;
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try {  
            session.beginTransaction();  
            projects = session.createCriteria(Project.class).list();  
            int count = projects.size();
            session.getTransaction().commit();  
        } catch (Exception e) {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return projects;  
    }
    
    public Project getProjectByID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Project project = null;
        try {
            project = (Project) session.get(Project.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        session.close();
        return project;
    }
    
    public List<Task> getProjectTasks(int projectId) {
        TaskDAO taskDAO = new TaskDAO();
        return taskDAO.getTasks(projectId);
    }
    
    public void addProject(Project newProject) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(newProject);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        session.close();
    }
    
    public void updateProject(Project updatedProject) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(updatedProject);
            session.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        session.close();
    }
    
    public void deleteProject(Project targetProject) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            session.delete(targetProject);
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
    }
}
