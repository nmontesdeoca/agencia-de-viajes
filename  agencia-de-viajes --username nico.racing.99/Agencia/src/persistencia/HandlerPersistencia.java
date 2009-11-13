package persistencia;

import java.sql.*;

public class HandlerPersistencia{
     
     private static HandlerPersistencia HANDLER_PERSISTENCIA = null;
     
     public HandlerPersistencia(){
          
          try{
               
               //cargamos driver     
               Class.forName("com.mysql.jdbc.Driver");
               
               //creamos la conexion
               Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/dbViajes?user=&password=");
               
               //creamos objeto Statement para hacer consulta
               Statement consulta = conexion.createStatement();
               
               //consulta sql
               String sql = "";
               ResultSet result = consulta.executeQuery(sql);
               
               //iterar en el result
               while( result.next() ){
                    
                    //obtener datos del result, sabiendo la posicion de la columna( ej: 2 )
                    result.getString(2);
                    
               }
               
          }catch(ClassNotFoundException classNotFoundException){
               
          }catch(SQLException sqlException){
               
          }
     }
     
      public static HandlerPersistencia GetInstance(){
          
          if(HANDLER_PERSISTENCIA == null){
               HANDLER_PERSISTENCIA = new HandlerPersistencia();
          }
          return HANDLER_PERSISTENCIA;
          
     }
      
      public String ejecutarSentencia( String s ){
           return s;
      }
     
}