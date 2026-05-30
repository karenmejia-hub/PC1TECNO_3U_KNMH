
package main;
import java.sql.*;

public class Conexion {
    // conexion a mysql.
    private static String User = "root";
    private static String Password = "admin";
    private static String Url = "jdbc:mysql://localhost:3306/pc1";

    public Connection getConnection() {
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection(Url, User, Password);
            System.out.println("Conexión estable");
        } catch (SQLException e) {
            System.out.println("Error en conexion.");
        }
        return con;
    }
}