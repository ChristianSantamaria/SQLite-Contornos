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
    
    /**
     * Aqui tenemos el metodo insertar donde al enviar todos esos parametros los añade a la query para asi ejecutarla
     * En mi caso en la Qry donde deberían estar los valores coloco interrogaciones que la cambio con la variable Prepared.set”Tipo de la variable”(la posición, la variable). Y finalmente execute.
     * @param Id El id por el que se identifica cada usuario
     * @param Nombre El nombre del usuario
     * @param Apellido El apellido del usuario
     * @param Edad La edad del usuario
     * @throws SQLException 
     */
    public void insertar(int Id, String Nombre, String Apellido, int Edad) throws SQLException {
        String Qry = "INSERT INTO 'USUARIO' ('Id','Nombre', 'Apellido', 'Edad') VALUES (?,?,?,?)";
        PreparedStatement rs = conex.prepareStatement(Qry);
        rs.setInt(1, Id);
        rs.setString(2, Nombre);
        rs.setString(3, Apellido);
        rs.setInt(4, Edad);
        rs.execute();
        JOptionPane.showMessageDialog(null, "El usuario de ha creado");
    }
    
    /**
     * Este metodo modificar es como el de insertar donde al enviar todos esos parametros los añade a la query para asi ejecutarla
     * @param Id El id por el que se identifica cada usuario
     * @param Nombre El nombre del usuario
     * @param Apellido El apellido del usuario
     * @param Edad La edad del usuario
     * @throws SQLException 
     */
    public void modificar(int Id, String Nombre, String Apellido, int Edad) throws SQLException {
        String Qry = "UPDATE 'USUARIO' SET Nombre = ?, Apellido= ?, Edad = ? WHERE Id = ?";     
        PreparedStatement rs = conex.prepareStatement(Qry);
        rs.setString(1, Nombre);
        rs.setString(2, Apellido);
        rs.setInt(3, Edad);
        rs.setInt (4,Id);
        rs.execute();

    }
    
    /**
     * Para este metodo precisamos un id para que cuando cree la query sepa cual borrar
     * @param Id El id por el que se identifica cada usuario
     * @throws SQLException 
     */
    public void eliminar(int Id) throws SQLException{
        String Qry = "DELETE FROM USUARIO WHERE Id= ? ";
        PreparedStatement rs = conex.prepareStatement(Qry);
        rs.setInt(1, Id);
        rs.execute();   
        JOptionPane.showMessageDialog(null, "Se ha eliminado al usuario correctamente");
    }

    /**
     * Este metodo recibira el id por el que se quiere buscar y hara el select con el cual buscara toda la informacion de dicho usuario por la id
     * @param Id El id por el que se identifica cada usuario
     * @return Devolvemos un ResultSet para que el usuario lo pueda recorrer y visualizar los datos
     * @throws SQLException 
     */
    public ResultSet consultar(String Id) throws SQLException {
        Statement stmt = conex.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Id, Nombre, Apellido, Edad FROM USUARIO WHERE Id = '" + Id + "'");
        return rs;

    }
    
    /**
     * Este metodo es extra que hice que vale para mostrar todos los Id de usuario
     * para asi poder pinchar en cualquiera y modificarlo o eliminarlo
     * @return Devuelve un ResultSet que son todos los Ids registrados en la base de datos
     * @throws SQLException 
     */
    public ResultSet selertTodo() throws SQLException {
        Statement stmt = conex.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Id FROM USUARIO");
        return rs;

    }
}
