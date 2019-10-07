/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Materiales;
import com.websystique.springmvc.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author delvinmartinez
 */
public class MaterialesDAO {
    
     public static List<Materiales> listMateriales(){
        List<Materiales> lst = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Materiales";
            Query query = session.createQuery(hql);
            lst = query.list();
            
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
    
    public static void insertMaterial(Materiales material){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(material);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void deleteMaterial(Materiales material){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(material);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void updateMaterial(Materiales material){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(material);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static Materiales selectMaterial(int id){
        List<Materiales> lst = null;  
        Materiales p = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Materiales where id ="+id;
            Query query = session.createQuery(hql);
            lst = query.list();
            p = lst.get(0);
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return p;
    }
    
    
      public static List<Materiales> selectMaterialesByOrdenFk(int id){
        List<Materiales> lst = null;  
    
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Materiales where orden_fk ="+id;
            Query query = session.createQuery(hql);
            lst = query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
    
      public static List<Materiales> buscarMaterial(String busqueda){
        List<Materiales> lst = null;  
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Materiales where material = '"+busqueda+"' or precio like '%"+busqueda+"%' or cantidad like '%"+busqueda+"%' or total like '%"+busqueda+"%' or condicion like '%"+busqueda+"%'";
            Query query = session.createQuery(hql);
            lst = query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
    
    
}
