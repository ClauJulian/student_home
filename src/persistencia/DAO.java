package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {

    protected Connection conexion = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    private final String HOST = "127.0.0.1";
    private final String PORT = "3307";
    private final String USER = "root";
    private final String PASSWORD = "wGlam2012!";
    private final String DATABASE = "estancias";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void connectarDataBase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos. ✅");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected void desconectarDataBase() throws SQLException, ClassNotFoundException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
            System.out.println("Conexion cerrada. ✅");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected void insertarModificarEliminarDataBase(String sql) throws SQLException, ClassNotFoundException {

        try {
            connectarDataBase(); // Conectar a la DB
            statement = conexion.createStatement(); // Preparar el objeto Statement
            statement.executeUpdate(sql); // Ejecutar la sentencia
//            desconectarDataBase();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ocurrio un error al realizar un CRUD ❌" + e.getMessage());
        } finally {
            desconectarDataBase();
        }
    }
   
    protected void consultarDataBase(String sql) throws SQLException, ClassNotFoundException {
        try {
            connectarDataBase(); // Conectar a la DB
            statement = conexion.createStatement(); // Preparar el objeto Statement
            resultSet = statement.executeQuery(sql); // Ejecutar la sentencia
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ocurrio un error al realizar un CRUD ❌" + e.getMessage());
        } 
        /*finally {
            desconectarDataBase();
        }*/
    }
}
