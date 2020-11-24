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
public class PedidoData {
   
private final Connection con;
    
    public PedidoData(Conexion conexion){
        con = conexion.getConnection();
    }
    
    public Mesa buscarMesa(int id){
        Conexion c = new Conexion();
        MesaData mesaData = new MesaData(c);
        return mesaData.buscarMesa(id);
    }
    
    public Mesero buscarMesero(int id){
        Conexion c = new Conexion();
        MeseroData meseroData = new MeseroData(c);
        return meseroData.buscarMesero(id);
    }
    
    public boolean guardarPedido(Pedido pedido){
        
        try{     
            //CONSULTA A REALIZAR
            String sql = "INSERT INTO pedido(id_mesa, id_mesero, estado, monto,fechaPedido) VALUES (?,?,?,?,?);";
                
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);    
            //PREPARANDO LOS ARGUMENTOS A ENVIAR.
                       
            ps.setInt(1, pedido.getMesa().getId());
            ps.setInt(2, pedido.getMesero().getId());
            ps.setBoolean(3, pedido.isEstado());
            ps.setDouble(4, pedido.getMonto());
            ps.setDate(5, pedido.getFechaPedido());
            
            //CONSULTA ENVIADA.
            ps.executeUpdate();
            
            //COLECCION CON CLAVES GENERADAS.
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next())
                pedido.setId(rs.getInt(1));
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
    
    public List<Pedido> obtenerPedidosActivos(){
        List<Pedido> pedidos = new ArrayList<>();
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM pedido where estado =1";
            
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                Pedido pedido;
                
                //MIENTRAS HAYA PEDIDOS HACER.
                while(resultSet.next()){
                    pedido = new Pedido();
                    pedido.setId(resultSet.getInt("id"));
                    pedido.setMesa(buscarMesa(resultSet.getInt("id_mesa")));
                    pedido.setMesero(buscarMesero(resultSet.getInt("id_mesero")));
                    pedido.setMonto(resultSet.getDouble("monto"));
                    pedido.setEstado(resultSet.getBoolean("estado"));
                    pedido.setFechaPedido(resultSet.getDate("fechaPedido"));
                    pedidos.add(pedido);
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener los productos: "+e.getMessage());
        }
        return pedidos;
    }  
    
    public double calcularValorPedido(List<String> p) throws SQLException{
          
        double val=0;
        
        for(int i=0; i<p.size(); i++){
                 
            PreparedStatement ps = con.prepareStatement("SELECT precio FROM producto where nombre =?"); 
            ps.setString(1, p.get(i));
            
            ResultSet resultSet = ps.executeQuery();
         
            Producto pr = new Producto();
            
            while (resultSet.next()) { 
            pr.setPrecio(resultSet.getDouble("precio"));
            }
            
            val = val + pr.getPrecio();
            
            
            resultSet.close();
            ps.close();
            
            }
        
        return val;
    
    }
    
    public void actualizarPedido(Pedido pedido){
        try{
            String sql = ("UPDATE pedido SET id_mesa=?,id_mesero=?,monto=?,estado=?,fechaPedido=? WHERE id=?");
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pedido.getMesa().getId());
            ps.setInt(2, pedido.getMesero().getId());
            ps.setDouble(3, pedido.getMonto());
            ps.setBoolean(4, pedido.isEstado());
            ps.setDate(5, pedido.getFechaPedido());
            ps.setInt(6, pedido.getId());
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public Pedido buscarPedido(int id){
        Pedido pedido=null;
        String sql = "SELECT * FROM pedido WHERE id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setMesa(buscarMesa(rs.getInt("id_mesa")));
                pedido.setMesero(buscarMesero(rs.getInt("id_mesero")));
                pedido.setMonto(rs.getDouble("monto"));
                pedido.setEstado(rs.getBoolean("estado"));
                pedido.setFechaPedido(rs.getDate("fechaPedido"));
            }
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return pedido;
    }
    
    public void borrarPedido (int id){
        String sql = "DELETE FROM pedido WHERE id=?";
        
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
}
