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
public class MeseroData {
    private final Connection con;
    
    public MeseroData(Conexion conexion){
        con = conexion.getConnection();
    }
    
    public void guardarMesero(Mesero mesero){
        
        try{     
            //CONSULTA A REALIZAR
            String sql = "INSERT INTO mesero(apellido,nombre,cuit,estado) VALUES (?,?,?,?);";
                
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);    
            //PREPARANDO LOS ARGUMENTOS A ENVIAR.
            ps.setString(1, mesero.getApellido());
            ps.setString(2, mesero.getNombre());
            ps.setString(3, mesero.getCuit());
            ps.setBoolean(4, mesero.isEstado());
            
            //CONSULTA ENVIADA.
            ps.executeUpdate();
            
            //COLECCION CON CLAVES GENERADAS.
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next())
                mesero.setId(rs.getInt(1));
            else
                System.out.println("Error no hay ID"); 
            
            //CERRANDO CONEXION.
            ps.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al guardar al mesero.");
        }
    }
    
    public List<Mesero> obtenerMeseros(){
        List<Mesero> meseros = new ArrayList<>();
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM mesero;";
            
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Mesero mesero;
            
            //MIENTRAS HAYA MESAS HACER
            while(resultSet.next()){
                mesero = new Mesero();
                mesero.setId(resultSet.getInt("id"));
                mesero.setApellido(resultSet.getString("apellido"));
                mesero.setNombre(resultSet.getString("nombre"));
                mesero.setCuit(resultSet.getString("cuit"));
                mesero.setEstado(resultSet.getBoolean("estado"));
                meseros.add(mesero);
            }
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error al obtener los meseros: "+e.getMessage());
        }
        return meseros;
    }
    
    public Mesero buscarMesero(int id){
        Mesero mesero=null;
        String sql = "SELECT * FROM mesero WHERE id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                mesero = new Mesero();
                mesero.setId(rs.getInt("id"));
                mesero.setApellido(rs.getString("apellido"));
                mesero.setNombre(rs.getString("nombre"));
                mesero.setCuit(rs.getString("cuit"));
                mesero.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return mesero;
    }
    
    public void actualizarMesero(Mesero mesero){
        try{
            String sql = ("UPDATE mesero SET apellido=?,nombre=?,cuit=?,estado=? WHERE id=?");
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, mesero.getApellido());
            ps.setString(2, mesero.getNombre());
            ps.setString(3, mesero.getCuit());
            ps.setBoolean(4, mesero.isEstado());
            ps.setInt(5, mesero.getId());
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void borrarMesero (int id){
        String sql = "DELETE FROM mesero WHERE id=?";
        
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
