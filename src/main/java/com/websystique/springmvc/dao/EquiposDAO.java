/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Equipos;
import com.websystique.springmvc.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author delvinmartinez
 */
public class EquiposDAO {
    
     public static List<Equipos> listEquipos(){
        List<Equipos> lst = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Equipos";
            Query query = session.createQuery(hql);
            lst = query.list();
            
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
    
    public static void insertEquipo(Equipos equipo){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(equipo);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void deleteEquipo(Equipos equipo){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(equipo);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void updateEquipo(Equipos equipo){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(equipo);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static Equipos selectEquipo(int id){
        List<Equipos> lst = null;  
        Equipos p = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Equipos where id ="+id;
            Query query = session.createQuery(hql);
            lst = query.list();
            p = lst.get(0);
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return p;
    }
    
      public static List<Equipos> buscarEquipo(String busqueda){
        List<Equipos> lst = null;  
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Equipos where tipo = '"+busqueda+"' or marca like '%"+busqueda+"%' or modelo like '%"+busqueda+"%' or ano like '%"+busqueda+"%' or serie like '%"+busqueda+"%' or ficha like '%"+busqueda+"%'";
            Query query = session.createQuery(hql);
            lst = query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
    
}
