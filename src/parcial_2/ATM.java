/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import parcial_2.AdministradorDeConexiones;
import parcial_2.Usuario;
/**
 *
 * @author Alumno
 */
public class ATM {

    private Connection laConexion;
    private Statement stmtConsulta;
    private ResultSet rs;
    
    
    public ATM(){
	}
    
    public Usuario ingresar(int nro_Cuenta, int nip) throws SQLException, ReflectiveOperationException {

        Usuario usuario = null;
        laConexion = AdministradorDeConexiones.getConnection();

        String ingresar = "SELECT * FROM usuarios WHERE Nro_Cuenta = " + nro_Cuenta + " AND NIP = " + nip;
        stmtConsulta = laConexion.createStatement();
        rs = stmtConsulta.executeQuery(ingresar);
        if (rs.next()) {
            
            StringBuilder ficha = new StringBuilder();
               ficha.append("/****************************************/ \n")
                    .append("/BIENVENIDO AL SISTEMA DE CAJERO VIRTUAL/ \n")
                    .append("/****************************************/ \n")
                    .append("\n")
                    .append("  Numero de cuenta:  ")
                    .append(rs.getString("Nro_cuenta"))
                    .append("\n")
                    .append("  Numero de Identificacion personal:  ")
                    .append(rs.getString("NIP"))
                    .append("\n")
                    .append("  Saldo:  ")
                    .append(rs.getInt("Saldo"))
                    .append("\n")
                    .append("\n")    
                    .append("/****************************************/ \n")
                    .append("/****************************************/");
            System.out.println(ficha);
            
          usuario = new Usuario(rs.getInt("Nro_cuenta")
                               ,rs.getInt("NIP")
                               ,rs.getInt("Saldo"));
        }else{
            JOptionPane.showMessageDialog(null, "Validacion Incorrecta");            
            laConexion.close();
            System.exit(0);
        }
        laConexion.close();
        return usuario;
    }
}
