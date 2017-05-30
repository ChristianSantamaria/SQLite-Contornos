package contornossqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BaseDatos {

    private String ruta;
    private Connection conex;

    /**
     * Aqui tenemos el constructor que recibe la ruta 
     * @param ruta Esta sera donde se creara la base de datos o donde la cargara
     * @throws SQLException 
     */
    public BaseDatos(String ruta) throws SQLException {
        this.ruta = ruta;    
    }
    /**
     * Aqui tenemos el constructor por defecto que en mi caso crearia la base de datos ya en la carpeta home
     * @throws SQLException 
     */
    public BaseDatos() throws SQLException {
         this.ruta = "jdbc:sqlite:/home/BaseContornos.db";   
    }
    /**
     * Aqui lo igualamos a la función DriverManager para que coja la conexión de la ruta que le habiamos enviado.
     * @throws SQLException 
     */
    public void connect() throws SQLException{
        this.conex = DriverManager.getConnection(ruta);
    }
    
    /**
     * Aqui tenemos el metodo para la creacion de mi tabla "USUARIO" donde tendra el id de clave principal(no puede repetirse), nombre, apellido y edad.
     * @throws SQLException 
     */
    public void crearTabla() throws SQLException {
        Statement stmt = conex.createStatement();
        ResultSet rs = stmt.executeQuery("CREATE TABLE USUARIO (Id INTEGER PRIMARY KEY, Nombre STRING, Apellido STRING, Edad INTEGER)");
    }

}
