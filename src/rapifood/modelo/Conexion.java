package rapifood.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fedep
 */
public class Conexion {
    //URL CONFORMADA DE URL+BASE Y VERIFICACION DE INGRESO A LA BD
    private final String base = "rapifood";
    private final String url = "jdbc:mysql://localhost:3306/"+base;
    private final String user = "root";
    private final String pass = "";
    //1 - DECLARAR VARIABLE CONNECTION
    private Connection con; 
    
    public Connection getConnection(){
        try{
            //2 - DECLARAR DRIVER
            Class.forName("com.mysql.jdbc.Driver");
            //3 - ESTABLECER CONEXION
            con = (Connection) DriverManager.getConnection(url,user,pass);
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.print(e);
        }
        return con;
    }
}
