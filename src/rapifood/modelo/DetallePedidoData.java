/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapifood.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fedep
 */
public class DetallePedidoData {
    //DECLARAR VARIABLE CONNECTION
    private final Connection con;
    
    //EN EL CONSTRUCTOR REALIZAMOS LA CONEXION CON LA BD.
    public DetallePedidoData(Conexion conexion){
        con = conexion.getConnection();
    }
    
    public Pedido buscarPedido(int id){
        Conexion c = new Conexion();
        PedidoData pedidoData = new PedidoData(c);
        return pedidoData.buscarPedido(id);
    }
    
    public Producto buscarProducto(int id){
        Conexion c = new Conexion();
        ProductoData productoData = new ProductoData(c);
        return productoData.buscarProducto(id);
    }
    
    //GUARDA LA MESA EN LA BD - NO AUTOINCREMENTA EL ID (CADA MESA TIENE SU PROPIO ID).
    public void guardarDetallePedido(DetallePedido detalle){
        
        try{     
            //CONSULTA A REALIZAR.
            String sql = "INSERT INTO detallepedido(id_pedido,id_producto) VALUES (?,?);";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);    
            //ARGUMENTOS A ENVIAR.
            ps.setInt(1, detalle.getPedido().getId());
            ps.setInt(2, detalle.getProducto().getId());
            
            //CONSULTA ENVIADA.
            ps.executeUpdate();
            
            //COLECCION CON CLAVES GENERADAS.
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next())
                detalle.setId(rs.getInt(1));
            else
                System.out.println("Error no hay ID"); 
            
            //CERRANDO CONEXION.
            ps.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al guardar el detalle.");
        }
    }
    
    //LISTA TODAS LOS DETALLES.
    public List<DetallePedido> obtenerDetallesPedidos(){
        List<DetallePedido> detalles = new ArrayList<>();
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM detallepedido;";
            PreparedStatement statement = con.prepareStatement(sql);
            
            //RECIBIENDO LAS MESAS.
            ResultSet resultSet = statement.executeQuery();
            
            //MESA A MODIFICAR.
            DetallePedido detalle;
            
            //MIENTRAS HAYA MESAS HACER
            while(resultSet.next()){
                detalle = new DetallePedido();
                detalle.setId(resultSet.getInt("id"));
                detalle.setPedido(buscarPedido(resultSet.getInt("id_pedido")));
                detalle.setProducto(buscarProducto(resultSet.getInt("id_producto")));
                //AGREGANDO MESA A LA LISTA DE MESAS.
                detalles.add(detalle);
            }
            //CERRANDO CONEXION.
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error al obtener los detalles: "+e.getMessage());
        }
        //RETORNANDO LISTA DE MESAS.
        return detalles;
    }
    
    //BUSCAR MESA POR SU ID.
    public DetallePedido buscarDetallePedido(int id){
        DetallePedido detalle=null;
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM detallepedido WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            //INSERTANDO LA ID BUSCADA EN LA CONSULTA.
            ps.setInt(1, id);
            
            //RECIBIENDO LA MESA INDICADA.
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                detalle = new DetallePedido();
                detalle.setId(rs.getInt("id"));
                detalle.setPedido(buscarPedido(rs.getInt("id_pedido")));
                detalle.setProducto(buscarProducto(rs.getInt("id_producto")));
            }
            
            //CERRANDO CONEXION.
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return detalle;
    }
    
    //ACTUALIZAR MESA RECIBIENDO LA MESA YA MODIFICADA.
    public void actualizarDetallePedido(DetallePedido detalle){
        try{
            //CONSULTA A REALIZAR.
            String sql = ("UPDATE detallepedido SET id_pedido=?,id_producto=? WHERE id=?");
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            //ARGUMENTOS A ENVIAR.
            ps.setInt(1, detalle.getPedido().getId());
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getId());
            
            //REALIZANDO CONSULTA
            ps.executeUpdate();
            
            //CERRANDO CONEXION.
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    //BORRAR UNA MESA USANDO SU ID.
    public void borrarDetallePedido (int id){
        try{
            //CONSULTA A REALIZAR.
            String sql = "DELETE FROM detallePedido WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            //INSERTANDO LA ID BUSCADA EN LA CONSULTA.
            ps.setInt(1, id);
            
            //REALIZANDO LA CONSULTA.
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
