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
 * @author marco
 */
public class ProductoData {
   
private final Connection con;
    
    public ProductoData(Conexion conexion){
        con = conexion.getConnection();
    }
    
    public boolean guardarProducto(Producto producto){
        
        try{     
            //CONSULTA A REALIZAR
            String sql = "INSERT INTO producto(nombre, precio, estado) VALUES (?,?,?);";
                
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);    
            //PREPARANDO LOS ARGUMENTOS A ENVIAR.
                       
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setBoolean(3, producto.isEstado());
            
            //CONSULTA ENVIADA.
            ps.executeUpdate();
            
            //COLECCION CON CLAVES GENERADAS.
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next())
                producto.setId(rs.getInt(1));
            else
                System.out.println("Error no hay ID"); 
            
            //CERRANDO CONEXION.
            ps.close();
            
            return true;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al guardar el producto.");
            return false;
        }
    }
    
    public List<Producto> obtenerProductosActivos(){
        List<Producto> productos = new ArrayList<>();
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM producto where estado =1";
            
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                Producto produc;
                
                //MIENTRAS HAYA MESAS HACER
                while(resultSet.next()){
                    produc = new Producto();
                    produc.setId(resultSet.getInt("id"));
                    produc.setNombre(resultSet.getString("nombre"));
                    produc.setPrecio(resultSet.getDouble("precio"));
                    produc.setEstado(resultSet.getBoolean("estado"));
                    productos.add(produc);
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener los productos: "+e.getMessage());
        }
        return productos;
    }
    
    public List<Producto> obtenerProductosEliminados(){
        List<Producto> productos = new ArrayList<>();
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM producto where estado =0";
            
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                Producto produc;
                
                //MIENTRAS HAYA MESAS HACER
                while(resultSet.next()){
                    produc = new Producto();
                    produc.setId(resultSet.getInt("id"));
                    produc.setNombre(resultSet.getString("nombre"));
                    produc.setPrecio(resultSet.getDouble("precio"));
                    produc.setEstado(resultSet.getBoolean("estado"));
                    productos.add(produc);
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener los productos: "+e.getMessage());
        }
        return productos;
    }
    
    public void actualizarProducto(Producto producto){
        try{
            String sql = ("UPDATE producto SET nombre=?, precio=?, estado=? WHERE id=?");
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setBoolean(3, producto.isEstado());
            ps.setInt(4, producto.getId());
            
            ps.executeUpdate();
            
            ps.close();
            
           
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void borrarProducto (int id){
        String sql = "DELETE FROM producto WHERE id=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public Producto buscarProducto(int id){
        Producto producto=null;
        String sql = "SELECT * FROM producto WHERE id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return producto;
    }
}
