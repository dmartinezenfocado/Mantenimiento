/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Ordenes;
import com.websystique.springmvc.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author delvinmartinez
 */
public class OrdenesDAO {
    
     public static List<Ordenes> listOrdenes(){
        List<Ordenes> lst = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes";
            Query query = session.createQuery(hql);
            lst = query.list();
            
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
     
      public static List<Ordenes> listOrdenesCompletas(){
        List<Ordenes> lst = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes where estatus like '%COMPLETADA%'";
            Query query = session.createQuery(hql);
            lst = query.list();
            
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
      
      
         public static List<Ordenes> listOrdenesOpen(){
        List<Ordenes> lst = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes where estatus like '%ABIERTA%'";
            Query query = session.createQuery(hql);
            lst = query.list();
            
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
    
    
    public static void insertOrden(Ordenes orden){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(orden);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void deleteOrden(Ordenes orden){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(orden);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void updateOrden(Ordenes orden){
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(orden);
            tx.commit();

            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static Ordenes selectOrden(int id){
        List<Ordenes> lst = null;  
        Ordenes p = null;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes where id ="+id;
            Query query = session.createQuery(hql);
            lst = query.list();
            p = lst.get(0);
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return p;
    }
    
    
      public static List<Ordenes> selectOrdenesByEquipoFk(int id){
        List<Ordenes> lst = null;  
    
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes where equipo_fk ="+id;
            Query query = session.createQuery(hql);
            lst = query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
    
      public static List<Ordenes> buscarOrden(String busqueda){
        List<Ordenes> lst = null;  
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes where fecha_creacion = '"+busqueda+"' or fecha_cierre like '%"+busqueda+"%' or tipo_orden like '%"+busqueda+"%' or descripcion like '%"+busqueda+"%' or equipo_fk like '%"+busqueda+"%' or estatus like '%"+busqueda+"%'";
            Query query = session.createQuery(hql);
            lst = query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
      
      
     public static List<Ordenes> buscarOrdenCompletas(String busqueda){
        List<Ordenes> lst = null;  
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes where fecha_creacion = '"+busqueda+"' or fecha_cierre like '%"+busqueda+"%' or tipo_orden like '%"+busqueda+"%' or descripcion like '%"+busqueda+"%' or equipo_fk like '%"+busqueda+"%' and estatus like '%COMPLETADA%'";
            Query query = session.createQuery(hql);
            lst = query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }
       
     public static List<Ordenes> buscarOrdenOpen(String busqueda){
        List<Ordenes> lst = null;  
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            String hql = "from Ordenes where fecha_creacion = '"+busqueda+"' or fecha_cierre like '%"+busqueda+"%' or tipo_orden like '%"+busqueda+"%' or descripcion like '%"+busqueda+"%' or equipo_fk like '%"+busqueda+"%' and estatus like '%ABIERTA%'";
            Query query = session.createQuery(hql);
            lst = query.list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lst;
    }   
    
    
}
