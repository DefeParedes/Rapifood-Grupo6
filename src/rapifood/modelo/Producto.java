/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapifood.modelo;

/**
 *
 * @author marco
 */
public class Producto {
    
private int id;
private String nombre;
private double precio;
private boolean estado; 

public Producto(){}

public Producto(int id,String nom, double pre, boolean est){

    this.estado= est;
    this.id = id;
    this.nombre= nom;
    this.precio= pre;
}

    public Producto(String nombre, double precio, boolean estado) {
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
    }

/**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
