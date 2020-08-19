package parcial_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public  class Usuario {

    private static int nroCuenta;
    private static int nip;
    private static int saldo;

    @Override
    public String toString() {
        return "Usuario{}";
    }

    public static int getSaldo() {
        return saldo;
    }

    public static int getNro_Cuenta() {
        return nroCuenta;
    }

    public static int getNIP() {
        return nip;
    }

    public static void setNIP(int NIP) {
        Usuario.nip = NIP;
    }

    public static void setSaldo(int Saldo) {
        Usuario.saldo = Saldo;
    }

    public Usuario(int nroCuenta, int nip, int saldo) {
        this.nroCuenta = nroCuenta;
        this.nip  = nip;
        this.saldo = saldo;

    }
    ATMUI atm;
    Connection laConexion;
    PreparedStatement retiro;
    PreparedStatement deposito;
    Statement stmtConsulta;
    ResultSet rs;

   
    /***********************************************Retiro**************************************************/
    public void retirar(int extraer) throws SQLException, ReflectiveOperationException {
        try {
            laConexion = AdministradorDeConexiones.getConnection();
            if (saldo > extraer) {

                retiro = laConexion.prepareStatement(
                        "UPDATE usuarios SET Saldo = ? - '" + extraer + 
                        "' WHERE Nro_Cuenta =" + nroCuenta);
                retiro.setInt(1, getSaldoFinal());
                retiro.execute();
               
                consultarSaldo();
                
                StringBuilder ficha = new StringBuilder();
                ficha.append("\n")
                     .append("\n")
                     .append("/****************************************/ \n")
                     .append("/RETIRO DE EFECTIVO EFECTUADO EXITOSAMENTE/ \n")
                     .append("/****************************************/ \n");   
                System.out.println(ficha);
                laConexion.close();
            } else {
                JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /***********************************************************************************************************/

    /*************************************************Deposito**************************************************/
    public void deposito(int depositar) throws SQLException, ReflectiveOperationException {

        laConexion = AdministradorDeConexiones.getConnection();
        try {

            deposito = laConexion.prepareStatement(
            "UPDATE usuarios SET Saldo = ? + '" + depositar + 
                "' WHERE Nro_Cuenta =" +nroCuenta);
            
            deposito.setInt(1, getSaldoFinal());
            deposito.execute();
           
            consultarSaldo();
            
            StringBuilder ficha = new StringBuilder();
                 ficha.append("\n")
                    .append("\n")
                    .append("/****************************************/ \n")
                    .append("/DEPOSITO DE EFECTIVO EFECTUADO EXITOSAMENTE/ \n")
                    .append("/****************************************/ \n");   
                System.out.println(ficha);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        laConexion.close();

    }

    /**
     * *******************************************************************************************************
     */

    public void consultarSaldo() throws SQLException, ReflectiveOperationException {

        try {

            laConexion = AdministradorDeConexiones.getConnection();
            String consultaSaldo = "SELECT Saldo FROM usuarios Where Nro_cuenta ="+ nroCuenta;
            stmtConsulta = laConexion.createStatement();
            rs = stmtConsulta.executeQuery(consultaSaldo);
            while (rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "Su saldo es " + rs.getInt("Saldo"));
                       
                StringBuilder ficha = new StringBuilder();
                 ficha.append("\n")
                    .append("\n")
                    .append("/**************/ \n")
                    .append("/CONSULTA DE SALDO EFECTUADA EXITOSAMENTE/ \n")
                    .append("/**************/ \n");   
                System.out.println(ficha);
            
            }
        } catch (Exception ex) {
            Logger.getLogger(Bienvenida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public int getSaldoFinal() throws SQLException, ReflectiveOperationException {

        try {

            laConexion = AdministradorDeConexiones.getConnection();
            String consultaSaldo = "SELECT Saldo FROM usuarios Where Nro_cuenta ="+ nroCuenta;
            stmtConsulta = laConexion.createStatement();
            rs = stmtConsulta.executeQuery(consultaSaldo);
            while (rs.next()) {
                
                        this.setSaldo(rs.getInt("Saldo"));
    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bienvenida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (this.getSaldo());
    }
}