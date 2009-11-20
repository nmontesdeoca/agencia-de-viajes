package dominio.brokers;
import dominio.Alojamiento;
import persistencia.Broker;
import persistencia.*;

public class BrokerAlojamiento extends Broker{
     
     public String SQLInsertar( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "INSERT INTO alojamientos"
               + " (nombre,tipo,estrellas,pension)"
               + " VALUES (@1,@2,@3,@4)";
          sql.replace("@1",aloja.getNombre()); 
          sql.replace("@2",aloja.getTipo().toString()); 
          sql.replace("@3",aloja.getEstrellas() + ""); 
          sql.replace("@4",aloja.getPension().toString());    
          
          return sql;
     } 
     
     public String SQLActualizar( IPersistente o ){
          return "";
     }    
     
     public String SQLEliminar( IPersistente o ){
          return "";
     }   
     
     public String SQLLeer( IPersistente o ){
          return "";
     }    
     
     public IPersistente readerToObject( IPersistente o ){
          return o;
     }

}