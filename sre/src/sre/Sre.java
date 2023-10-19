
package sre;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Sre {

    
    public static void main(String[] args) {
        
        /** referenciar el usuario y password con el cual me conectaré a la base de datos */
        String usuario = "root";
        String password = "";
        
        String url = "jdbc:mysql://localhost:3306/sre_colegios";
        
        
        /** establecer conexión */

        Connection conexion; 
        Statement statement; /** permite ejecutar sentencias sql a través de la conexión establecida */
        ResultSet rs; /** sirve como objeto que tiene la capacidad de recibir la respuesta desde la base de datos, como el reflejo de una tabla */
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sre.class.getName()).log(Level.SEVERE, null, ex);
          }   
         
         /** establecer la conexion*/
                
            try {
                conexion = DriverManager.getConnection(url,usuario,password);
                statement = conexion.createStatement();
            
             /**INSERTAR información a la base de datos*/
             
            statement.executeUpdate("INSERT INTO ESTUDIANTE(Nombre_estudiante,Apellido_estudiante)VALUES('SARA','MOTTA')");
            rs = statement.executeQuery("SELECT * FROM ESTUDIANTE");
            rs.next();
                
            do{
            
            System.out.println(rs.getInt("Codigo_estudiante")+" "+rs.getString("Nombre_estudiante")+" "+rs.getString("Apellido_estudiante" ));
                
            }while(rs.next());
            
            } catch (SQLException ex) {
                Logger.getLogger(Sre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /** CONSULTAR */  
            
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sre_colegios","root","");
            PreparedStatement sentencia = cn.prepareStatement("SELECT * FROM ESTUDIANTE WHERE CODIGO_ESTUDIANTE = ?");
            sentencia.setString(1, "1111");
            ResultSet prs =sentencia.executeQuery();
            prs.next();
            
            do {
                System.out.println(
                prs.getString("Nombre_estudiante") + " " + prs.getString("Apellido_estudiante") + " " + prs.getString("Emai_estudiante") + " " + prs.getString("Id_documento"));
            } while(prs.next()); 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         /** ELIMINAR */
         
         
        try {
            Connection cne = DriverManager.getConnection("jdbc:mysql://localhost:3306/sre_colegios","root","");
            PreparedStatement sentenciaEliminar = cne.prepareStatement("DELETE FROM ESTUDIANTE WHERE CODIGO_ESTUDIANTE = ?");
            sentenciaEliminar.setString(1, "22");
            int elm = sentenciaEliminar.executeUpdate();
            
            System.out.println("Elemento eliminado: " + elm);
                                   
        } catch (SQLException ex) {
            Logger.getLogger(Sre.class.getName()).log(Level.SEVERE, null, ex);
        }
                             
     }
 }
