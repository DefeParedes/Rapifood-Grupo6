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
public class ReservaData {
    private final Connection con;
    
    public Mesa buscarMesa(int id){
        Conexion c = new Conexion();
        MesaData mesaData = new MesaData(c);
        return mesaData.buscarMesa(id);
    }
    
    public ReservaData(Conexion conexion){
        con = conexion.getConnection();
    }
    
    public void guardarReserva(Reserva reserva){
        
        try{     
            //CONSULTA A REALIZAR
            String sql = "INSERT INTO reserva(nombre_cliente,apellido_cliente,turno_reserva,estado,id_mesa,cant_comensales) VALUES (?,?,?,?,?,?);";
                
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);    
            //PREPARANDO LOS ARGUMENTOS A ENVIAR.
            ps.setString(1, reserva.getNombre_cliente());
            ps.setString(2, reserva.getApellido_cliente());
            ps.setTimestamp(3, reserva.getTurno_reserva());
            ps.setBoolean(4, reserva.isEstado());
            ps.setInt(5, reserva.getMesa().getId());
            ps.setInt(6, reserva.getCant_comensales());
            
            //CONSULTA ENVIADA.
            ps.executeUpdate();
            
            //COLECCION CON CLAVES GENERADAS.
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next())
                reserva.setId(rs.getInt(1));
            else
                System.out.println("Error no hay ID"); 
            
            //CERRANDO CONEXION.
            ps.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al realizar la reserva.");
        }
    }
    
    public List<Reserva> obtenerReservas(){
        List<Reserva> reservas = new ArrayList<>();
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM reserva;";
            
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Reserva reserva;
            
            //MIENTRAS HAYA RESERVAS HACER
            while(resultSet.next()){
                reserva = new Reserva();
                reserva.setId(resultSet.getInt("id"));
                reserva.setApellido_cliente(resultSet.getString("apellido_cliente"));
                reserva.setNombre_cliente(resultSet.getString("nombre_cliente"));
                reserva.setTurno_reserva(resultSet.getTimestamp("turno_reserva"));
                reserva.setEstado(resultSet.getBoolean("estado"));
                reserva.setMesa(buscarMesa(resultSet.getInt("id_mesa")));
                reserva.setCant_comensales(resultSet.getInt("cant_comensales"));
                reservas.add(reserva);
            }
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error al obtener las reservas: "+e.getMessage());
        }
        return reservas;
    }
    
    public Reserva buscarReserva(int id){
        Reserva reserva=null;
        String sql = "SELECT * FROM reserva WHERE id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setNombre_cliente(rs.getString("nombre_cliente"));
                reserva.setApellido_cliente(rs.getString("apellido_cliente"));
                reserva.setTurno_reserva(rs.getTimestamp("turno_reserva"));
                reserva.setEstado(rs.getBoolean("estado"));
                reserva.setMesa(buscarMesa(rs.getInt("id_mesa")));
                reserva.setCant_comensales(rs.getInt("cant_comensales"));
            }
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return reserva;
    }
    
    public void actualizarReserva(Reserva reserva){
        try{
            String sql = ("UPDATE reserva SET nombre_cliente=?,apellido_cliente=?,turno_reserva=?,estado=?,id_mesa=?,cant_comensales=? WHERE id=?");
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, reserva.getNombre_cliente());
            ps.setString(2, reserva.getApellido_cliente());
            ps.setTimestamp(3, reserva.getTurno_reserva());
            ps.setBoolean(4, reserva.isEstado());
            ps.setInt(5, reserva.getMesa().getId());
            ps.setInt(6, reserva.getCant_comensales());
            ps.setInt(7, reserva.getId());
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void borrarReserva (int id){
        String sql = "DELETE FROM reserva WHERE id=?";
        
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
