package persistencia;

import java.sql.*;

public class HandlerPersistencia{
     
     private static HandlerPersistencia HANDLER_PERSISTENCIA = null;
     private static String stringConexion;
     private Connection conexion;
     private ResultSet reader;
     
     public HandlerPersistencia(){
          
          try{
               
               //cargamos driver     
               Class.forName("com.mysql.jdbc.Driver");
               
               //creamos la conexion
               conexion = DriverManager.getConnection("jdbc:mysql://localhost/agencia?user=&password=");
              
               /*                        
               //iterar en el result
               while( result.next() ){
                    
                    //obtener datos del result, sabiendo la posicion de la columna( ej: 2 )
                    result.getString(2);
               */
               
               
               
          }catch(ClassNotFoundException classNotFoundException){
               
          }catch(SQLException sqlException){
               
          }
     }
     
     public void setStringConexion(String nuevoString){
          this.stringConexion = nuevoString;
     }
     
     public String getStringConexion(){
          return this.stringConexion;
     }     
          
     public static HandlerPersistencia GetInstance(){
          
          if(HANDLER_PERSISTENCIA == null){
               HANDLER_PERSISTENCIA = new HandlerPersistencia();
          }
          return HANDLER_PERSISTENCIA;
          
     }
     
     public Object leerRegistro(String col){
          Object o = null;
          try{
             o = reader.getObject(col);
          }catch(SQLException e){
          
          }
          return o;
     }
     
     public Object ejecutarSentencia( String s ){
          try{
          //creamos objeto Statement para hacer consulta
          Statement consulta = conexion.createStatement();          
  
          reader = consulta.executeQuery(s);
          consulta.close();
          }catch(SQLException sqlException){
               
          }
          return  null;
          
     }
     
     public void terminarConsulta(){
          try{
               reader.close();
          }catch(SQLException e){
               System.out.println("Imposible terminar consulta");
          }
     }
     
     public void cerrarConexion(){
          try{
               conexion.close();
          }catch(SQLException e){
               System.out.println("Imposible cerrar conexion");
          }
     }
     
}