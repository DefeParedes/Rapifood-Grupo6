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
public class MesaData {
    //DECLARAR VARIABLE CONNECTION
    private final Connection con;
    
    //EN EL CONSTRUCTOR REALIZAMOS LA CONEXION CON LA BD.
    public MesaData(Conexion conexion){
        con = conexion.getConnection();
    }
    
    //GUARDA LA MESA EN LA BD - NO AUTOINCREMENTA EL ID (CADA MESA TIENE SU PROPIO ID).
    public void guardarMesa(Mesa mesa){
        
        try{     
            //CONSULTA A REALIZAR.
            String sql = "INSERT INTO mesa(max_comensales,estado,id) VALUES (?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);    
            //ARGUMENTOS A ENVIAR.
            ps.setInt(1, mesa.getMax_comensales());
            ps.setBoolean(2, mesa.isEstado());
            ps.setInt(3, mesa.getId());
            
            //CONSULTA ENVIADA.
            ps.executeUpdate();
            
            //CERRANDO CONEXION.
            ps.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al guardar la mesa.");
        }
    }
    
    //LISTA TODAS LAS MESAS.
    public List<Mesa> obtenerMesas(){
        List<Mesa> mesas = new ArrayList<>();
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM mesa;";
            PreparedStatement statement = con.prepareStatement(sql);
            
            //RECIBIENDO LAS MESAS.
            ResultSet resultSet = statement.executeQuery();
            
            //MESA A MODIFICAR.
            Mesa mesa;
            
            //MIENTRAS HAYA MESAS HACER
            while(resultSet.next()){
                mesa = new Mesa();
                mesa.setId(resultSet.getInt("id"));
                mesa.setMax_comensales(resultSet.getInt("max_comensales"));
                mesa.setEstado(resultSet.getBoolean("estado"));
                //AGREGANDO MESA A LA LISTA DE MESAS.
                mesas.add(mesa);
            }
            //CERRANDO CONEXION.
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error al obtener las mesas: "+e.getMessage());
        }
        //RETORNANDO LISTA DE MESAS.
        return mesas;
    }
    
    //BUSCAR MESA POR SU ID.
    public Mesa buscarMesa(int id){
        Mesa mesa=null;
        try{
            //CONSULTA A REALIZAR.
            String sql = "SELECT * FROM mesa WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            //INSERTANDO LA ID BUSCADA EN LA CONSULTA.
            ps.setInt(1, id);
            
            //RECIBIENDO LA MESA INDICADA.
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                mesa = new Mesa();
                mesa.setId(rs.getInt("id"));
                mesa.setMax_comensales(rs.getInt("max_comensales"));
                mesa.setEstado(rs.getBoolean("estado"));
            }
            
            //CERRANDO CONEXION.
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return mesa;
    }
    
    //ACTUALIZAR MESA RECIBIENDO LA MESA YA MODIFICADA.
    public void actualizarMesa(Mesa mesa){
        try{
            //CONSULTA A REALIZAR.
            String sql = ("UPDATE mesa SET max_comensales=?,estado=? WHERE id=?");
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            //ARGUMENTOS A ENVIAR.
            ps.setInt(1, mesa.getMax_comensales());
            ps.setBoolean(2, mesa.isEstado());
            ps.setInt(3, mesa.getId());
            
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
    public void borrarMesa (int id){
        try{
            //CONSULTA A REALIZAR.
            String sql = "DELETE FROM mesa WHERE id=?";
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
