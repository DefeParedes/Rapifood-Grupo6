/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapifood.modelo;

import java.sql.Timestamp;

/**
 *
 * @author Fedep
 */
public class Reserva {
    private int id;
    private String nombre_cliente;
    private String apellido_cliente;
    private Timestamp turno_reserva;
    private boolean estado;
    private Mesa mesa;
    private int cant_comensales;

    public Reserva(String nombre_cliente, String apellido_cliente, Timestamp turno_reserva, boolean estado, Mesa mesa,int cant_comensales) {
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.turno_reserva = turno_reserva;
        this.estado = estado;
        this.mesa = mesa;
        this.cant_comensales = cant_comensales;
    }

    public Reserva(int id, String nombre_cliente, String apellido_cliente, Timestamp turno_reserva, boolean estado, Mesa mesa, int cant_comensales) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.turno_reserva = turno_reserva;
        this.estado = estado;
        this.mesa = mesa;
        this.cant_comensales = cant_comensales;
    }
    
    

    public Reserva() {}

    public int getId() {
        return id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public Timestamp getTurno_reserva() {
        return turno_reserva;
    }

    public boolean isEstado() {
        return estado;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public void setTurno_reserva(Timestamp turno_reserva) {
        this.turno_reserva = turno_reserva;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    } 

    public int getCant_comensales() {
        return cant_comensales;
    }

    public void setCant_comensales(int cant_comensales) {
        this.cant_comensales = cant_comensales;
    }
}
