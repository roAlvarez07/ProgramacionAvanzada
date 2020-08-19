package parcial_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdministradorDeConexiones {

   public static Connection getConnection() throws ReflectiveOperationException, SQLException {
        
     
            // Establece el nombre del driver a utilizar
            String dbDriver = "com.mysql.jdbc.Driver";
            // Establece la conexion a utilizar
            String dbConnString = "jdbc:mysql://localhost:3306/usuarios";
            // Establece el usuario de la base de datos
            String dbUser = "root";
            // Establece la contraseña de la base de datos
            String dbPassword = "";
            // Establece el driver de conexion
            Class.forName(dbDriver).newInstance();
            // Retorna la conexion
            return DriverManager.getConnection(dbConnString, dbUser, dbPassword);
      
    }

}
