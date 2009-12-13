package persistencia;

import java.sql.*;

public class HandlerPersistencia{
     
     private static HandlerPersistencia HANDLER_PERSISTENCIA = null;
     private static String stringConexion;
     private Connection conexion;
     private ResultSet reader;
     private int num = 0;
     
     public HandlerPersistencia(){
          
          try{
               
               //cargamos driver     
               Class.forName("com.mysql.jdbc.Driver");
               
               //creamos la conexion
               conexion = DriverManager.getConnection("jdbc:mysql://localhost/agencia?user=root&password=4724");
              
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
          HandlerPersistencia.stringConexion = nuevoString;
     }
     
     public String getStringConexion(){
          return HandlerPersistencia.stringConexion;
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
        	  String[] str = s.split(" ");
          //creamos objeto Statement para hacer consulta
          Statement consulta = conexion.createStatement();          
          if( str[0].equals("SELECT") )
        	  reader = consulta.executeQuery(s);
          else
        	 consulta.executeUpdate(s);
          reader.first();
          //consulta.close(); Si cierro la consulta, me arroja excepcion, imagino que se elimina el valor del reader( ResultSet )
          }catch(SQLException sqlException){
               
          }
          return  null;
          
     }
     
     public boolean hayMasRegistros(){
    	boolean ret = false;
    	try{
    		if(num == 0){
        		reader.beforeFirst();
        		num++;
    		}
    		if( reader.next() )
    			ret = true;
    	}catch(SQLException e){
    		
    	}
    	return ret;
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