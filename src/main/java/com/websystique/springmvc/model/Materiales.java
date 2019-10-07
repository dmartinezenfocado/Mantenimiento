package com.websystique.springmvc.model;
// Generated Sep 8, 2017 3:12:32 PM by Hibernate Tools 4.3.1



/**
 * Materiales generated by hbm2java
 */
public class Materiales  implements java.io.Serializable {


     private Integer id;
     private String material;
     private Float precio;
     private Integer ordenFk;
     private Integer cantidad;
     private Float total;
     private String condicion;

    public Materiales() {
    }

    public Materiales(String material, Float precio, Integer ordenFk, Integer cantidad, Float total, String condicion) {
       this.material = material;
       this.precio = precio;
       this.ordenFk = ordenFk;
       this.cantidad = cantidad;
       this.total = total;
       this.condicion = condicion;
    }
    
     public Materiales(int id,String material, Float precio, Integer ordenFk, Integer cantidad, Float total, String condicion) {
       this.id= id;
       this.material = material;
       this.precio = precio;
       this.ordenFk = ordenFk;
       this.cantidad = cantidad;
       this.total = total;
       this.condicion = condicion;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMaterial() {
        return this.material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    public Float getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    public Integer getOrdenFk() {
        return this.ordenFk;
    }
    
    public void setOrdenFk(Integer ordenFk) {
        this.ordenFk = ordenFk;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Float getTotal() {
        return this.total;
    }
    
    public void setTotal(Float total) {
        this.total = total;
    }
    public String getCondicion() {
        return this.condicion;
    }
    
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }




}

