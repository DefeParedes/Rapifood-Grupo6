/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapifood.modelo;

/**
 *
 * @author Fedep
 */
public class DetallePedido {
    private int id;
    private Pedido pedido;
    private Producto producto;

    public DetallePedido(Pedido pedido, Producto producto) {
        this.pedido = pedido;
        this.producto = producto;
    }

    public DetallePedido() {
    }

    public int getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
