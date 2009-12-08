package dominio.brokers;
import dominio.Alojamiento;
import persistencia.Broker;
import persistencia.*;

public class BrokerAlojamiento extends Broker{
     
     public String SQLInsertar( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "INSERT INTO alojamientos"
               + " (id_alojamiento,nombre,tipo,estrellas,pension)"
               + " VALUES (@1,@2,@3,@4,@5)";          
          sql.replace("@1",aloja.getOid() + "");
          sql.replace("@2",aloja.getNombre()); 
          sql.replace("@3",aloja.getTipo().toString()); 
          sql.replace("@4",aloja.getEstrellas() + ""); 
          sql.replace("@5",aloja.getPension().toString());    
          
          return sql;
     } 
     
     public String SQLActualizar( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "UPDATE alojamientos"
               + " SET nombre = @1,"
               + " tipo = @2,"
               + " estrellas = @3,"
               + " pension = @4"
               + " WHERE id_alojamiento = @5";
          sql.replace("@1",aloja.getNombre()); 
          sql.replace("@2",aloja.getTipo().toString()); 
          sql.replace("@3",aloja.getEstrellas() + ""); 
          sql.replace("@4",aloja.getPension().toString());   
          sql.replace("@5",aloja.getOid() + "");   
        
          return sql;
     }    
     
     public String SQLEliminar( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "DELETE FROM alojamientos"
               + " WHERE id_alojamiento = @1";          
          sql.replace("@1",aloja.getOid() + ""); 
          
          return sql;
     }   
     
     public String SQLLeer( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "SELECT * FROM alojamientos"
               + " WHERE id_alojamiento = @1";
          sql.replace("@1",aloja.getOid() + "");
          
          return sql;
     }    
     
     public IPersistente readerToObject( IPersistente o ){
          
          return o;
     }

}
